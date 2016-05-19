<?php

Flight::route('GET /areas', function() {
	return AreaController::getAllAreas();
});

Flight::route('GET /areas/@id', function($id){
	return AreaController::getAreasById($id);
});