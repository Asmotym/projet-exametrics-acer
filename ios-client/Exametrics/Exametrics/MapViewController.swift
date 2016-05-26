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

class MapViewController: UIViewController, CLLocationManagerDelegate {

    // Variables
    var locationManager: CLLocationManager!
    var pointList = [Point]()
    var myArea : Area!
    let regionRadius: CLLocationDistance = 10000
    
    // Outlets
    @IBOutlet weak var map: MKMapView!

    
    override func viewDidLoad() {
        super.viewDidLoad()

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
        
        let initialLocation = CLLocation(latitude: currentLocation.coordinate.latitude, longitude: currentLocation.coordinate.longitude)
        centerMapOnLocation(initialLocation)
        
        let newArea = Area()
        newArea.setArea("1", name: "LaZone", color: "Ox7c880088")
        
        addBoundry()
    }

    // Fonction permettant d'afficher la zone actuelle
    func addBoundry()
    {
        var pointsCLLC = [CLLocationCoordinate2D]()
        
        for point in pointList {
            let locationCoor = CLLocationCoordinate2DMake(point.getLongitude(), point.getLatitude())
            pointsCLLC.append(locationCoor)
        }

        let polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)
        
        //let point = CGPoint(x: 41.5, y: 1.5)
        
        
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
        let region = MKCoordinateRegion(center: center, span: MKCoordinateSpan(latitudeDelta: 0.005, longitudeDelta: 0.005))
        
        self.map.setRegion(region, animated: true)
    }

    
    
    
}
