<?php

class PointsController {
	
	public static function getAllPoints() {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query('select * from point');
		if ($result = $req->fetchAll(PDO::FETCH_OBJ)) {
			$response->count = count($result);
			$response->result = $result;
		} else {
			$response->count = 0;
			$response->result = "error";
		}
		return Flight::json($response);
	}
	
	public static function getPointsByAreaId($idArea) {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query("select * from point where idArea = $idArea");
		if ($result = $req->fetchAll(PDO::FETCH_OBJ)) {
			$response->count = 1;
			$response->result = $result;
		} else {
			$response->count = 0;
			$response->result = "error";
		}
		return Flight::json($response);
	}
	
	/**
	 * Add multiple points to the DB
	 * @param unknown $points
	 */
	public static function addPoints($points) {
		$db = Flight::db(false);
		foreach ($points as $point) {
			$longitude = $point["longitude"];
			$latitude = $point["latitude"];
			$idArea = $point["idArea"];
			$req = $db->prepare("insert into point values('', :longitude, :latitude, :idArea)");
			$req->execute(array("longitude" => "$longitude", "latitude" => "$latitude", "idArea" => $idArea));
		}
	}
	
}