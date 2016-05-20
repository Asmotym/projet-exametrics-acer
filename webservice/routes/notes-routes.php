<?php

Flight::route('GET /notes', function() {
	return NotesController::getAllNotes();
});

Flight::route('GET /notes/@id', function($id) {
	return NotesController::getNotesByAreaId($id);
});

Flight::route('POST /notes', function() {
	$note = Flight::request()->data;
	return NotesController::addNote($note);
});