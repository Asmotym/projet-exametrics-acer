<?php

Class point{
    
    private $_id;
    private $_longitude;
    private $_latitude;
    private $_laZone;
    
    function get_id() {
        return $this->_id;
    }

    function get_longitude() {
        return $this->_longitude;
    }

    function get_latitude() {
        return $this->_latitude;
    }

    function get_laZone() {
        return $this->_laZone;
    }

    function set_id($_id) {
        $this->_id = $_id;
    }

    function set_longitude($_longitude) {
        $this->_longitude = $_longitude;
    }

    function set_latitude($_latitude) {
        $this->_latitude = $_latitude;
    }

    function set_laZone($_laZone) {
        $this->_laZone = $_laZone;
    }

    function __construct($_id = null, $_longitude = null, $_latitude = null, $_laZone = null) {
        $this->_id = $_id;
        $this->_longitude = $_longitude;
        $this->_latitude = $_latitude;
        $this->_laZone = $_laZone;
    }

    
    
}