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
    var urlPath : String
    var url : NSURL!
    
    // Init
    init(urlPath : String){
        self.urlPath = urlPath
        self.url = NSURL()
    }
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getPoints() -> [Point]{
        
        // Déclaration de l'url
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
            
            
            
            /*
            guard let validation = result[1] as? Bool,
                let outputMessage = result["message"] as? String else {
                    NSLog("PoinController : Probleme création Points")
                    return
            }
            
            
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
            */
        }
        task.resume()
        
        let listPoints = [Point]()
        
        return listPoints
        
    }
}


