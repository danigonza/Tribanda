<?php

class UsersController extends Zend_Controller_Action
{

    public function init()
    {
		$this->_helper->viewRenderer->setNoRender(true);    
   	}

    public function indexAction()
    {
        // action body
    }

    public function getAction()
    {
    	$this->getResponse()->appendBody("From getAction() returning the requested article");
    }

}

