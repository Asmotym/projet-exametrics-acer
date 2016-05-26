

//
//  PointController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import RealmSwift

class PointController {
    
    
    // Champs
    var urlPath = "http://172.30.1.178:8080/exametrics-ws/"
    
    // Init
    init(){
    }
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getPoints(){
        
        // Déclaration de l'url et de la liste de Points
        
        var listPoints = [Point]()
        let myUrl = NSURL(string: "\(urlPath)points")!
        
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
                let newIdPoint   = result[index]["idPoint"] as! String
                let newLongitude = result[index]["longitude"] as! String
                let newLatitude  = result[index]["latitude"] as! String
                let newIdArea    = result[index]["idArea"] as! String
                
                let newPoint     = Point()
                newPoint.setPoint(newIdPoint, longitude: Double(newLongitude)!, latitude: Double(newLatitude)!, idArea: newIdArea)
                
                let realm = try! Realm()
                
                try! realm.write {
                    realm.add(newPoint)
                }
                
                listPoints.append(newPoint)
            }
            
            
            // Recharge si besoin
            
        }
        task.resume()
        
        
    }
    
    
    // Fonction permettant de récupérer les Points selon l'id d'une Zone
    func getPointsByIdArea(idArea: String) -> [Point]{
        
        // Déclaration de l'url et de la liste de Point
        
        var listPoints = [Point]()
        let url = NSURL(string: "\(urlPath)point?id=\(idArea)")
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {
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
                let newIdPoint   = result[index]["idPoint"] as! String
                let newLongitude = result[index]["longitude"] as! String
                let newLatitude  = result[index]["latitude"] as! String
                let newIdArea    = result[index]["idArea"] as! String
                let newPoint     = Point()
                newPoint.setPoint(newIdPoint, longitude: Double(newLongitude)!, latitude: Double(newLatitude)!, idArea: newIdArea)
                
                let realm = try! Realm()
                
                try! realm.write {
                    realm.add(newPoint)
                }
                
                listPoints.append(newPoint)
            }
        
            NSOperationQueue.mainQueue().addOperationWithBlock({
                
            })
            
        }
        task.resume()
        return listPoints
        
    }
    
}





