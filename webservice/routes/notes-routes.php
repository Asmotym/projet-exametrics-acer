<?php

Flight::route('GET /notes', function() {
	if ($id = Flight::request()->query->id) {
		return NotesController::getNotesByAreaId($id);
	} else {
		return NotesController::getAllNotes();
	}
});

Flight::route('POST /notes', function() {
	$note = Flight::request()->data;
	return NotesController::addNote($note);
});