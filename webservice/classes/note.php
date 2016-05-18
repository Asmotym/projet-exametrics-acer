<?php


Class Note{
    
    private $_id;
    private $_author;
    private $_text;
    private $_date;
    private $_theZone;
    
    function GetId() {
        return $this->_id;
    }

    function GetAuthor() {
        return $this->_author;
    }

    function GetText() {
        return $this->_text;
    }

    function GetDate() {
        return $this->_date;
    }

    function GetTheZone() {
        return $this->_theZone;
    }

    function SetId($id) {
        $this->_id = $id;
    }

    function SetAuthor($author) {
        $this->_author = $author;
    }

    function SetText($text) {
        $this->_text = $text;
    }

    function SetDate($date) {
        $this->_date = $date;
    }

    function SetTheZone($theZone) {
        $this->_theZone = $theZone;
    }


    
    function __construct($_id = null, $_author = null, $_text = null, $_date = null, $_theZone = null) {
        $this->_id = $_id;
        $this->_author = $_author;
        $this->_text = $_text;
        $this->_date = $_date;
        $this->_theZone = $_theZone;
    }

    
    
}