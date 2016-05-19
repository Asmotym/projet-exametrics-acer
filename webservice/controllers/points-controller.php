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
	
}