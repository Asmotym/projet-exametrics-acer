<?php

// Get all areas
Flight::route('GET /areas', function() {
	return AreaController::getAllAreas();
});

// Get one area
Flight::route('GET /areas/@id', function($id){
	return AreaController::getAreasById($id);
});

// Add an area
Flight::route('POST /areas', function(){
	$data = Flight::request()->data;
	return AreaController::addArea($data->idArea, $data->nameArea, $data->colorArea);
});