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
    
    // Outlets
    @IBOutlet weak var inputLogin: UITextField!
    @IBOutlet weak var inputMessage: UITextView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
        
    }

    @IBAction func touchAddButton(sender: AnyObject) {
    }

}
