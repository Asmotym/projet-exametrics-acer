//
//  PointController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation


class PointController {
    
    var urlPath = "http://172.30.1.178:8080/exametrics-ws/"
    var url : NSURL!
    
    
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getPoints() {
        
        // Déclaration de l'url
        url += "points"
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
            
            guard let result = root["result"] as? NSDictionary else {
                NSLog("PoinController : Probleme result")
                return
            }
            
            guard let validation = result["isValid"] as? Bool,
                let outputMessage = result["message"] as? String else {
                    NSLog("PoinController : Probleme isValid")
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


