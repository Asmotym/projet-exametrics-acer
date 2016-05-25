//
//  AreaController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 24/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import Realm

class AreaController {
    
    // Champs
    //var urlPath = "http://172.30.1.178:8080/exametrics-ws/"
    var urlPath = "http://127.0.0.1:8888/exametrics-ws/"
    
    // Init
    init(){
    }
    
    // Fonction permettant de récupérer la liste de toutes les zones
    func getAreas(){
        
        // Déclaration de l'url et de la liste de Zones
        
        var listAreas = [Area]()
        urlPath += "areas"
        let myUrl = NSURL(string: urlPath)!
        
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
            
            for index in 0..<result.count {
                let newId    = result[index]["idArea"] as! String
                let newName  = result[index]["nameArea"] as! String
                let newColor = result[index]["colorArea"] as! String
                
                let newArea = Area(id: newId, name: newName, color: newColor)
                
                do {
                    let realm = RLMRealm.defaultRealm()
                    try realm.transactionWithBlock(){
                        realm.addObject(newArea)
                    }
                    
                }catch {
                    print("Error Realm getArea")
                }

                listAreas.append(newArea)
            }
            
            
            NSOperationQueue.mainQueue().addOperationWithBlock({
                //HomeViewController.refresh(HomeViewController)
            })
            
        }
        task.resume()
    }
    
    
    
    // Fonction permettant de récupérer une zone selon son id
    func getAreaById(idArea: String){
        
        var areaList: RLMResults {
            get {
                return Area.allObjects()
            }
        }
        
        // Si un id d'une des Areas de areaList correspond au paramètre idArea, return ?
        
        // Déclaration de l'url et de la Zone
        
        var myArea : Area!
        
        urlPath += "areas?id=\(idArea)"
        let myUrl = NSURL(string: urlPath)!
        
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
            
            let newArea = Area(id: newId, name: newName, color: newColor)
            
            do {
                let realm = RLMRealm.defaultRealm()
                try realm.transactionWithBlock(){
                        realm.addObject(newArea)
                    }
                
                }catch {
                    print("Error Realm getAreaById")
            }

            myArea = newArea
            
            
            // Stock Realm / UserDefault
            
            // Recharge si besoin
            
        }
        task.resume()
        
    }
    
    
    
    
}
