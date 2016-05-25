//
//  HomeViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 18/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

// Clef GoogleAPI IOS : AIzaSyBrBX4Q_tXvnCezexSX0c61SjvGICiJ_0w

import UIKit
import Realm

class HomeViewController: UIViewController, UITableViewDataSource {

    // Variables
    var mArea : Area!
    //var areaList  = [Area]()
    //var noteList  = [Note]()
    //var pointList = [Point]()
    let areaCont  = AreaController()
    let pointCont = PointController()
    let noteCont  = NoteController()
    
    var areaList: RLMResults {
        get {
            return Area.allObjects()
        }
    }
    
    var pointList: RLMResults {
        get {
            return Point.allObjects()
        }
    }
    var noteList: RLMResults {
        get {
            return Note.allObjects()
        }
    }
    
    // Outlets
    @IBOutlet weak var noteTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
        if (areaList.count == 0){
            areaCont.getAreas()
        }
        
        if (pointList.count == 0){
            pointCont.getPoints()
        }

        if (noteList.count == 0){
            noteCont.getNotes()
        }
        
        
        // self.title = mArea.getName()
        noteTableView.reloadData()
        
    }
    
    
    func refresh() {
        
    }
    
    // Fonctions du TableView
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Int(noteList.count)
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Déclaration de la cellule
        let cell = tableView.dequeueReusableCellWithIdentifier(NoteTableViewCell.identifier, forIndexPath: indexPath) as! NoteTableViewCell
        
        // Récupération des notes
        let index = UInt(indexPath.row)
        let noteItem = noteList.objectAtIndex(index) as! Note // [4]
        
        // Configuration de la cellule
        let author = noteItem.getAuthor()
        let text   = noteItem.getText()
        let date   = noteItem.getDate()
        
        cell.configureWithData(author, text: text, date: date)
        
        return cell
        
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        noteTableView.reloadData() 
    }

    
    // Préparation du Segue, envoie de la Zone séléctionnée
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "toAddNote") {
            let destination = segue.destinationViewController as! AddNoteViewController
            destination.mArea = mArea
        }
    }

}
