<?php


Class note{
    
    private $_id;
    private $_auteur;
    private $_texte;
    private $_date;
    private $_laZone;
    
    function get_id() {
        return $this->_id;
    }

    function get_auteur() {
        return $this->_auteur;
    }

    function get_texte() {
        return $this->_texte;
    }

    function get_date() {
        return $this->_date;
    }

    function get_laZone() {
        return $this->_laZone;
    }

    function set_id($_id) {
        $this->_id = $_id;
    }

    function set_auteur($_auteur) {
        $this->_auteur = $_auteur;
    }

    function set_texte($_texte) {
        $this->_texte = $_texte;
    }

    function set_date($_date) {
        $this->_date = $_date;
    }

    function set_laZone($_laZone) {
        $this->_laZone = $_laZone;
    }

    function __construct($_id = null, $_auteur = null, $_texte = null, $_date = null, $_laZone = null) {
        $this->_id = $_id;
        $this->_auteur = $_auteur;
        $this->_texte = $_texte;
        $this->_date = $_date;
        $this->_laZone = $_laZone;
    }
    
    
    

    
    
    
    
    
    
    
    
    
    
}