<?php

Class Area{
    
    private $_id;
    private $_name;
    private $_color;
    
    function Get_id() {
        return $this->_id;
    }

    function Get_name() {
        return $this->_name;
    }

    function Get_color() {
        return $this->_color;
    }

    function Set_id($_id) {
        $this->_id = $_id;
    }

    function Set_name($_name) {
        $this->_name = $_name;
    }

    function Set_color($_color) {
        $this->_color = $_color;
    }

    function __construct($_id = null, $_name = null, $_color = null) {
        $this->_id = $_id;
        $this->_name = $_name;
        $this->_color = $_color;
    }

    
}
