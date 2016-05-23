//
//  ConnectionController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation

class Connection {
    
    // Champs
    private var urlPath : String
    private var url : NSURL!
    
    init(urlPath : String){
        self.urlPath = urlPath
    }
    
    
    // Fonctions 
    
    // Get Points
    // Fonction permettant de vérifier le formulaire de connexion
    func getPoints(login: String, password: String) {
        
        // Déclaration de l'url
        urlPath = "http://172.30.1.178:8080/exametrics-ws/points"
        url = NSURL(string: urlPath.stringByAddingPercentEscapesUsingEncoding(NSUTF8StringEncoding)!)!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(url) {
            dataMaybe, _, errorMaybe in
            
            guard errorMaybe == nil else {
                NSLog("Could not download data: \(errorMaybe!.description)")
                return
            }
            
            guard let data = dataMaybe else {
                NSLog("No data available")
                return
            }
            
            guard let rootObj = try? NSJSONSerialization.JSONObjectWithData(data, options: []) else {
                NSLog("Not proper JSON")
                return
            }
            
            guard let root = rootObj as? NSDictionary else {
                NSLog("A bad format")
                return
            }
            
            guard let result = root["result"] as? NSDictionary else {
                NSLog("Probleme message")
                return
            }
            
            guard let validation = result["isValid"] as? Bool,
                let outputMessage = result["message"] as? String else {
                    NSLog("Probleme isValid")
                    return
            }
            
            // Ajout de l'opération traitant les résultats
            NSOperationQueue.mainQueue().addOperationWithBlock({
                self.isValid = validation;
                
                if (self.isValid == true) {
                    self.progressBarDisplayer("Chargement…", true)
                    self.performSegueWithIdentifier("toAlbums", sender: self)
                } else {
                    self.messageLabel.text = outputMessage;
                    self.messageLabel.hidden = false;
                }
            })
            
        }
        task.resume()
        
    }

    
}


