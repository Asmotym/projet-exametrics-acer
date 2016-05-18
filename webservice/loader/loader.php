<?php

require 'flight/Flight.php';

class Loader {
	
	private function __construct(){}
	
	public static function init($host, $db_name, $login, $password) {
		Flight::register('db', 'PDO', 
				array("mysql:host=$host;dbname=$db_name", 
						$login, 
						$password), 
				function($db) {
					$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		});
	}
	
}