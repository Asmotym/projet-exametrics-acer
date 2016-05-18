<?php

Class Zone{
    
    private $_id;
    private $_nom;
    private $_couleur;
    
    function get_id() {
        return $this->_id;
    }

    function get_nom() {
        return $this->_nom;
    }

    function get_couleur() {
        return $this->_couleur;
    }

    function set_id($_id) {
        $this->_id = $_id;
    }

    function set_nom($_nom) {
        $this->_nom = $_nom;
    }

    function set_couleur($_couleur) {
        $this->_couleur = $_couleur;
    }

    function __construct($_id = null, $_nom = null, $_couleur = null) {
        $this->_id = $_id;
        $this->_nom = $_nom;
        $this->_couleur = $_couleur;
    }

    
    
}
