//
//  NoteController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 23/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import RealmSwift

class NoteController {
    
    
    // Champs
    var urlPath = "http://172.30.1.178:8080/exametrics-ws/"
    
    // Init
    init(){
    }
    
    // Fonction permettant de vérifier le formulaire de connexion
    func getNotes(){
        
        // Déclaration de l'url et de la liste de Points
        
        var listNotes = [Note]()
        urlPath += "notes"
        let url = NSURL(string: urlPath.stringByAddingPercentEscapesUsingEncoding(NSUTF8StringEncoding)!)!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(url) {
            dataMaybe, _, errorMaybe in
            
            guard errorMaybe == nil else {
                NSLog("NoteController : N'as pas pu télécharger : \(errorMaybe!.description)")
                return
            }
            
            guard let data = dataMaybe else {
                NSLog("NoteController : Pas de données disponibles")
                return
            }
            
            guard let rootObj = try? NSJSONSerialization.JSONObjectWithData(data, options: []) else {
                NSLog("NoteController : Erreur dans le JSON ")
                return
            }
            
            guard let root = rootObj as? NSDictionary else {
                NSLog("NoteController : Erreur dans  le format")
                return
            }
            
            guard let result = root["result"] as? NSArray else {
                NSLog("NoteController : Probleme result")
                return
            }
            
            for index in 0..<result.count {
                let newId        = result[index]["idNote"] as! String
                let newAuthor    = result[index]["authorNote"] as! String
                let newText      = result[index]["textNote"] as! String
                let newDate      = result[index]["dateNote"] as! String
                let newIdArea    = result[index]["idArea"] as! String
                
                
                let newNote = Note()
                newNote.setNote(newId, author: newAuthor, text: newText, date: newDate, idArea: newIdArea)
                
                let realm = try! Realm()
                
                try! realm.write {
                    realm.add(newNote)
                }
                
                listNotes.append(newNote)
            }
            
            // Stock Realm / UserDefault
            // Recharge si besoin
            
            
        }
        
        task.resume()
        
    }
    
    
    // Fonction permettant de récupérer les Notes selon l'id d'une Zone
    func getNotesByIdArea(idArea: String) {
        
        // Déclaration de l'url et de la liste de Note
        var listNotes = [Note]()
        urlPath += "notes?id=\(idArea)"
        
        let url = NSURL(string: urlPath.stringByAddingPercentEscapesUsingEncoding(NSUTF8StringEncoding)!)!
        
        // Mise en place de la tâche
        let task = NSURLSession.sharedSession().dataTaskWithURL(url) {
            dataMaybe, _, errorMaybe in
            
            guard errorMaybe == nil else {
                NSLog("NoteController : N'as pas pu télécharger : \(errorMaybe!.description)")
                return
            }
            
            guard let data = dataMaybe else {
                NSLog("NoteController : Pas de données disponibles")
                return
            }
            
            guard let rootObj = try? NSJSONSerialization.JSONObjectWithData(data, options: []) else {
                NSLog("NoteController : Erreur dans le JSON ")
                return
            }
            
            guard let root = rootObj as? NSDictionary else {
                NSLog("NoteController : Erreur dans  le format")
                return
            }
            
            guard let result = root["result"] as? NSArray else {
                NSLog("NoteController : Probleme result")
                return
            }
            
            for index in 0..<result.count {
                let newId        = result[index]["idNote"] as! String
                let newAuthor    = result[index]["authorNote"] as! String
                let newText      = result[index]["textNote"] as! String
                let newDate      = result[index]["dateNote"] as! String
                let newIdArea    = result[index]["idArea"] as! String
                
                
                let newNote = Note()
                newNote.setNote(newId, author: newAuthor, text: newText, date: newDate, idArea: newIdArea)
                
                let realm = try! Realm()
                
                try! realm.write {
                    realm.add(newNote)
                }
                
                listNotes.append(newNote)
            }
            
            // Stock Realm / UserDefault
            // Recharge si besoin
            
            
        }
        
        task.resume()
        
        
    }
    
    // Fonction permettant d'upload des notes
    func uploadNote(newNote: Note){
        
        let myJson = ["idNote": "", "authorNote": newNote.getAuthor(), "textNote": newNote.getText(), "dateNote": newNote.getDate(), "idArea" : newNote.getIdArea()]
        
        urlPath += "notes"
        let myUrl = NSURL(string: urlPath)!
        
        let data = try! NSJSONSerialization.dataWithJSONObject(myJson, options: [])
        
        let request = NSMutableURLRequest(URL: myUrl)
        request.HTTPMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        let task = NSURLSession.sharedSession().uploadTaskWithRequest(request, fromData: data)
        task.resume()
        
    }
}
