<?php

// Get all areas or area with id in a parameter
Flight::route('GET /areas', function() {
	if ($id = Flight::request()->query->id) {
		return AreaController::getAreasById($id);
	} else {
		return AreaController::getAllAreas();
	}
});

// Add an area
Flight::route('POST /areas', function(){
	$data = Flight::request()->data;
	return AreaController::addArea($data->idArea, $data->nameArea, $data->colorArea);
});

// Get the last area id
Flight::route('GET /areas/last', function() {
	return AreaController::getLastAreaId();
});

