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
    @IBOutlet weak var inputLogin: UITextField!
    @IBOutlet weak var inputMessage: UITextView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Ajoutez une note"
        
    }

    @IBAction func touchAddButton(sender: AnyObject) {
        
        let newLogin   = inputLogin.text
        let newMessage = inputMessage.text
        let newDate    = NSDate()
        
        let newNote = Note(id: noteList.count + 1, login: newLogin!, message: newMessage, date: newDate, area: mArea)
        
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
