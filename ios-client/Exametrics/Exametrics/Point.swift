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
    private var _longitude : Float!
    private var _latitude : Float!
    private var _idArea : String!
    
    // Getter & Setter
    func getId() -> String{
        return _id
    }
    
    func setId(id: String) {
        _id = id
    }
    
    func getLongitude() -> Float{
        return _longitude
    }
    
    func setLongitude(longitude: Float) {
        _longitude = longitude
    }
    
    func getLatitude() -> Float{
        return _latitude
    }
    
    func setLatitude(latitude: Float) {
        _latitude = latitude
    }
    
    func getIdArea() -> String{
        return _idArea
    }
    
    func setIdArea(idArea: String) {
        _idArea = idArea
    }
    
    // Constructeurs
    required init(id: String, longitude: Float, latitude: Float, idArea: String){
        
        _id = id
        _longitude = longitude
        _latitude = latitude
        _idArea = idArea
        
    }
    
}

