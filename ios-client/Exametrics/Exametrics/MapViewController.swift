//
//  MapViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 20/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

import UIKit
import CoreLocation
import MapKit
import RealmSwift

class MapViewController: UIViewController, CLLocationManagerDelegate {

    // Variables
    var locationManager: CLLocationManager!
    var pointList : Results<(Point)>!
    var myArea : Area!
    let regionRadius: CLLocationDistance = 10000
    var realm = try! Realm()
    let dropPin = MKPointAnnotation()
    
    // Outlets
    @IBOutlet weak var map: MKMapView!

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        myArea = realm.objects(Area).filter("_id == '129'").first
        self.title = myArea.getName()
        self.navigationController!.navigationBar.barTintColor = UIColor(hex: myArea.getColor())
        
        pointList = realm.objects(Point).filter("_idArea == '\(myArea.getId())'")
        
        
        
        getLocalisation()
        addBoundry()
    }

    func getLocalisation(){
        //Mise en place des paramètres de la carte
        if (CLLocationManager.locationServicesEnabled())
        {
            locationManager = CLLocationManager()
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyBest
            locationManager.requestAlwaysAuthorization()
            locationManager.startUpdatingLocation()
        }
        
        // Localisation du téléphone
        var currentLocation = CLLocation!()
        currentLocation = locationManager.location
        
        
        
        // Drop a pin
        
        dropPin.coordinate = CLLocationCoordinate2DMake(currentLocation.coordinate.latitude, currentLocation.coordinate.longitude)
        dropPin.title = "You"
        map.addAnnotation(dropPin)
        
        let initialLocation = CLLocation(latitude: currentLocation.coordinate.latitude, longitude: currentLocation.coordinate.longitude)
        centerMapOnLocation(initialLocation)
    }
    
    // Fonction permettant d'afficher la zone actuelle
    func addBoundry()
    {
        var pointsCLLC = [CLLocationCoordinate2D]()
        
        for point in pointList {
            let locationCoor = CLLocationCoordinate2DMake(point.getLatitude(), point.getLongitude())
            pointsCLLC.append(locationCoor)
        }

        let polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)
        
        map.addOverlay(polygon)
    }
    
    
    
    func centerMapOnLocation(location: CLLocation) {
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate,
                                                                  regionRadius * 2.0, regionRadius * 2.0)
        map.setRegion(coordinateRegion, animated: true)
    }
    
    
    func mapView(mapView: MKMapView!, rendererForOverlay overlay: MKOverlay!) -> MKOverlayRenderer! {
        if overlay is MKPolygon {
            let polygonView = MKPolygonRenderer(overlay: overlay)
            polygonView.strokeColor = UIColor(hex: myArea.getColor())
            
            return polygonView
        }
        
        return nil
    }
    
    // Permet de recentrer la carte sur le téléphone
    func locationManager(manager: CLLocationManager, didUpdateLocations locations: [CLLocation])
    {
        
        let location = locations.last! as CLLocation
        let center = CLLocationCoordinate2D(latitude: location.coordinate.latitude, longitude: location.coordinate.longitude)
        let region = MKCoordinateRegion(center: center, span: MKCoordinateSpan(latitudeDelta: 0.001, longitudeDelta: 0.001))
        
        dropPin.coordinate = CLLocationCoordinate2DMake(location.coordinate.latitude, location.coordinate.longitude)
        dropPin.title = "You"
        map.addAnnotation(dropPin)
        
        self.map.setRegion(region, animated: true)
    }

}
