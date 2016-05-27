//
//  AddNoteViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 20/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import UIKit
import RealmSwift


class AddNoteViewController: UIViewController {

    // Variables
    var myArea : Area!
    let noteCont  = NoteController()
    let realm = try! Realm()
    
    
    // Outlets
    @IBOutlet weak var inputMessage: UITextView!
    @IBOutlet weak var inputAuthor: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Ajoutez une note"
        
        inputAuthor.layer.borderColor = UIColor.blackColor().CGColor;
        inputAuthor.layer.borderColor = UIColor(red: 0.5, green: 0.5, blue:0, alpha: 1.0 ).CGColor;
        inputAuthor.layer.borderWidth = 1.0;
        inputAuthor.layer.cornerRadius = 5.0;
        
        inputMessage.layer.borderColor = UIColor.blackColor().CGColor;
        inputMessage.layer.borderColor = UIColor(red: 0.5, green: 0.5, blue:0, alpha: 1.0 ).CGColor;
        inputMessage.layer.borderWidth = 1.0;
        inputMessage.layer.cornerRadius = 5.0;
        
        // Reconnait un toucher
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(AddNoteViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
        
    }

    func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }
    
    @IBAction func touchAddButton(sender: AnyObject) {
        
        let newId      = ""
        let newAuthor  = inputAuthor.text
        let newText    = inputMessage.text
        
        let newDate    = NSDate()
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd hh:mm:ss"
        let newStringDate = dateFormatter.stringFromDate(newDate)
    
        let newIdArea = myArea.getId()
        
        let newNote = Note()
        newNote.setNote(newId, author: newAuthor!, text: newText, date: newStringDate, idArea: newIdArea)
        
        try! realm.write {
            realm.add(newNote)
        }
        
        noteCont.uploadNote(newNote)
        
        navigationController?.popViewControllerAnimated(true)
        
    }
}
