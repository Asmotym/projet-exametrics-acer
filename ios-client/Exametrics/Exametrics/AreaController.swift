//
//  AreaController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 24/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import Foundation

class AreaController {
    
    
    // Init
    init(){
    }
    
    // Fonction permettant de récupérer la liste de toutes les zones
    func getAreas() -> [Area]{
        
        // Déclaration de l'url et de la liste de Zones
        
        var listAreas = [Area]()
        let connectControl = ConnectionController()
        
        let result = connectControl.getListTuples("areas")
        
        for index in 0...(result.count - 1) {
            let newId    = result[index]["idArea"] as! String
            let newName  = result[index]["nameArea"] as! String
            let newColor = result[index]["colorArea"] as! String
            
            let newArea = Area(id: newId, name: newName, color: newColor)
                
            listAreas.append(newArea)
        }
        
        // Stockage Realm / UserDefault
        
        return listNotes
        
    }
    
    // Fonction permettant de récupérer une zone selon son id
    func getAreaById(idArea: String) -> Area{
        
        // Déclaration de l'url et de la Zone
        
        var myArea : Area!
        let connectControl = ConnectionController()
        
        let result = connectControl.getListTuples("areas?id=\(idArea)")
        
        let newId    = result[index]["idArea"] as! String
        let newName  = result[index]["nameArea"] as! String
        let newColor = result[index]["colorArea"] as! String
        
        let newArea = Area(id: newId, name: newName, color: newColor)
        
        myArea = newArea
        // Stock Realm / UserDefault
        
        return listNotes
        
    }
    
    
    
    
}
