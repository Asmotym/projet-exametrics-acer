//
//  NoteTableViewCell.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 19/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import UIKit

class NoteTableViewCell: UITableViewCell {

    // Outlets
    @IBOutlet weak var loginLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var messageLabel: UILabel!
    
    
    // Variables
    static let identifier = "noteCell"
    
    // Fonction permettant la configuration de la cellule
    func configureWithData(login: String,message: String,  date: String){
        
        loginLabel.text = login
        messageLabel.text = message
        dateLabel.text = date
        
    }
}
