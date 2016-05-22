//
//  HomeViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 18/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import UIKit

class HomeViewController: UIViewController, UITableViewDataSource {

    // Variables
    var mArea : Area!
    var noteList = [Note]()
    
    // Outlets
    @IBOutlet weak var noteTableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mArea = Area(id: 1, name: "Za Waludo", color: "cd13b8")
        if(noteList.count == 0)
        {
            
            let note1 = Note(id: 0, login: "Admin", message: "Premier test", date: NSDate(), area: mArea)
            let note2 = Note(id: 1, login: "Admin", message: "Second test", date: NSDate(), area: mArea)
            noteList.append(note1)
            noteList.append(note2)
        }
        

        self.title = mArea.getName()
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
        let login = noteList[indexPath.row].getLogin()
        let message  = noteList[indexPath.row].getMessage()
        let date  = noteList[indexPath.row].getDate()
        
        let timestamp = NSDateFormatter()
        timestamp.dateFormat = "dd/MM/yyyy à hh:mm"
        let string = timestamp.stringFromDate(date)
        
        cell.configureWithData(login, message: message, date: string)
        
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
