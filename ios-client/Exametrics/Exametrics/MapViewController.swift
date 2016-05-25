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
    
    // Outlets
    @IBOutlet weak var map: MKMapView!

    
    override func viewDidLoad() {
        super.viewDidLoad()

        if (CLLocationManager.locationServicesEnabled())
        {
            locationManager = CLLocationManager()
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyBest
            locationManager.requestAlwaysAuthorization()
            locationManager.startUpdatingLocation()
        }
        
        var currentLocation = CLLocation!()
        currentLocation = locationManager.location
            
        let initialLocation = CLLocation(latitude: 41, longitude: 1)
        //let initialLocation = CLLocation(latitude: currentLocation.coordinate.latitude, longitude: currentLocation.coordinate.longitude)
        centerMapOnLocation(initialLocation)
        
        if(pointList.count == 0) {
            let point1 = Point(id: "1",longitude: 41, latitude: 1, idArea: "1")
            let point2 = Point(id: "1",longitude: 42, latitude: 1, idArea: "1")
            let point3 = Point(id: "1",longitude: 41.5, latitude: 2, idArea: "1")
            let point4 = Point(id: "1",longitude: 41, latitude: 2, idArea: "1")
            pointList.append(point1)
            pointList.append(point2)
            pointList.append(point3)
        }
        
        
        addBoundry()
    }

    func addBoundry()
    {
        var pointsCLLC = [CLLocationCoordinate2D]()
        
        
        for point in pointList {
            let locationCoor = CLLocationCoordinate2DMake(point.getLongitude(), point.getLatitude())
            pointsCLLC.append(locationCoor)
        }

        let polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)
        
        map.addOverlay(polygon)
    }
    
    
    let regionRadius: CLLocationDistance = 10000
    func centerMapOnLocation(location: CLLocation) {
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate,
                                                                  regionRadius * 2.0, regionRadius * 2.0)
        map.setRegion(coordinateRegion, animated: true)
    }
    
    
    func mapView(mapView: MKMapView!, rendererForOverlay overlay: MKOverlay!) -> MKOverlayRenderer! {
        if overlay is MKPolygon {
            let polygonView = MKPolygonRenderer(overlay: overlay)
            polygonView.strokeColor = UIColor.magentaColor()
            
            return polygonView
        }
        
        return nil
    }
    
    func locationManager(manager: CLLocationManager, didUpdateLocations locations: [CLLocation])
    {
        
        let location = locations.last! as CLLocation
        
        let center = CLLocationCoordinate2D(latitude: location.coordinate.latitude, longitude: location.coordinate.longitude)
        let region = MKCoordinateRegion(center: center, span: MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01))
        
        self.map.setRegion(region, animated: true)
    }

    
    
    
}
