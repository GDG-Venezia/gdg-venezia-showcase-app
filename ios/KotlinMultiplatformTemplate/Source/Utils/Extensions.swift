//
//  Extensions.swift
//  iosApp
//
//  Created by Marco Gomiero on 11/08/2019.
//

import Foundation
import UIKit

import MBProgressHUD

extension UIColor {
    public convenience init?(hex: String) {
        let r, g, b, a: CGFloat
        
        if hex.hasPrefix("#") {
            let start = hex.index(hex.startIndex, offsetBy: 1)
            let hexColor = String(hex[start...])
            
            if hexColor.count == 8 {
                let scanner = Scanner(string: hexColor)
                var hexNumber: UInt64 = 0
                
                if scanner.scanHexInt64(&hexNumber) {
                    r = CGFloat((hexNumber & 0xff000000) >> 24) / 255
                    g = CGFloat((hexNumber & 0x00ff0000) >> 16) / 255
                    b = CGFloat((hexNumber & 0x0000ff00) >> 8) / 255
                    a = CGFloat(hexNumber & 0x000000ff) / 255
                    
                    self.init(red: r, green: g, blue: b, alpha: a)
                    return
                }
            }
        }
        
        return nil
    }
}

extension UIView {
    var loader: MBProgressHUD? {
        get {
            return objc_getAssociatedObject(self, "loader") as? MBProgressHUD
        }
        set(newValue) {
            objc_setAssociatedObject(self, "loader", newValue, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN)
        }
    }
    
    /// Display a loader in the view
    func showLoader() {
        if loader == nil {
            loader = MBProgressHUD.showAdded(to: self, animated:true)
            print("Loader added successfully")
        } else {
            print("WARNING: You are trying to show a loader while another is attached on view.")
        }
    }
    
    /// Hide loader in view if present
    func hideLoader() {
        let removed = MBProgressHUD.hide(for: self, animated:true)
        print("Loader remove successfully? -> \(removed)")
        loader = nil
    }
    
}
