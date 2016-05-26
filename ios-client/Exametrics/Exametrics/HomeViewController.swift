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
    var mArea : Area!
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
        
        getDatas()
        
        areaList  = realm.objects(Area)
        pointList = realm.objects(Point)
        noteList  = realm.objects(Note)
        
        // Permet de recharger les données au lancement
        let myTimerFirst = NSTimer(timeInterval: 2.0, target: self, selector: #selector(HomeViewController.refresh), userInfo: nil, repeats: false)
        NSRunLoop.mainRunLoop().addTimer(myTimerFirst, forMode: NSDefaultRunLoopMode)
        
        let myTimerMap = NSTimer(timeInterval: 2.0, target: self, selector: #selector(HomeViewController.checkLocation), userInfo: nil, repeats: false)
        NSRunLoop.mainRunLoop().addTimer(myTimerMap, forMode: NSDefaultRunLoopMode)
        
        let myTimerBase = NSTimer(timeInterval: 30.0, target: self, selector: #selector(HomeViewController.getDatas), userInfo: nil, repeats: true)
        NSRunLoop.mainRunLoop().addTimer(myTimerBase, forMode: NSDefaultRunLoopMode)
        
        
        
    }
    
    // Récupère les données sur le serveur
    func getDatas(){
        areaCont.getAreas()
        pointCont.getPoints()
        noteCont.getNotes()
    }
    
    // Pour chaque Zone, récupère la liste de Points et vérifie si l'utilisateur se trouve dedans
    func checkLocation(){
        for area in areaList {
            
            var currentPointList = [Point]()
            for point in pointList {
                if(point.getIdArea() == area.getId()){
                    currentPointList.append(point)
                }
            }
            checkLocationWithPoints(currentPointList)
        }
    }
    
    
    // Localisation de la zone actuelle
    internal func checkLocationWithPoints(pointList: [Point]){
        
        let areaId = pointList[1].getIdArea()
        
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
            let locationCoor = CLLocationCoordinate2DMake(point.getLongitude(), point.getLatitude())
            pointsCLLC.append(locationCoor)
        }
        
        let point   = MKMapPoint(x: myLongitude , y: myLatitude)
        let polygon = MKPolygon(coordinates: &pointsCLLC, count: pointsCLLC.count)

        // Si le point est dans le polygon, on vérifie si la Zone a changée
        if (isPointInPolygon(point, polygon: polygon)){
            if(mArea.getId() != areaId){
                mArea = realm.objects(Area).filter("age == \(areaId)").first
                self.title = mArea.getName()
                self.navigationController!.navigationBar.barTintColor = UIColor(hex: mArea.getColor())
                self.getDatas()
            }
        }

    }
    
    // Vérifie si un Point est dans un Polygon
    func isPointInPolygon(point : MKMapPoint, polygon : MKPolygon) -> Bool{
        
        let a = polygon.points()
        var isInsidePolygon = false
        
        let x = point.x
        let y = point.y
        
        var i = 0
        var j = 0
        
        let nvert = polygon.pointCount
        
        for (i = 0,  j = nvert - 1; i < nvert; j = i++) {
            if (((a[i].y >= y) != (a[j].y >= y)) &&
                (x <= (a[j].x - a[i].x) * (y - a[i].y) / (a[j].y - a[i].y) + a[i].x)) {
                isInsidePolygon = !isInsidePolygon;
            }
        }
        return isInsidePolygon;
    }
    
    func refresh() {
        noteTableView.reloadData()
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
        let noteItem = noteList[index]
        
        // Configuration de la cellule
        let author = noteItem.getAuthor()
        let text   = noteItem.getText()
        let date   = noteItem.getDate()
        
        cell.configureWithData(author, text: text, date: date)
        
        return cell
        
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        noteTableView.reloadData() 
    }

    
    // Préparation du Segue, envoie de la Zone séléctionnée
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "toAddNote") {
            let destination = segue.destinationViewController as! AddNoteViewController
            destination.mArea = mArea
        }
    }

}
