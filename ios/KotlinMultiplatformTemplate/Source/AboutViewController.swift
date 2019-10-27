//
//  AboutViewController.swift
//  iosApp
//
//  Created by Marco Gomiero on 10/08/2019.
//

import UIKit
import common
import MaterialComponents.MaterialSnackbar

class AboutViewController: UIViewController, TeamView {

    @IBOutlet weak var labelToolbar: UILabel!
    @IBOutlet weak var aboutContainer: UIView!
    @IBOutlet weak var labelAbout: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    private var teamList = [TeamMemberModel]()
    
    private lazy var presenter = ServiceLocator.init().teamPresenter

    
    override func viewDidLoad() {
        super.viewDidLoad()

        presenter.attachView(view: self)
        
        self.view.backgroundColor = Colors.backgroundColor
        
        self.labelToolbar.text = "GDG Venezia"
        self.labelToolbar.font = Fonts.get(.regular, size: Fonts.Sizes.xLarge)
        
        self.setupAboutCard()
        self.setupTableView()
    }
    
    private func setupAboutCard() {

        // TODO: look for a better description
        self.labelAbout.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu ...."
        
        self.labelAbout.font = Fonts.get(.regular, size: Fonts.Sizes.normal)
        
        self.aboutContainer.layer.cornerRadius = 10
        self.aboutContainer.translatesAutoresizingMaskIntoConstraints = false
        self.aboutContainer.backgroundColor = UIColor.white
        
        // add shadow on cell
        aboutContainer.layer.masksToBounds = false
        aboutContainer.layer.shadowOpacity = 0.23
        aboutContainer.layer.shadowRadius = 4
        aboutContainer.layer.shadowOffset = CGSize(width: 0, height: 0)
        aboutContainer.layer.shadowColor = UIColor.black.cgColor
        

    }
    
    private func setupTableView() {
        let eventCell = UINib(nibName: "IconImageCell", bundle: nil)
        self.tableView.register(eventCell, forCellReuseIdentifier: "StaffCell")
        
        self.tableView.dataSource = self
        self.tableView.delegate = self
        self.tableView.backgroundColor = Colors.backgroundColor
        self.tableView.translatesAutoresizingMaskIntoConstraints = false
        self.tableView.separatorStyle = .none
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        presenter.detachView()
    }
    
    func renderError(errorMessage: String) {
        print(errorMessage)
        let message = MDCSnackbarMessage()
        message.text = errorMessage
        MDCSnackbarManager.show(message)
    }
    
    func renderLoading(visible: Bool) {
        if visible {
            view.showLoader()
        } else {
            view.hideLoader()
        }
    }
    
    func renderTeam(team: [TeamMemberModel]) {
        self.teamList = team
        self.tableView.reloadData()
    }
}

extension AboutViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "StaffCell", for: indexPath) as! IconImageCell
        cell.backgroundColor = UIColor.clear
        cell.selectionStyle = .none
        
        cell.setupTeamCell(member: self.teamList[indexPath.row])

        return cell
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 120
    }
    
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        // Cell shadow stuff
        cell.contentView.layer.masksToBounds = true
        let radius = cell.contentView.layer.cornerRadius
        cell.layer.shadowPath = UIBezierPath(roundedRect: cell.bounds, cornerRadius: radius).cgPath
    }
    
    // Header stuff
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return teamList.count
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return NSLocalizedString("ABOUT_organizers", comment: "")
    }
    
    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        let headerView = (view as! UITableViewHeaderFooterView)
        headerView.backgroundView?.backgroundColor = UIColor.clear
        headerView.textLabel?.font = Fonts.get(.regular, size: Fonts.Sizes.small)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("item selected")
    }
}
