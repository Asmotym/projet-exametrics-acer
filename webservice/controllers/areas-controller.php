<?php

class AreaController {
	
	public static function getAllAreas() {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query('select * from area');
		if ($result = $req->fetchAll(PDO::FETCH_OBJ)) {
			$response->count = count($result);
			$response->result = $result;
		} else {
			$response->count = 0;
			$response->result = "error";
		}
		return Flight::json($response);
	}
	
	public static function getAreasById($id) {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query("select * from area where idArea = $id");
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