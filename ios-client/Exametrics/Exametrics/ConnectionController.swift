//
//  ConnectionController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 24/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation

class ConnectionController {
    
    
    // Champs
    var urlPath = "http://172.30.1.178:8080/exametrics-ws/"
    
    // Init
    init(){
    }
    
    // Fonction permettant de récupérer des données
    func getListTuples(tuple: String) -> NSArray{
        
        // Déclaration de l'url
        var myResult = NSArray()
        urlPath += tuple
        
        let myUrl = NSURL(string: urlPath)!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(myUrl) {
            dataMaybe, _, errorMaybe in
            
            guard errorMaybe == nil else {
                NSLog("N'as pas pu télécharger : \(errorMaybe!.description)")
                return
            }
            
            guard let data = dataMaybe else {
                NSLog("Pas de données disponibles")
                return
            }
            
            guard let rootObj = try? NSJSONSerialization.JSONObjectWithData(data, options: []) else {
                NSLog("Erreur dans le JSON ")
                return
            }
            
            guard let root = rootObj as? NSDictionary else {
                NSLog("Erreur dans  le format")
                return
            }
            
            guard let result = root["result"] as? NSArray else {
                NSLog("Probleme result")
                return
            }
            
            myResult = result
            
        }
        task.resume()
        return myResult
        
        
    }
    
    
    
    
    
}