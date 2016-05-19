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
    private var _longitude : String!
    private var _lattitude : String!
    
    // Getter & Setter
    func getId() -> Int{
        return _id;
    }
    
    func setId(id: Int) {
        _id = id
    }
    
    func getName() -> String{
        return _name
    }
    
    func setName(name: String) {
        _name = name
    }
    
    func getColor() -> String{
        return _color;
    }
    
    func setColor(color: String) {
        _color = color
    }
    
    // Constructeurs
    required init(id: Int, name: String, color: String){
        
        _id = id
        _name = name
        _color = color
        
    }
}