//
//  AddNoteViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 20/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import UIKit

class AddNoteViewController: UIViewController {

    // Variables
    var noteList = [Note]()
    var mArea : Area!
    
    // Outlets
    @IBOutlet weak var inputMessage: UITextView!
    @IBOutlet weak var inputAuthor: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Ajoutez une note"
        
    }

    @IBAction func touchAddButton(sender: AnyObject) {
        
        let newId      = "\(noteList.count + 1)"
        let newAuthor  = inputAuthor.text
        let newText    = inputMessage.text
        let newDate    = NSDate()
        
        let newNote = Note(id: newId, author: newAuthor!, text: newText, date: newDate, idArea: mArea.getId())
        
        noteList.append(newNote)
    
        navigationController?.popViewControllerAnimated(true)
        
    }

    // Préparation du Segue, envoie de l'Album séléctionné
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
            let destination = segue.destinationViewController as! HomeViewController
            destination.noteList = noteList
            destination.mArea = mArea
    }
    
}
