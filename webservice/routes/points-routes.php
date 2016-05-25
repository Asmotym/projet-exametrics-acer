<?php

Flight::route('GET /points', function() {
	return PointsController::getAllPoints();
});

Flight::route('GET /points/@idArea', function($idArea) {
	return PointsController::getPointsByAreaId($idArea);
});

Flight::route('POST /points', function() {
	//$points = new stdClass();
	$points = Flight::request()->data;
	return PointsController::addPoints($points);
});