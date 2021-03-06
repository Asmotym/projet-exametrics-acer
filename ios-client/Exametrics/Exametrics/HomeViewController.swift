//
//  HomeViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 18/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

// Clef GoogleAPI IOS : AIzaSyBrBX4Q_tXvnCezexSX0c61SjvGICiJ_0w

import UIKit
import RealmSwift
import CoreLocation
import MapKit

class HomeViewController: UIViewController, UITableViewDataSource,CLLocationManagerDelegate {

    // Variables
    var myArea : Area!
    var areaList : Results<(Area)>!
    var pointList : Results<(Point)>!
    var noteList : Results<(Note)>!
    
    var realm = try! Realm()
    
    let areaCont  = AreaController()
    let pointCont = PointController()
    let noteCont  = NoteController()
    
    var locationManager: CLLocationManager!
    var myLatitude : CLLocationDegrees!
    var myLongitude : CLLocationDegrees!
    
    // Outlets
    @IBOutlet weak var noteTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Supprime tous les objets de Realm
        try! realm.write {
            realm.deleteAll()
        }
        
        myArea = Area()
        myArea.setArea("none", name: "none", color: "Ox7cFFFFFF")
        
        areaCont.getAreas()
        pointCont.getPoints()
        noteCont.getNotes()
        
        areaList  = realm.objects(Area)
        pointList = realm.objects(Point)
        noteList  = realm.objects(Note)
        
        // Permet de recharger les données au lancement
        let myTimerFirst = NSTimer(timeInterval: 2.0, target: self, selector: #selector(HomeViewController.refresh), userInfo: nil, repeats: false)
        NSRunLoop.mainRunLoop().addTimer(myTimerFirst, forMode: NSDefaultRunLoopMode)
        
        let myTimerMap = NSTimer(timeInterval: 5.0, target: self, selector: #selector(HomeViewController.checkLocation), userInfo: nil, repeats: true)
        NSRunLoop.mainRunLoop().addTimer(myTimerMap, forMode: NSDefaultRunLoopMode)
        
        let myTimerBase = NSTimer(timeInterval: 30.0, target: self, selector: #selector(HomeViewController.getNotes), userInfo: nil, repeats: true)
        NSRunLoop.mainRunLoop().addTimer(myTimerBase, forMode: NSDefaultRunLoopMode)
        
        
        
    }
    
    
    // Fonction permettant de recharger les notes dans le tableView
    func refresh() {
        noteTableView.reloadData()
    }
    
    // Récupère les notes sur le serveur
    func getNotes(){
        noteTableView.reloadData()
        try! realm.write {
            realm.delete(noteList)
        }
        noteCont.getNotes()
    }
    
    
    
    // Pour chaque Zone, récupère la liste de Points et vérifie si l'utilisateur se trouve dedans
    func checkLocation(){
        
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
    }
    
    
    
    
    
    // Localisation de la zone actuelle
    internal func checkLocationWithPoints(pointList: [Point], areaId: String){
        
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
        
        myLatitude = currentLocation.coordinate.latitude
        
        myLongitude = currentLocation.coordinate.longitude
        
        var pointsCLLC = [CLLocationCoordinate2D]()
        
        
        for point in pointList {
            let locationCoor = CLLocationCoordinate2DMake(point.getLatitude(), point.getLongitude())
            pointsCLLC.append(locationCoor)
        }
        
        let point   = MKMapPoint(x: myLongitude , y: myLatitude)
        let polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)

        // Si le point est dans le polygon, on vérifie si la Zone a changée
        if (isPointInPolygon(point, polygon: polygon)){
            if(myArea.getId() != areaId){
                
                myArea = realm.objects(Area).filter("_id == '\(areaId)'").first
                self.title = myArea.getName()
                self.navigationController!.navigationBar.barTintColor = UIColor(hex: myArea.getColor())
                noteList = realm.objects(Note).filter("_idArea == '\(myArea.getId())'")
                self.refresh()
            }
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
    
 
    
    
    
    
    
    // Fonctions du TableView
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Int(noteList.count)
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
       
        // Déclaration de la cellule
        let cell = tableView.dequeueReusableCellWithIdentifier(NoteTableViewCell.identifier, forIndexPath: indexPath) as! NoteTableViewCell
        
        // Récupération des notes
        let index = Int(indexPath.row)
        
        
        if(noteList.count > 0){
            let noteItem = noteList[index]
            
            // Configuration de la cellule
            let author = noteItem.getAuthor()
            let text   = noteItem.getText()
            let date   = noteItem.getDate()
            
            cell.configureWithData(author, text: text, date: date)
        }
        
        return cell
        
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        noteTableView.reloadData() 
    }


    // Préparation du Segue, envoie de la centrale séléctionnée
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "toAddNote") {
            let destination = segue.destinationViewController as! AddNoteViewController
            destination.myArea = myArea
            
        }
    }
}
