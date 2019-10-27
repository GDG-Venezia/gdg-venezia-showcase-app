//
//  EventViewController.swift
//  iosApp
//
//  Created by Marco Gomiero on 10/08/2019.
//

import UIKit
import common
import MaterialComponents.MaterialSnackbar

class EventViewController: UIViewController, EventListView {
    
    @IBOutlet weak var labelToolbar: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    private lazy var presenter = ServiceLocator.init().eventListPresenter
    
    private var events = [EventModel]()
    private var pastEvents = [EventModel]()
    private var futureEvents = [EventModel]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        presenter.attachView(view: self)
        
        self.view.backgroundColor = Colors.backgroundColor
        
        self.labelToolbar.text = "GDG Venezia"
        self.labelToolbar.font = Fonts.get(.regular, size: Fonts.Sizes.xLarge)
        
        let eventCell = UINib(nibName: "IconTextCell", bundle: nil)
        self.tableView.register(eventCell, forCellReuseIdentifier: "EventCell")
        
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
    
    func renderEventList(eventList: EventListModel) {
        self.futureEvents = eventList.futureEvents
        self.pastEvents = eventList.pastEvents
        self.tableView.reloadData()
    }
}

extension EventViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "EventCell", for: indexPath) as! IconTextCell
        cell.backgroundColor = UIColor.clear
        cell.selectionStyle = .none
        
        var event: EventModel? = nil
        switch indexPath.section {
        case 0:
            event = self.futureEvents[indexPath.row]
        case 1:
            event = self.pastEvents[indexPath.row]
        default:
            break
        }
        
        if event != nil {
            cell.setup(title: event!.title, subtitle: "Subtitle", event: event!)
        }
        
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
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0:
            return futureEvents.count
        case 1:
            return pastEvents.count
        default:
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        switch section {
        case 0:
            return NSLocalizedString("EVENTS_next", comment: "")
        case 1:
            return NSLocalizedString("EVENTS_past", comment: "")
        default:
            return nil
        }
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


