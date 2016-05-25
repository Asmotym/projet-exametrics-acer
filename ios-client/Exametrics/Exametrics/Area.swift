//
//  Area.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 18/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation
import Realm

class Area: RLMObject {
    
    // Champs
    private dynamic var _id    : String!
    private dynamic var _name  : String!
    private dynamic var _color : String!
    
    // Getter & Setter
    func getId() -> String{
        return _id
    }
    
    func setId(id: String) {
        _id = id
    }
    
    func getName() -> String{
        return _name
    }
    
    func setName(name: String) {
        _name = name
    }
    
    func getColor() -> String{
        return _color
    }
    
    func setColor(color: String) {
        _color = color
    }
    
    override init() {
        super.init()
    }
    // Constructeurs
    required init(id: String, name: String, color: String){
        
        _id = id
        _name = name
        _color = color
        super.init()
    }
}