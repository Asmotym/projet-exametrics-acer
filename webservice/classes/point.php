<?php

Class Point{
    
    private $_id;
    private $_longitude;
    private $_latitude;
    private $_theArea;
    
    function GetId() {
        return $this->_id;
    }

    function GetLongitude() {
        return $this->_longitude;
    }

    function GetLatitude() {
        return $this->_latitude;
    }

    function GetTheArea() {
        return $this->_theArea;
    }

    function SetId($id) {
        $this->_id = $id;
    }

    function SetLongitude($longitude) {
        $this->_longitude = $longitude;
    }

    function SetLatitude($latitude) {
        $this->_latitude = $latitude;
    }

    function SetTheArea($theArea) {
        $this->_theArea = $theArea;
    }

    function __construct($_id = null, $_longitude = null, $_latitude = null, $_theArea = null) {
        $this->_id = $_id;
        $this->_longitude = $_longitude;
        $this->_latitude = $_latitude;
        $this->_theArea = $_theArea;
    }

    
}