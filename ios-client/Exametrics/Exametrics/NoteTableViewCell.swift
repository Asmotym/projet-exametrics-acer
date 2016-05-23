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
    @IBOutlet weak var authorLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var messageLabel: UILabel!
    
    
    // Variables
    static let identifier = "noteCell"
    
    // Fonction permettant la configuration de la cellule
    func configureWithData(author: String, text: String, date: String){
        
        authorLabel.text = author
        messageLabel.text = text
        dateLabel.text = date
        
    }
}
