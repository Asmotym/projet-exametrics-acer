<?php

class NotesController {
	
	/**
	 * Return all notes.
	 */
	public static function getAllNotes() {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query('select * from note');
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
	 * Return all notes 
	 * @param int $idArea
	 */
	public static function getNotesByAreaId($idArea) {
		$db = Flight::db(false);
		$response = new stdClass();
		$req = $db->query("select * from note where idArea = $idArea");
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
	 * Add a note to the DB
	 * @param unknown $note
	 */
	public static function addNote($note) {
		$db = Flight::db(false);
		$author = $note["authorNote"];
		$text = $note["textNote"];
		$date = $note["dateNote"];
		$idArea = $note["idArea"];
		$req = $db->prepare("insert into note values('', :author, :text, :date, :idArea)");
		$req->execute(array("author" => "$author", "text" => "$text", "date" => "$date", "idArea" => "$idArea"));
	}
	
	public static function deleteNote($idNote) {
	$db = Flight::db(false);
		$req = $db->prepare("delete from note where idNote = $idNote");
		if ($result = $req->execute()) {
			return Flight::redirect('/', 201);
		} else {
			return Flight::redirect('/', 400);
		}
	}
	
}