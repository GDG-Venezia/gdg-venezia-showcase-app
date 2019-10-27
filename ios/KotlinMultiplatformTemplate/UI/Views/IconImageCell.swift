//
//  IconImageCell.swift
//  iosApp
//
//  Created by Marco Gomiero on 15/08/2019.
//

import Foundation
import UIKit
import common

class IconImageCell: UITableViewCell {
    
    @IBOutlet weak var leftImage: UIImageView!
    @IBOutlet weak var labelTitle: UILabel!
    @IBOutlet weak var labelSubtitle: UILabel!
    @IBOutlet weak var container: UIView!
    
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
        
        
        self.labelTitle.font = Fonts.get(.bold, size: Fonts.Sizes.normal)
        self.labelSubtitle.font = Fonts.get(.regular, size: Fonts.Sizes.normal)
    }
    
    func setupTeamCell(member: TeamMemberModel) {
        self.labelTitle.text = "\(member.firstname) \(member.lastname)"
        self.labelSubtitle.text = member.shortDescription
        
        
        self.leftImage.image = UIImage(named: "mg")
        self.leftImage.layer.cornerRadius = self.leftImage.frame.size.width/2
        self.leftImage.clipsToBounds = true

    }
    
    func setupSocialCell() {
        self.labelTitle.text = "Social Name"
        self.labelSubtitle.isHidden = true
        
        self.leftImage.image = UIImage(named: "")
    }
    
    func makeRoundedImage(image: UIImage, radius: Float) -> UIImage? {
        let imageLayer = CALayer()
        imageLayer.frame = CGRect(x: 0, y: 0, width: image.size.width, height: image.size.height)
        imageLayer.contents = image.cgImage
        
        imageLayer.masksToBounds = true
        imageLayer.cornerRadius = CGFloat(radius)
        
        UIGraphicsBeginImageContext(image.size)
        imageLayer.render(in: UIGraphicsGetCurrentContext()!)
        let roundedImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return roundedImage ?? nil
    }
    
}
