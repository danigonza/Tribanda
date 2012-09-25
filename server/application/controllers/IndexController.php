<?php

class IndexController extends Zend_Controller_Action
{

    public function init()
    {
		$this->_helper->viewRenderer->setNoRender(true);    
    }

    public function indexAction()
    {
 
    	$this->getResponse()->appendBody('conect');   
    	
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

