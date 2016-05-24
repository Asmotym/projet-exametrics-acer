//
//  NoteController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
class NoteController {
    
    
    // Init
    init(){
    }
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getNotes() -> [Note]{
        
        // Déclaration de l'url et de la liste de Notes
        
        var listNotes = [Note]()
        let connectControl = ConnectionController()
        
        let result = connectControl.getListTuples("notes")
            
        for index in 0...(result.count - 1) {
            let newId        = result[index]["idNote"] as! String
            let newAuthor    = result[index]["authorNote"] as! String
            let newText      = result[index]["textNote"] as! String
            let newDate      = result[index]["dateNote"] as! String
            let newIdArea    = result[index]["idArea"] as! String
                
            // Formattage de la date
            let dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd hh:mm:ss"
            guard let newNSDate = dateFormatter.dateFromString(newDate) else {
                NSLog("Note Controller : Format date error")
                break
            }
                
            let newNote = Note(id: newId, author: newAuthor, text: newText, date: newNSDate, idArea: newIdArea)
                
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
        return listNotes
        
    }
}