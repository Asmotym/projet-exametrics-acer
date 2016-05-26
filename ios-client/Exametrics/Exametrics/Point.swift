//
//  Point.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 19/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import RealmSwift
import Realm

class Point : Object{
    
    // Champs
    private dynamic var _id        : String = ""
    private dynamic var _longitude : String = ""
    private dynamic var _latitude  : String = ""
    private dynamic var _idArea    : String = ""
    
    // Getter & Setter
    func getId() -> String{
        return _id
    }
    
    func setId(id: String) {
        _id = id
    }
    
    func getLongitude() -> Double{
        return Double(_longitude)!
    }
    
    func setLongitude(longitude: Double) {
        _longitude = String(longitude)
    }
    
    func getLatitude() -> Double{
        return Double(_latitude)!
    }
    
    func setLatitude(latitude: Double) {
        _latitude = String(latitude)
    }
    
    func getIdArea() -> String{
        return _idArea
    }
    
    func setIdArea(idArea: String) {
        _idArea = idArea
    }
    
    // Constructeurs
    func setPoint(id: String, longitude: Double, latitude: Double, idArea: String){
        
        _id = id
        _longitude = String(longitude)
        _latitude = String(latitude)
        _idArea = idArea
    }
    
    
}

