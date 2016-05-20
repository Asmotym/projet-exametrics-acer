<?php

require 'flight/Flight.php';
require 'loader/server-loader.php';
require 'configs/server-config.php';
require 'configs/global-variables.php';
require 'controllers/areas-controller.php';
require 'controllers/points-controller.php';
require 'controllers/notes-controller.php';

ServerLoader::init(ServerConfig::$HOST, 
		ServerConfig::$DB_NAME, 
		ServerConfig::$LOGIN, 
		ServerConfig::$PASSWORD);

Flight::route('GET /', function(){
	$response = new stdClass();
	$items = [];
	$items[0] = "API Examétrics";
	$response->count = 1;
	$response->result = $items;
	return Flight::json($response);
});

include 'routes/areas-routes.php';
include 'routes/notes-routes.php';
include 'routes/points-routes.php';


Flight::start();