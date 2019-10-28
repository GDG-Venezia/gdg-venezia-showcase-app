//
//  ScreenType.swift
//  iosApp
//
//  Created by Marco Gomiero on 11/08/2019.
//

import UIKit

public enum DeviceScreenType: String {
    
    case iPhone4
    case iPhone5
    case iPhone6
    case iPhone6Plus
    case iPhoneX
    
    static func get() -> DeviceScreenType {
        return UIDevice.screenType
    }
    
    static func initialize() -> DeviceScreenType {
        let screenSize: CGRect = UIScreen.main.bounds
        let screenHeight = screenSize.height
        if screenHeight <= 480 {
            return .iPhone4
        }
        if screenHeight <= 568 {
            return .iPhone5
        }
        if screenHeight <= 667 {
            return .iPhone6
        }
        if screenHeight <= 736 {
            return .iPhone6Plus
        }
        return .iPhoneX // 812
    }
    
    static func getHeight() -> CGFloat {
        return UIScreen.main.bounds.height
    }
    
    static func isOf(screenType type: DeviceScreenType) -> Bool {
        return DeviceScreenType.get() == type
    }
    
    static func isOf(screenTypes types: [DeviceScreenType]) -> Bool {
        for deviceScreenType in types {
            if DeviceScreenType.get() == deviceScreenType {
                return true
            }
        }
        return false
    }
    
}

public extension UIDevice {
    
    private struct AssociatedKeys {
        static var deviceName = "deviceName"
        static var screenType = "DeviceScreenType"
    }
    
    static var screenType: DeviceScreenType {
        if let screenType = UIDevice.currentScreenType {
            return DeviceScreenType(rawValue: screenType)!
        }
        let screenType = DeviceScreenType.initialize()
        UIDevice.currentScreenType = screenType.rawValue
        return screenType
    }
    
    static var currentScreenType: String? {
        get {
            guard let value = objc_getAssociatedObject(self, &AssociatedKeys.screenType) as? String else {
                return nil
            }
            return value
        }
        set(value) {
            objc_setAssociatedObject(self, &AssociatedKeys.screenType, value, .OBJC_ASSOCIATION_RETAIN_NONATOMIC)
        }
    }
}
