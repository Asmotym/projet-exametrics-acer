//
//  Note.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 19/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation

class Note {
    
    // Champs
    private var _id : Int!
    private var _login : String!
    private var _message : String!
    private var _date : NSDate!
    private var _area : Area!
    
    // Getter & Setter
    func getId() -> Int{
        return _id
    }
    
    func setId(id: Int) {
        _id = id
    }
    
    func getLogin() -> String{
        return _login
    }
    
    func setLogin(login: String) {
        _login = login
    }
    
    func getMessage() -> String{
        return _message
    }
    
    func setMessage(message: String) {
        _message = message
    }
    
    func getDate() -> NSDate{
        return _date
    }
    
    func setDate(date: NSDate) {
        _date = date
    }
    
    func getArea() -> Area{
        return _area
    }
    
    func setArea(area: Area) {
        _area = area
    }
    
    // Constructeurs
    required init(id: Int, login: String, message: String, date: NSDate, area : Area){
        
        _id = id
        _login = login
        _message = message
        _date = date
        _area = area
        
    }
    
}