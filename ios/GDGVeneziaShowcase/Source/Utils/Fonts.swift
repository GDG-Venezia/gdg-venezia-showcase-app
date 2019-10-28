//
//  File.swift
//  iosApp
//
//  Created by Marco Gomiero on 11/08/2019.
//

import Foundation
import UIKit

enum FontType {
    case bold
    case boldItalic
    case italic
    case regular
    
    var fontTypeName: String {
        switch self {
        case .boldItalic:
            return "ProductSans-BoldItalic"
        case .bold:
            return "ProductSans-Bold"
        case .italic:
            return "ProductSans-Italic"
        case .regular:
            return "ProductSans-Regular"
        }
    }
}

struct Fonts {
    
    static func get(_ type: FontType, size: Int) -> UIFont {
        return UIFont(name: type.fontTypeName, size: CGFloat(size))!
    }
    
    // MARK: - Internal struct with font sizes -
    
    struct Sizes {
        
        static var xSmall: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 8
            case .iPhone5:
                return 10
            case .iPhone6:
                return 12
            case .iPhone6Plus, .iPhoneX:
                return 12
            }
        }
        
        static var small: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 10
            case .iPhone5:
                return 13
            case .iPhone6:
                return 14
            case .iPhone6Plus, .iPhoneX:
                return 15
            }
        }
        
        static var normal: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 12
            case .iPhone5:
                return 15
            case .iPhone6:
                return 16
            case .iPhone6Plus, .iPhoneX:
                return 17
            }
        }
        
        static var medium: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 16
            case .iPhone5:
                return 18
            case .iPhone6:
                return 20
            case .iPhone6Plus, .iPhoneX:
                return 22
            }
        }
        
        static var large: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 20
            case .iPhone5:
                return 22
            case .iPhone6:
                return 24
            case .iPhone6Plus, .iPhoneX:
                return 28
            }
        }
        
        static var xLarge: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 24
            case .iPhone5:
                return 26
            case .iPhone6:
                return 28
            case .iPhone6Plus, .iPhoneX:
                return 30
            }
        }
        
        static var huge: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 28
            case .iPhone5:
                return 30
            case .iPhone6:
                return 32
            case .iPhone6Plus, .iPhoneX:
                return 34
            }
        }
        
        static var giant: Int {
            switch DeviceScreenType.get() {
            case .iPhone4:
                return 40
            case .iPhone5:
                return 45
            case .iPhone6:
                return 48
            case .iPhone6Plus, .iPhoneX:
                return 51
            }
        }
        
    }
    
}
