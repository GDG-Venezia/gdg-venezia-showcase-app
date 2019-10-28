//
//  PhotoViewController.swift
//  iosApp
//
//  Created by Marco Gomiero on 10/08/2019.
//

import UIKit
import common
import MaterialComponents.MaterialSnackbar

class PhotoViewController: UIViewController, PhotoView, CustomLayoutDelegate  {
    
    @IBOutlet weak var labelToolbar: UILabel!
    @IBOutlet weak var collectionView: UICollectionView!
    
    private lazy var presenter = ServiceLocator.init().photoPresenter
    
    private var photos = [PhotoModel]()
    
    private let itemsPerRow: CGFloat = 2
    
    public var customCollectionViewLayout: PhotoCollectionViewLayout? {
        get {
            return self.collectionView.collectionViewLayout as? PhotoCollectionViewLayout
        }
        set {
            if newValue != nil {
                self.collectionView?.collectionViewLayout = newValue!
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        presenter.attachView(view: self)
        
        self.view.backgroundColor = Colors.backgroundColor
        
        self.labelToolbar.text = "GDG Venezia"
        self.labelToolbar.font = Fonts.get(.regular, size: Fonts.Sizes.xLarge)
        
        let eventCell = UINib(nibName: "PhotoCell", bundle: nil)
        self.collectionView.register(eventCell, forCellWithReuseIdentifier: "PhotoCell")
        
        self.collectionView.dataSource = self
        self.collectionView.delegate = self
        self.collectionView.backgroundColor = Colors.backgroundColor
        
        
        self.customCollectionViewLayout?.delegate = self
        self.customCollectionViewLayout?.numberOfColumns = 2
        
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        heightForItemAt
        indexPath: IndexPath,
                        with width: CGFloat) -> CGFloat {
        
        let heightSizes = [180,250]
        let number = Int.random(in: 0 ..< 2)
        return CGFloat(heightSizes[number])
    }
    
    func renderError(errorMessage: String) {
        let message = MDCSnackbarMessage()
        message.text = errorMessage
        MDCSnackbarManager.show(message)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        presenter.detachView()
    }
    
    func renderLoading(visible: Bool) {
        if visible {
            view.showLoader()
        } else {
            view.hideLoader()
        }
    }
    
    func renderPhotoList(photoList: [PhotoModel]) {
        self.photos = photoList
        self.collectionView.reloadData()
    }
}

extension PhotoViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.photos.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "PhotoCell", for: indexPath as IndexPath) as! PhotoCell
        cell.setup(photoModel: photos[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        //2
        let paddingSpace = 1 * (itemsPerRow + 1)
        let availableWidth = view.frame.width - paddingSpace
        let widthPerItem = availableWidth / itemsPerRow
        
        return CGSize(width: widthPerItem, height: widthPerItem)
    }
}
