//
//  AreaController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 24/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import RealmSwift

class AreaController {
    
    // Champs
    var urlPath = "http://172.30.1.178:8080/exametrics-ws/"
    var pointCont = PointController()
    
    // Init
    init(){
    }
    
    // Fonction permettant de récupérer la liste de toutes les zones
    func getAreas(){
        
        // Déclaration de l'url et de la liste de Zones
        
        var listAreas = [Area]()
        let myUrl = NSURL(string: "\(urlPath)areas")!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(myUrl) {
            dataMaybe, _, errorMaybe in
            
            guard errorMaybe == nil else {
                NSLog("AreaController : N'as pas pu télécharger : \(errorMaybe!.description)")
                return
            }
            
            guard let data = dataMaybe else {
                NSLog("AreaController : Pas de données disponibles")
                return
            }
            
            guard let rootObj = try? NSJSONSerialization.JSONObjectWithData(data, options: []) else {
                NSLog("AreaController : Erreur dans le JSON ")
                return
            }
            
            guard let root = rootObj as? NSDictionary else {
                NSLog("AreaController : Erreur dans  le format")
                return
            }
            
            guard let result = root["result"] as? NSArray else {
                NSLog("PoinController : Probleme result")
                return
            }
            
            for index in 0..<result.count {
                let newId    = result[index]["idArea"] as! String
                let newName  = result[index]["nameArea"] as! String
                let newColor = result[index]["colorArea"] as! String
                
                let newArea = Area()
                newArea.setArea(newId, name: newName, color: newColor)
                
                let realm = try! Realm()
                
                try! realm.write {
                    realm.add(newArea)
                }
                
                listAreas.append(newArea)
            }
            
        }
        task.resume()
    }
    
    
    
    // Fonction permettant de récupérer une zone selon son id
    func getAreaById(idArea: String){
        
        
        // Si un id d'une des Areas de areaList correspond au paramètre idArea, return ?
        
        // Déclaration de l'url et de la Zone
        
        var myArea : Area!
        
        let myUrl = NSURL(string: "\(urlPath)areas?id=\(idArea)")!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(myUrl) {
            dataMaybe, _, errorMaybe in
            
            guard errorMaybe == nil else {
                NSLog("PoinController : N'as pas pu télécharger : \(errorMaybe!.description)")
                return
            }
            
            guard let data = dataMaybe else {
                NSLog("PoinController : Pas de données disponibles")
                return
            }
            
            guard let rootObj = try? NSJSONSerialization.JSONObjectWithData(data, options: []) else {
                NSLog("PoinController : Erreur dans le JSON ")
                return
            }
            
            guard let root = rootObj as? NSDictionary else {
                NSLog("PoinController : Erreur dans  le format")
                return
            }
            
            guard let result = root["result"] as? NSArray else {
                NSLog("PoinController : Probleme result")
                return
            }
            
            let newId    = result[0]["idArea"] as! String
            let newName  = result[0]["nameArea"] as! String
            let newColor = result[0]["colorArea"] as! String
            
            let newArea = Area()
            newArea.setArea(newId, name: newName, color: newColor)
            
            let realm = try! Realm()
            
            try! realm.write {
                realm.add(newArea)
            }

            myArea = newArea
            
        }
        task.resume()
        
    }
    
    
    
    
}
