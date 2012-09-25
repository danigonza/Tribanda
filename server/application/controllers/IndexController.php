<?php

class IndexController extends Zend_Controller_Action
{

    public function init()
    {
		$this->_helper->viewRenderer->setNoRender(true);    
    }

    public function indexAction()
    {
    	$str='';
    	try {
    		// open connection to MongoDB server
    		$conn = new Mongo('localhost');
    	
    		// access database
    		$db = $conn->test;
    	
    		// access collection
    		$collection = $db->items;
    	
    		// execute query
    		// retrieve all documents
    		$cursor = $collection->find();
    	
    		// iterate through the result set
    		// print each document
    		$str .= $cursor->count() . ' document(s) found. <br/>';
    		foreach ($cursor as $obj) {
    			$str .= 'Name: ' . $obj['name'] . '<br/>';
    			$str .= 'Quantity: ' . $obj['quantity'] . '<br/>';
    			$str .= 'Price: ' . $obj['price'] . '<br/>';
    			$str .= '<br/>';
    		}
    	
    		// disconnect from server
    		$conn->close();
    	} catch (MongoConnectionException $e) {
    		$str .='Error connecting to MongoDB server';
    	} catch (MongoException $e) {
    		$str .= 'Error: ' . $e->getMessage();
    	}
    	
    	$this->getResponse()->appendBody(Zend_Json::encode($str));   
    }
    
    public function getAction()
    {
    	$this->getResponse()->appendBody("From getAction() returning the requested article");	 
    }
    
    public function postAction()
    {
    	$this->getResponse()->appendBody("From postAction() creating the requested article");
    }
    
    public function putAction()
    {
    	$this->getResponse()->appendBody("From putAction() creating the requested article");
    }
    
    public function deleteAction()
    {
    	$this->getResponse()->appendBody("From deleteAction() creating the requested article");
    }


}

