//
//  NoteController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
class NoteController {
    
    
    // Champs
    var urlPath = "http://127.0.0.1:8888/exametrics-ws/"
    
    // Init
    init(){
    }
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getNotes() -> [Note]{
        
        // Déclaration de l'url et de la liste de Points
        
        var listNotes = [Note]()
        urlPath += "notes"
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
            
            guard let idPoint = result[0]["idNote"] as? String else {
                NSLog("PoinController : Probleme result")
                return
            }
            
            
            for index in 0...result.count {
                let newId        = result[index]["idNote"] as! String
                let newAuthor    = result[index]["authorNote"] as! String
                let newText      = result[index]["textNote"] as! String
                let newDate      = result[index]["dateNote"] as! String
                let newIdArea    = result[index]["idArea"] as! String
                let newNote      = Note(id: newId, author: newAuthor, text: newText, date: newDate, idArea: newIdArea)
                listNotes.append(newNote)
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
        
        return listNotes
        
    }
}