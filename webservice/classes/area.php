<?php

Class Area{
    
    private $_id;
    private $_name;
    private $_color;
    
    function GetId() {
        return $this->_id;
    }

    function GetName() {
        return $this->_name;
    }

    function GetColor() {
        return $this->_color;
    }

    function SetId($_id) {
        $this->_id = $_id;
    }

    function SetName($_name) {
        $this->_name = $_name;
    }

    function SetColor($_color) {
        $this->_color = $_color;
    }

    function __construct($_id = null, $_name = null, $_color = null) {
        $this->_id = $_id;
        $this->_name = $_name;
        $this->_color = $_color;
    }

    
}
