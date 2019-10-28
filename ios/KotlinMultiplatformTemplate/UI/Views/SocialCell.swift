//
//  SocialCell.swift
//  iosApp
//
//  Created by Marco Gomiero on 21/08/2019.
//

import UIKit
import common

class SocialCell: UITableViewCell {
    
    @IBOutlet weak var container: UIView!
    @IBOutlet weak var imageVIew: UIImageView!
    @IBOutlet weak var labelTitle: UILabel!
    
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
    }
    
    func setup(socialModel: SocialLinkModel) {
        self.labelTitle.text = socialModel.title
        if let image = getImage(socialCode: socialModel.code) {
            self.imageVIew.image = image
        }
    }
    
    private func getImage(socialCode: String) -> UIImage? {
        switch socialCode {
        case "facebook":
            return UIImage(named: "facebook")
        case "twitter":
            return UIImage(named: "twitter")
        case "youtube":
            return UIImage(named: "youtube")
        case "meetup":
            return UIImage(named: "meetup")
        case "instagram":
            return UIImage(named: "instagram")
        case "github":
            return UIImage(named: "github")
        case "telegram":
            return UIImage(named: "telegram")
        case "mail":
            return UIImage(named: "mail")
        default:
            break
        }
        return nil
    }
}
