# Webservice APP pour iOS et Android

## Routes WebService

- [Zones](#routes-pour-les-zones)
- [Points](#routes-pour-les-points)
- [Notes](#routes-pour-les-notes)

### Routes pour les Zones

<dl><dt>http://172.30.1.178:8080/exametrics-ws/areas</dt>
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
<dt>http://172.30.1.178:8080/exametrics-ws/areas/@idArea</dt>
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


### Routes pour les Points

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/points</dt>
<dd>Retourne tous les Points.</dd></dl>

<dl>
<dt>http://172.30.1.178:8080/exametrics-ws/points/@idArea</dt>
<dd>Retourne les Points d'une Zone.</dd></dl>

### Routes pour les Notes

    - /notes
        Retourne toutes les Notes.
        
    - /notes/@idArea
        Retourne toutes les Notes d'une Zone.
