<?php

class ServerLoader {
	
	/**
	 * We don't need it
	 */
	private function __construct(){}
	
	/**
	 * Static initializer for register the database into Flight
	 * @param String $host Host server
	 * @param String $db_name Name of the database
	 * @param String $login Login of the database
	 * @param String $password Password for the login
	 */
	public static function init($host, $db_name, $login, $password) {
		Flight::register('db', 'PDO',
				array("mysql:host=$host;dbname=$db_name",
						$login,
						$password), function($db) {
							$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		});
	}
	
}