//
//  TextCell.swift
//  iosApp
//
//  Created by Marco Gomiero on 11/08/2019.
//

import Foundation
import UIKit
import common

class IconTextCell: UITableViewCell {
    
    
    @IBOutlet weak var labelTitle: UILabel!
    @IBOutlet weak var labelSubtitle: UILabel!
    @IBOutlet weak var container: UIView!
    @IBOutlet weak var leftView: UIView!
    @IBOutlet weak var labelMonth: UILabel!
    @IBOutlet weak var labelDay: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.container.layer.cornerRadius = 10
        self.container.translatesAutoresizingMaskIntoConstraints = false
        self.container.backgroundColor = UIColor.white
        
        // add shadow on cell
        backgroundColor = .clear 
        container.layer.masksToBounds = false
        container.layer.shadowOpacity = 0.23
        container.layer.shadowRadius = 4
        container.layer.shadowOffset = CGSize(width: 0, height: 0)
        container.layer.shadowColor = UIColor.black.cgColor
        
        self.leftView.layer.cornerRadius = self.leftView.frame.size.width/2
        self.leftView.clipsToBounds = true
        self.leftView.backgroundColor = Colors.backgroundColor
        
        self.labelTitle.font = Fonts.get(.bold, size: Fonts.Sizes.normal)
        self.labelSubtitle.font = Fonts.get(.regular, size: Fonts.Sizes.normal)
        self.labelDay.font = Fonts.get(.regular, size: Fonts.Sizes.small)
        self.labelMonth.font = Fonts.get(.regular, size: Fonts.Sizes.small)
        
    }
    
    func setup(event: EventModel?) {
        if let singleEvent = event {
            self.labelTitle.text = singleEvent.title
            self.labelSubtitle.text = singleEvent.eventDescription.html2String
            self.labelMonth.text = singleEvent.monthShort
            self.labelDay.text = String(singleEvent.day)
        }
    }
}
