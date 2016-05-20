<?php

class AreaController {
	
	/**
	 * Request to the DB all areas
	 */
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
	
	/**
	 * Request an Area to the DB
	 * @param int $id identifier of an area
	 */
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
	
	/**
	 * Insert an Area to the DB
	 * @param int $id identifier of the area (can be empty)
	 * @param string $name name of the area
	 * @param string $color HEX color of the area
	 */
	public static function addArea($id, $name, $color) {
		$db = Flight::db(false);
		$req = $db->prepare("insert into area values('', :areaName, :areaColor)");
		if ($req->execute(array("areaName" => "$name", "areaColor" => "$color"))) {
			return Flight::redirect('new/location', 201);
		} else {
			return Flight::redirect('new/location', 400);
		}
	}
	
	public static function getLastAreaId() {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query("select idArea from area order by idArea desc limit 1");
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