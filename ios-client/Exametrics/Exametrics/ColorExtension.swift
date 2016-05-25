//
//  ColorExtension.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 25/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import UIKit

extension String {
    
    /**
     Returns the float value of a string
     */
    var floatValue: Float {
        return (self as NSString).floatValue
    }
    
    /**
     Subscript to allow for quick String substrings ["Hello"][0...1] = "He"
     */
    subscript (r: Range<Int>) -> String {
        get {
            let start = self.startIndex.advancedBy(r.startIndex)
            let end = self.startIndex.advancedBy(r.endIndex - 1)
            return self.substringWithRange(start..<end)
        }
    }
}

extension UIColor {
    
    convenience init(var hex: String) {
        let hexLength = hex.characters.count
        if !(hexLength == 10) {
            // A hex must be either 7 or 9 characters (#RRGGBBAA)
            print("improper call to 'colorFromHex', hex length must be 10 (GGRRBBAA)")
            self.init(white: 0, alpha: 1)
            return
        }
        
        if hexLength == 10 {
            // Note: this uses String subscripts as given below
            hex = hex[4...10]
        }
        
        // Establishing the rgb color
        var rgb: UInt32 = 0
        let s: NSScanner = NSScanner(string: hex)
        // Scanning the int into the rgb colors
        s.scanHexInt(&rgb)
        
        // Creating the UIColor from hex int
        self.init(
            red: CGFloat((rgb & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgb & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgb & 0x0000FF) / 255.0,
            alpha: CGFloat(50)
        )
    }
}