//
//  ContactsViewController.swift
//  iosApp
//
//  Created by Marco Gomiero on 10/08/2019.
//

import UIKit
import common
import MaterialComponents.MaterialSnackbar

class ContactsViewController: UIViewController, SocialView {

    @IBOutlet weak var labelToolbar: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    private lazy var presenter = ServiceLocator.init().socialPresenter

    private var socialList = [SocialLinkModel]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        presenter.attachView(view: self)
        
        self.view.backgroundColor = Colors.backgroundColor
        
        self.labelToolbar.text = "GDG Venezia"
        self.labelToolbar.font = Fonts.get(.regular, size: Fonts.Sizes.xLarge)
        
        let eventCell = UINib(nibName: "SocialCell", bundle: nil)
        self.tableView.register(eventCell, forCellReuseIdentifier: "SocialCell")
        
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
    
    func renderSocialLinkList(photoList: [SocialLinkModel]) {
        self.socialList = photoList
        self.tableView.reloadData()
    }
}

extension ContactsViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "SocialCell", for: indexPath) as! SocialCell
        cell.backgroundColor = UIColor.clear
        cell.selectionStyle = .none
        
        cell.setup(socialModel: self.socialList[indexPath.row])
        
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
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.socialList.count
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let url = URL(string: self.socialList[indexPath.row].url) else { return }
        UIApplication.shared.open(url)
    }
}
