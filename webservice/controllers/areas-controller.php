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
	 * Request an Area into DB
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
	 * Request an Area with the name of it
	 * @param string $name
	 */
	public static function getAreaByName($name) {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query("select MAX(idArea) as idArea from area where nameArea like '$name'");
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
	 * Insert an Area into DB
	 * @param int $id identifier of the area (can be empty)
	 * @param string $name name of the area
	 * @param string $color HEX color of the area
	 */
	public static function addArea($id, $name, $color) {
		$db = Flight::db(false);
		$req = $db->prepare("insert into area values('', :areaName, :areaColor)");
		if ($req->execute(array("areaName" => "$name", "areaColor" => "$color"))) {
			return self::getAreaByName($name);
		} else {
			return Flight::redirect('/', 400);
		}
	}
	
	/**
	 * Delete an Area
	 * @param int $idArea
	 */
	public static function deleteArea($idArea) {
		$db = Flight::db(false);
		$req = $db->prepare("delete from area where idArea = $idArea");
		if ($result = $req->execute()) {
			return Flight::redirect('/', 201);
		} else {
			return Flight::redirect('/', 400);
		}
	}
	
}