import UIKit
import MaterialComponents.MDCBottomNavigationBar
import MaterialComponents.MaterialBottomNavigation_ColorThemer
import MaterialComponents.MaterialColorScheme

class MainViewController: UITabBarController, MDCBottomNavigationBarDelegate{
    
    @objc var colorScheme = MDCSemanticColorScheme()
    let bottomNavBar = MDCBottomNavigationBar()
    
    init() {
        super.init(nibName: nil, bundle: nil)
        setupBottomNavigation()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupBottomNavigation()
    }
    
    // Bottom Bar Customization
    func setupBottomNavigation() {
        
        view.backgroundColor = .lightGray
        view.addSubview(bottomNavBar)
        // Always show bottom navigation bar item titles.
        bottomNavBar.titleVisibility = .always
        
        // Cluster and center the bottom navigation bar items.
        bottomNavBar.alignment = .centered
        
        // Add items to the bottom navigation bar.
        let tabBarItem1 = UITabBarItem( title: NSLocalizedString("BOTTOM_BAR_events", comment: ""), image: UIImage(named: "icon_bar_events"), tag: 0 )
        let tabBarItem2 = UITabBarItem( title: NSLocalizedString("BOTTOM_BAR_about", comment: ""), image: UIImage(named: "icon_bar_about"), tag: 1 )
        let tabBarItem3 = UITabBarItem( title: NSLocalizedString("BOTTOM_BAR_photo", comment: ""), image: UIImage(named: "icon_bar_photo"), tag: 2 )
        let tabBarItem4 = UITabBarItem( title: NSLocalizedString("BOTTOM_BAR_contact", comment: ""), image: UIImage(named: "icon_bar_contacts"), tag: 3 )
        
        bottomNavBar.items = [ tabBarItem1, tabBarItem2, tabBarItem3, tabBarItem4 ]
        
        // Select a bottom navigation bar item.
        bottomNavBar.selectedItem = tabBarItem1;
        bottomNavBar.delegate = self
        
        colorScheme.backgroundColor = .white
        colorScheme.primaryColor = UIColor(red: 0.243, green: 0.510, blue: 0.969, alpha: 1.000)
        MDCBottomNavigationBarColorThemer.applySemanticColorScheme(colorScheme,
                                                                   toBottomNavigation: bottomNavBar)
    }
    
    func bottomNavigationBar(_ bottomNavigationBar: MDCBottomNavigationBar, didSelect item: UITabBarItem) {
        self.selectedIndex = item.tag
    }
    
    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        layoutBottomNavBar()
    }
    
    func layoutBottomNavBar() {
        let size = bottomNavBar.sizeThatFits(view.bounds.size)
        var bottomNavBarFrame = CGRect(x: 0,
                                       y: view.bounds.height - size.height,
                                       width: size.width,
                                       height: size.height)
        if #available(iOS 11.0, *) {
            bottomNavBarFrame.size.height += view.safeAreaInsets.bottom
            bottomNavBarFrame.origin.y -= view.safeAreaInsets.bottom
        }
        bottomNavBar.frame = bottomNavBarFrame
    }
}
