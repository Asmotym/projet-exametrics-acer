<?php

// Get all areas or area with id in a parameter
Flight::route('GET /areas', function() {
	if ($id = Flight::request()->query->id) {
		return AreaController::getAreasById($id);
	} else {
		return AreaController::getAllAreas();
	}
});

// Add an area and return the id of the area created
Flight::route('POST /areas', function(){
	$data = Flight::request()->data;
	return AreaController::addArea($data->idArea, $data->nameArea, $data->colorArea);
});

Flight::route('POST /areas/delete', function() {
	$idArea = Flight::request()->data->idArea;
	return AreaController::deleteArea($idArea);
});

