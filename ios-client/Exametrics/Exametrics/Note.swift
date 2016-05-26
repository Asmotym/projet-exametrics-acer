//
//  Note.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 19/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import RealmSwift
import Realm

class Note : Object{
    
    // Champs
    private dynamic var _id     : String = ""
    private dynamic var _author : String = ""
    private dynamic var _text   : String = ""
    private dynamic var _date   : String = ""
    private dynamic var _idArea : String = ""
    
    // Getter & Setter
    func getId() -> String{
        return _id
    }
    
    func setId(id: String) {
        _id = id
    }
    
    func getAuthor() -> String{
        return _author
    }
    
    func setAuthor(author: String) {
        _author = author
    }
    
    func getText() -> String{
        return _text
    }
    
    func setText(text: String) {
        _text = text
    }
    
    func getDate() -> String{
        return _date
    }
    
    func setDate(date: String) {
        _date = date
    }
    
    func getIdArea() -> String{
        return _idArea
    }
    
    func setIdArea(idArea: String) {
        _idArea = idArea
    }
    
    
    // Constructeurs
    func setNote(id: String, author: String, text: String, date: String, idArea : String){
        
        _id = id
        _author = author
        _text = text
        _date = date
        _idArea = idArea
    }
    
}