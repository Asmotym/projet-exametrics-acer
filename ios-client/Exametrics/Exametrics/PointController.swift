/*

//
//  PointController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
class PointController {
    
    
    // Champs
    var urlPath = "http://127.0.0.1:8888/exametrics-ws/"
    
    // Init
    init(){
    }
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getPoints() -> [Point]{
        
        // Déclaration de l'url et de la liste de Points
        
        var listPoints = [Point]()
        urlPath += "points"
        let url = NSURL(string: urlPath.stringByAddingPercentEscapesUsingEncoding(NSUTF8StringEncoding)!)!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(url) {
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
        
            for index in 0...result.count {
                let newIdPoint   = result[index]["idPoint"] as! String
                let newLongitude = result[index]["longitude"] as! String
                let newLatitude  = result[index]["latitude"] as! String
                let newIdArea    = result[index]["idArea"] as! String
                let newPoint     = Point(id: newIdPoint, longitude: Float(newLongitude)!, latitude: Float(newLatitude)!, idArea: newIdArea)
                listPoints.append(newPoint)
            }
            
            /*
            // Write our movie objects to the database
            let realm = RLMRealm.defaultRealm()
            realm.beginWriteTransaction()
            
            for point in movies {
                /*  This method will avoid duplicating records by looking at the
                 primary key we've set on our object. Go look at the XMCMovie
                 class to see that method defined.
                 */
                XMCMovie.createOrUpdateInDefaultRealmWithObject(movie)
                
                // Alternatively, you could add new objects by calling this method
                // realm.addObject(movie)
                // or
                // realm.addObjects(movies) // An array of objects
            }
            
            realm.commitWriteTransaction()
 
            */
            
            
        }
        task.resume()
        
        return listPoints
        
    }
}

*/





//
//  PointController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//
import Foundation

class PointController {
    
    let connectControl = ConnectionController()
    
    // Init
    init(){
    }
    
    // Fonction permettant de récupérer le liste de tous les Points
    func getPoints() -> [Point]{
        
        // Déclaration de l'url et de la liste de Points
        
        var listPoints = [Point]()
        
        let result = connectControl.getListTuples("points")
            
        for index in 0...(result.count - 1) {
            let newIdPoint   = result[index]["idPoint"] as! String
            let newLongitude = result[index]["longitude"] as! String
            let newLatitude  = result[index]["latitude"] as! String
            let newIdArea    = result[index]["idArea"] as! String
            let newPoint     = Point(id: newIdPoint, longitude: Float(newLongitude)!, latitude: Float(newLatitude)!, idArea: newIdArea)
            listPoints.append(newPoint)
        }
        
        // Stock Realm / UserDefault
        
        return listPoints
        
    }
    
    
    // Fonction permettant de récupérer les Points selon l'id d'une Zone
    func getPointsByIdArea(idArea: String) -> [Point]{
        
        // Déclaration de l'url et de la liste de Point
        
        var listPoints = [Point]()
        
        let result = connectControl.getListTuples("point?id=\(idArea)")
        
        for index in 0...(result.count - 1) {
            let newIdPoint   = result[index]["idPoint"] as! String
            let newLongitude = result[index]["longitude"] as! String
            let newLatitude  = result[index]["latitude"] as! String
            let newIdArea    = result[index]["idArea"] as! String
            let newPoint     = Point(id: newIdPoint, longitude: Float(newLongitude)!, latitude: Float(newLatitude)!, idArea: newIdArea)
            listPoints.append(newPoint)
        }
        
        // Stock Realm / UserDefault
        
        return listPoints
        
    }
    
}




