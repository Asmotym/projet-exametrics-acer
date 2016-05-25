//
//  Point.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 19/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation

class Point {
    
    // Champs
    private var _id : String!
    private var _longitude : Double!
    private var _latitude : Double!
    private var _idArea : String!
    
    // Getter & Setter
    func getId() -> String{
        return _id
    }
    
    func setId(id: String) {
        _id = id
    }
    
    func getLongitude() -> Double{
        return _longitude
    }
    
    func setLongitude(longitude: Double) {
        _longitude = longitude
    }
    
    func getLatitude() -> Double{
        return _latitude
    }
    
    func setLatitude(latitude: Double) {
        _latitude = latitude
    }
    
    func getIdArea() -> String{
        return _idArea
    }
    
    func setIdArea(idArea: String) {
        _idArea = idArea
    }
    
    // Constructeurs
    required init(id: String, longitude: Double, latitude: Double, idArea: String){
        
        _id = id
        _longitude = longitude
        _latitude = latitude
        _idArea = idArea
        
    }
    
}

