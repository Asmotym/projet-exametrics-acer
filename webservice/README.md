# Webservice APP pour iOS et Android

## Routes WebService

- [Zones](#routes-pour-les-zones)
- [Points](#routes-pour-les-points)
- [Notes](#routes-pour-les-notes)

### Routes pour les Zones

<dl><dt>http://172.30.1.178:8080/exametrics-ws/areas GET</dt>
<dd>Retourne toute les Zones.</dd></dl>
```json
{
	"count": 2,
	"result": [{
		"idArea": "1",
		"nameArea": "The World",
    	"colorArea": "121212"
	}, {
		"idArea": "2",
		"nameArea": "Star Platinum",
		"colorArea": "600060"
	}]
}
```
        
<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/areas?id=@idArea GET</dt>
<dd>Retourne une Zone avec l'id.</dd></dl>
```json
{
	"count": 1,
	"result": [{
		"idArea": "1",
		"nameArea": "The World",
		"colorArea": "121212"
	}]
}
```

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/areas POST</dt>
<dd>Ajoute une zone.</dd>
<dd>Exemple du json à envoyer.</dd></dl>

```json
{
	"idArea": "",
	"nameArea": "Toast",
	"colorArea": "FFFFFF"
}
```

### Routes pour les Points

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/points GET</dt>
<dd>Retourne tous les Points.</dd></dl>

```json
{
	"count": 8,
	"result": [{
		"idPoint": "3",
		"longitude": "42",
		"latitude": "2",
		"idArea": "1"
	},{
		"idPoint": "4",
		"longitude": "43",
		"latitude": "2",
		"idArea": "1"
	},{
		"idPoint": "5",
		"longitude": "43",
		"latitude": "3",
		"idArea": "1"
	},{
		"idPoint": "6",
		"longitude": "42",
		"latitude": "3",
		"idArea": "1"
	},{
		"idPoint": "7",
		"longitude": "40",
		"latitude": "2",
		"idArea": "2"
	},{
		"idPoint": "8",
		"longitude": "41",
		"latitude": "2",
		"idArea": "2"
	},{
		"idPoint": "9",
		"longitude": "41",
		"latitude": "3",
		"idArea": "2"
	},{
		"idPoint": "10",
		"longitude": "40",
		"latitude": "3",
		"idArea": "2"
	}]
}
```

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/points/@idArea GET</dt>
<dd>Retourne les Points d'une Zone.</dd></dl>

```json
{
	"count": 8,
	"result": [{
		"idPoint": "3",
		"longitude": "42",
		"latitude": "2",
		"idArea": "1"
	},{
		"idPoint": "4",
		"longitude": "43",
		"latitude": "2",
		"idArea": "1"
	},{
		"idPoint": "5",
		"longitude": "43",
		"latitude": "3",
		"idArea": "1"
	},{
		"idPoint": "6",
		"longitude": "42",
		"latitude": "3",
		"idArea": "1"
	}]
}
```

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/points POST</dt>
<dd>Ajoute des points à une zone</dd>
<dd>Exemple du type de json à envoyer.</dd></dl>

```json
{
    "0": {
        "idPoint": "",
        "longitude": 40,
        "latitude": 3,
        "idArea": 5
    },
    "1": {
        "idPoint": "",
        "longitude": 45,
        "latitude": 2,
        "idArea": 5
    },
    "2": {
        "idPoint": "",
        "longitude": 50,
        "latitude": 3,
        "idArea": 5
    }
}
```

### Routes pour les Notes

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/notes GET</dt>
<dd>Retourne toutes les notes.</dd></dl>

```json
{
	"count": 7,
	"result": [{
		"idNote": "1",
		"authorNote": "Clément",
		"textNote": "test",
		"dateNote": "2016-05-19 00:00:00",
		"idArea": "1"
	},{
		"idNote": "2",
		"authorNote": "Clement",
		"textNote": "test",
		"dateNote": "2016-05-16 00:00:00",
		"idArea": "1"
	},{
		"idNote": "3",
		"authorNote": "Anonyme",
		"textNote": "sfgdf",
		"dateNote": "2016-05-19 00:00:00",
		"idArea": "2"
	},{
		"idNote": "4",
		"authorNote": "test",
		"textNote": "test choucroute",
		"dateNote": "2016-05-20 11:00:00",
		"idArea": "1"
	},{
		"idNote": "5",
		"authorNote": "Anonyme",
		"textNote": "OUI",
		"dateNote": "2016-05-20 13:00:00",
		"idArea": "2"
	},{
		"idNote": "6",
		"authorNote": "Anonyme",
		"textNote": "NON",
		"dateNote": "2016-05-20 14:00:00",
		"idArea": "2"
	},{
		"idNote": "7",
		"authorNote": "Anonyme",
		"textNote": "erztret\ngesrfyhrt-yhydcryjv\ndxjydxtyj\ncd\njvyxd\nvjtdyvjtd\nyjv\ndjvyx\nyjytd\njyftd\njd\njdvr\nyjvyd\ntjfdutjrvtyjtdyjdbtyjtfydjtfydjvftyjvftryjbvftydjvftyjrtyjdhvtrhdvtyj\nvtrd\nyvj\nd\nyjvd\nyjv\ntydj\nftvuy\nj\nufyj\nf\njf\njf\nyt\njrufty\njvf\ntykjr\nuyk\nfyuv\nktyu\nbkfy\nubkt\nfyubk\ntgyuf\nbky\ngufbk\nfgyu\nk\ntyubk\ntgyuk\nfuyk\ntyuk\ntyu\nk\ntyukbty\nukt\nub",
		"dateNote": "2016-05-24 00:00:00",
		"idArea": "1"
	}]
}
```

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/notes/@idArea GET</dt>
<dd>Retourne les notes d'une zone.</dd></dl>

```json
{
	"count": 1,
	"result": [{
		"idNote": "1",
		"authorNote": "Clément",
		"textNote": "test",
		"dateNote": "2016-05-19 00:00:00",
		"idArea": "1"
	},{
		"idNote": "2",
		"authorNote": "Clement",
		"textNote": "test",
		"dateNote": "2016-05-16 00:00:00",
		"idArea": "1"
	},{
		"idNote": "4",
		"authorNote": "test",
		"textNote": "test choucroute",
		"dateNote": "2016-05-20 11:00:00",
		"idArea": "1"
	},{
		"idNote": "7",
		"authorNote": "Anonyme",
		"textNote": "erztret\ngesrfyhrt-yhydcryjv\ndxjydxtyj\ncd\njvyxd\nvjtdyvjtd\nyjv\ndjvyx\nyjytd\njyftd\njd\njdvr\nyjvyd\ntjfdutjrvtyjtdyjdbtyjtfydjtfydjvftyjvftryjbvftydjvftyjrtyjdhvtrhdvtyj\nvtrd\nyvj\nd\nyjvd\nyjv\ntydj\nftvuy\nj\nufyj\nf\njf\njf\nyt\njrufty\njvf\ntykjr\nuyk\nfyuv\nktyu\nbkfy\nubkt\nfyubk\ntgyuf\nbky\ngufbk\nfgyu\nk\ntyubk\ntgyuk\nfuyk\ntyuk\ntyu\nk\ntyukbty\nukt\nub",
		"dateNote": "2016-05-24 00:00:00",
		"idArea": "1"
	}]
}
```

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/notes POST</dt>
<dd>Ajoute une note à une zone.</dd>
<dd>Exemple de json à envoyer. L'auteur est définie à 'Anonyme' si il n'est pas renseigné.</dd></dl>

```json
{
    "idNote": "",
    "authorNote": "",
    "textNote": "JAJAJAJA CHOUCROUTE",
    "dateNote": "2016-05-20 11:00:00",
    "idArea": "1"
}
```
