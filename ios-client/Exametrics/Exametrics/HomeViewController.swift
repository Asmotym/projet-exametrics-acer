//
//  HomeViewController.swift
//  Exametrics
//
//  Created by Vilaine Emilien on 18/05/2016.
//  Copyright © 2016 IMERIR. All rights reserved.
//

// Clef GoogleAPI IOS : AIzaSyBrBX4Q_tXvnCezexSX0c61SjvGICiJ_0w

import UIKit
import Realm
import CoreLocation
import MapKit

class HomeViewController: UIViewController, UITableViewDataSource,CLLocationManagerDelegate {

    // Variables
    var mArea : Area!
    var pointList = [Point]()
    var noteList = [Note]()
    var areaList: RLMResults!
    //var pointList: RLMResults!
    //var noteList: RLMResults!
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
        
        
        
        //areaCont.getAreas()
        
        //pointCont.getPoints()
        

        
        let point1 = Point(id: "1",longitude: 2.84692917, latitude: 42.67417863, idArea: "87")
        let point2 = Point(id: "1",longitude: 2.84581672, latitude: 42.67432826, idArea: "87")
        let point3 = Point(id: "1",longitude: 2.84641989, latitude: 42.67505938, idArea: "87")
        let point4 = Point(id: "1",longitude: 2.84797993, latitude: 42.67518164, idArea: "87")
        let point5 = Point(id: "1",longitude: 2.84828033, latitude: 42.67409975, idArea: "87")
        pointList.append(point1)
        pointList.append(point2)
        pointList.append(point3)
        pointList.append(point4)
        pointList.append(point5)
        
        // Chargement des notes selon la zone actuelle
        
        checkLocation(pointList)
        
        //        
    }
    
    // Localisation de la zone actuelle
    func checkLocation(pointList: [Point]){
        
        
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

        if (isPointInPolygon(point, polygon: polygon)){
            mArea = Area(id: "Ok", name: "YEAH", color: "Ox7cFF00FF")
            self.title = mArea.getName()
            
        }

    }
    
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
        print(isInsidePolygon)
    }
    
    /*
    func refresh() {
        areaList = RLMResults {
            get {
                return Area.allObjects()
            }
        }
     
        pointList: RLMResults {
            get {
                return Point.allObjects()
            }
        }
        
        var noteList: RLMResults {
            get {
                return Note.allObjects()
            }
        }
    }
     */
    
    // Fonctions du TableView
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Int(noteList.count)
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
       
        // Déclaration de la cellule
        let cell = tableView.dequeueReusableCellWithIdentifier(NoteTableViewCell.identifier, forIndexPath: indexPath) as! NoteTableViewCell
        /*
        // Récupération des notes
        let index = UInt(indexPath.row)
        let noteItem = noteList.objectAtIndex(index) as! Note // [4]
        
        // Configuration de la cellule
        let author = noteItem.getAuthor()
        let text   = noteItem.getText()
        let date   = noteItem.getDate()
        
        cell.configureWithData(author, text: text, date: date)
        */
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
