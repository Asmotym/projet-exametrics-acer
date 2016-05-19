<?php

require 'flight/Flight.php';
require 'loader/server-loader.php';
require 'configs/server-config.php';
require 'configs/global-variables.php';
require 'classes/area.php';
require 'controllers/areas-controller.php';

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

Flight::start();