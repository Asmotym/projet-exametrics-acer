<?php

Flight::route('GET /points', function() {
	return PointsController::getAllPoints();
});

Flight::route('GET /points/@idArea', function($idArea) {
	return PointsController::getPointsByAreaId($idArea);
});