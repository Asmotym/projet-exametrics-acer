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
        
        if(pointList.count == 0) {
            let point1 = Point(id: "1",longitude: 2.84692917, latitude: 42.67417863, idArea: "87")
            let point2 = Point(id: "2",longitude: 2.84581672, latitude: 42.67432826, idArea: "87")
            let point3 = Point(id: "3",longitude: 2.84641989, latitude: 42.67505938, idArea: "87")
            let point4 = Point(id: "4",longitude: 2.84797993, latitude: 42.67518164, idArea: "87")
            let point5 = Point(id: "5",longitude: 2.84828033, latitude: 42.67409975, idArea: "87")
            pointList.append(point1)
            pointList.append(point2)
            pointList.append(point3)
            
        }
        
        myArea = Area(id: "1", name: "LaZone", color: "Ox7c880088")
        
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
