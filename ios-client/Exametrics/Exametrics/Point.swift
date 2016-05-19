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
    private var _id : Int!
    private var _longitude : Float!
    private var _latitude : Float!
    private var _area : Area!
    
    // Getter & Setter
    func getId() -> Int{
        return _id
    }
    
    func setId(id: Int) {
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
    
    func getArea() -> Area{
        return _area
    }
    
    func setArea(area: Area) {
        _area = area
    }
    
    // Constructeurs
    required init(id: Int, longitude: Float, latitude: Float, area: Area){
        
        _id = id
        _longitude = longitude
        _latitude = latitude
        _area = area
        
    }
    
}

