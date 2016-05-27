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
    var areaList : Results<(Area)>!
    var pointList : Results<(Point)>!
    var myArea : Area!
    
    let areaCont  = AreaController()
    let pointCont = PointController()
    
    let regionRadius: CLLocationDistance = 10000
    var realm = try! Realm()
    let dropPin = MKPointAnnotation()
    var currentLocation = CLLocation!()
    var polygon = MKPolygon()
    
    // Outlets
    @IBOutlet weak var map: MKMapView!

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        areaList  = realm.objects(Area)
        pointList = realm.objects(Point)
        
        areaCont.getAreas()
        pointCont.getPoints()
        
        getLocalisation()
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
        
        currentLocation = locationManager.location
        
        for area in areaList {
            
            print(area.getId())
            var currentPointList = [Point]()
            for point in pointList {
                if(point.getIdArea() == area.getId()){
                    currentPointList.append(point)
                    print("\(point.getLatitude()) & \(point.getLongitude())")
                }
            }
            checkLocationWithPoints(currentPointList, areaId: area.getId())
            print("CHECK")
        }

        
        // Drop a pin
        
        dropPin.coordinate = CLLocationCoordinate2DMake(currentLocation.coordinate.latitude, currentLocation.coordinate.longitude)
        dropPin.title = "You"
        map.addAnnotation(dropPin)
        
        let initialLocation = CLLocation(latitude: currentLocation.coordinate.latitude, longitude: currentLocation.coordinate.longitude)
        centerMapOnLocation(initialLocation)
        
        // Dessine la zone
        addBoundry()
    }
    
    
    
    
    // Localisation de la zone actuelle
    internal func checkLocationWithPoints(pointList: [Point], areaId: String){
        
        var pointsCLLC = [CLLocationCoordinate2D]()
        
        for point in pointList {
            let locationCoor = CLLocationCoordinate2DMake(point.getLatitude(), point.getLongitude())
            pointsCLLC.append(locationCoor)
        }
        
        let point   = MKMapPoint(x: currentLocation.coordinate.longitude , y: currentLocation.coordinate.latitude)
        let polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)
        
        // Si le point est dans le polygon, on vérifie si la Zone a changée
        if (isPointInPolygon(point, polygon: polygon)){
            
            self.myArea = realm.objects(Area).filter("_id == '\(areaId)'").first
            self.pointList = realm.objects(Point).filter("_idArea == '\(areaId)'")
            self.title = myArea.getName()
            self.navigationController!.navigationBar.barTintColor = UIColor(hex: myArea.getColor())
        
        }
        
    }
    
    // Vérifie si un Point est dans un Polygon
    func isPointInPolygon(point : MKMapPoint, polygon : MKPolygon) -> Bool{
        
        
        let polygonRenderer = MKPolygonRenderer(polygon: polygon)
        let currentMapPoint: MKMapPoint = MKMapPointForCoordinate((locationManager.location?.coordinate)!)
        let polygonViewPoint: CGPoint = polygonRenderer.pointForMapPoint(currentMapPoint)
        
        if CGPathContainsPoint(polygonRenderer.path, nil, polygonViewPoint, true) {
            print("Your location was inside your polygon.")
            return true
        }
        
        
        return false
    }

    
    
    
    
    
    // Fonction permettant d'afficher la zone actuelle
    func addBoundry()
    {
        
        var pointsCLLC = [CLLocationCoordinate2D]()
        
        for point in pointList {
            let locationCoor = CLLocationCoordinate2DMake(point.getLatitude(), point.getLongitude())
            pointsCLLC.append(locationCoor)
        }

        polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)
        
        
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
        let region = MKCoordinateRegion(center: center, span: MKCoordinateSpan(latitudeDelta: 0.002, longitudeDelta: 0.002))
        
        dropPin.coordinate = CLLocationCoordinate2DMake(location.coordinate.latitude, location.coordinate.longitude)
        dropPin.title = "You"
        map.addAnnotation(dropPin)
        
        self.map.setRegion(region, animated: true)
    }

}
