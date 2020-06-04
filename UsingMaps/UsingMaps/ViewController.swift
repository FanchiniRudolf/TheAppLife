//
//  ViewController.swift
//  UsingMaps
//
//  Created by user167967 on 5/27/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit
import CoreLocation
import MapKit


class ViewController: UIViewController {
    
    let arrImages = ["linux", "pac-man", "pokemon"]
    
    @IBOutlet weak var map: MKMapView!
    let gps = CLLocationManager()
    var showingRestaurants = false
    var menu: CPMenuView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        configureMap()
        configureMenu()
    }
    
    func configureMenu(){
        let btnMenu = HomeMenuButton(image:  UIImage(named: "menu")!, size: CGSize(width: 50, height: 50))
        btnMenu.pressedImage = UIImage(named: "close")!
        
        let animator = CPMenuAnimator(commonDuration: 0.5, commonSpringWithDamping: 0.5, commonSpringVelocity: 10)
        
        menu = CPMenuView(parentView: self.view, homeButton: btnMenu, animator: animator, type: .all, isClockWise: true)
        menu.delegate = self
        menu.datasource = self
        menu.setHomeButtonPosition(position: CGPoint(x: self.view.center.x-100, y: self.view.frame.height-100))
    }
    
        
        func switchFood(){
            if showingRestaurants {
                self.map.removeAnnotations(self.map.annotations)
                showingRestaurants = !showingRestaurants
            }else{
                let request = MKLocalSearch.Request()
                request.naturalLanguageQuery = "Restaurant"
                request.region = map.region
                
                let search = MKLocalSearch(request: request)
                
                search.start(completionHandler: {(response, error)in
                    if error != nil{
                        print("Error ocurred in search : \(error!.localizedDescription)")
                    }else if response!.mapItems.count == 0 {
                        print("No matches found")
                    }else{
                        print("Matches found")
                        for item in response!.mapItems {
                            let foodPlace = CLLocationCoordinate2DMake(item.placemark.coordinate.latitude, item.placemark.coordinate.longitude)
                            let pin = MKPointAnnotation()
                            pin.coordinate = foodPlace
                            pin.title = item.name
                            self.map.addAnnotation(pin)
                        }
                    }
                })
                showingRestaurants = !showingRestaurants
        }
        
    }
    
    func configureMap (){
        map.delegate = self
        gps.delegate = self
        
        gps.desiredAccuracy = kCLLocationAccuracyBest
        gps.requestWhenInUseAuthorization()
        
        let centre = CLLocationCoordinate2DMake(19.5953, -99.2276)
        let span = MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01)
        let region = MKCoordinateRegion(center: centre, span: span)
        
        map.region = region
        
        
        
    }
    
    func changeAboutScreen(){
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let secondViewController = storyBoard.instantiateViewController(withIdentifier: "aboutScreen")
        
        self.present(secondViewController, animated: true, completion: nil)
    }

}

extension ViewController: CPMenuViewDelegate, CPMenuViewDataSource{
    func menuView(_ menuView: CPMenuView, didSelectButtonAtIndex index: Int) {
        print("Selecciono \(index)")
        if index == 0{
            switchFood()
        }else if index == 1{
            map.showsTraffic.toggle()
        }else if index == 2{
            changeAboutScreen()
        }
    }
    
    func menuView(_ menuView: CPMenuView, didSelectHomeButtonState state: CPMenuViewState) {
        
    }
    
    func menuViewNumberOfItems() -> Int {
        return arrImages.count
    }
    
    func menuView(_: CPMenuView, buttonAtIndex index: Int) -> SubMenuButton {
        let imgBtn = UIImage(named: arrImages[index])!
        let btnSubMenu = SubMenuButton(image: imgBtn, size: CGSize(width: 60, height: 60))
        btnSubMenu.offset = 20
        btnSubMenu.backgroundColor = UIColor(displayP3Red: 0.5, green: 0.4, blue: 0.7, alpha: 0.5)
        btnSubMenu.layer.cornerRadius = btnSubMenu.frame.height/2
        btnSubMenu.layer.masksToBounds = true
        
        return btnSubMenu
        
    }
    
    
}

extension ViewController: CLLocationManagerDelegate, MKMapViewDelegate {
    	
    func mapView(_ mapView: MKMapView, didUpdate userLocation: MKUserLocation) {
        mapView.setCenter(userLocation.coordinate, animated: true)
        
        let borregos = CLLocationCoordinate2DMake(userLocation.coordinate.latitude, userLocation.coordinate.longitude)
        let pin = MKPointAnnotation()
        pin.coordinate = borregos
        pin.title = "Plaza borregos"
        pin.subtitle = "Tec CEM"
        map.addAnnotation(pin)
    }
    
     func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        switch status {
        case .authorizedWhenInUse:
            gps.startUpdatingLocation()
        case .denied:
            print("NO permit")
        default:
            print("indeterminate")
        }
    }
}
