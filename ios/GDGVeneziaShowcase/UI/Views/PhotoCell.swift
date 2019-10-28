//
//  ImageCell.swift
//  iosApp
//
//  Created by Marco Gomiero on 21/08/2019.
//

import Foundation
import UIKit
import SDWebImage
import common

class PhotoCell: UICollectionViewCell {
    
    @IBOutlet weak var container: UIView!
    @IBOutlet weak var imageView: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.container.layer.cornerRadius = 10
        self.imageView.layer.cornerRadius = 10
        
        self.container.translatesAutoresizingMaskIntoConstraints = false
        self.container.backgroundColor = UIColor.white
        
        // add shadow on cell
        backgroundColor = .clear
        container.layer.masksToBounds = false
        container.layer.shadowOpacity = 0.23
        container.layer.shadowRadius = 4
        container.layer.shadowOffset = CGSize(width: 0, height: 0)
        container.layer.shadowColor = UIColor.black.cgColor
    }
    
    func setup(photoModel: PhotoModel) {
        self.imageView.sd_setImage(with: URL(string: photoModel.url))
    }
    
}
