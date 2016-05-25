//
//  HomeViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 18/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

// Clef GoogleAPI IOS : AIzaSyBrBX4Q_tXvnCezexSX0c61SjvGICiJ_0w

import UIKit

class HomeViewController: UIViewController, UITableViewDataSource {

    // Variables
    var mArea : Area!
    var areaList  = [Area]()
    var noteList  = [Note]()
    var pointList = [Point]()
    let areaCont  = AreaController()
    let pointCont = PointController()
    let noteCont  = NoteController()
    
    // Outlets
    @IBOutlet weak var noteTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //areaList = areaCont.getAreas()
        
        //pointList = pointCont.getPoints()

        //noteList = noteCont.getNotes()
        
        if(noteList.count == 0)
        {
            
            let newDate    = NSDate()
            let dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd hh:mm:ss"
            let newStringDate = dateFormatter.stringFromDate(newDate)
            
            mArea = Area(id: "87", name: "Za Waludo", color: "cd13b8")
            
            let note1 = Note(id: "0", author: "Admin", text: "Premier test IOS", date: newStringDate, idArea: mArea.getId())
            let note2 = Note(id: "1", author: "Admin", text: "Second test IOS MDR", date: newStringDate, idArea: mArea.getId())
            noteList.append(note1)
            noteList.append(note2)
            
            //noteCont.uploadNote(note1)
            
        }

        // self.title = mArea.getName()
        noteTableView.reloadData()
        
    }
    
    
    // Fonctions du TableView
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return noteList.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Déclaration de la cellule
        let cell = tableView.dequeueReusableCellWithIdentifier(NoteTableViewCell.identifier, forIndexPath: indexPath) as! NoteTableViewCell
        
        // Configuration de la cellule
        let author = noteList[indexPath.row].getAuthor()
        let text   = noteList[indexPath.row].getText()
        let date   = noteList[indexPath.row].getDate()
        
        cell.configureWithData(author, text: text, date: date)
        
        return cell
        
    }

    
    // Préparation du Segue, envoie de l'Album séléctionné
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "toAddNote") {
            let destination = segue.destinationViewController as! AddNoteViewController
            destination.noteList = noteList
            destination.mArea = mArea
        }
    }

}
