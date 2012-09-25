<?php

class HandlerController extends Zend_Controller_Action
{

    public function init()
    {
		$this->_helper->viewRenderer->setNoRender(true);    
   	}

    public function indexAction()
    {
        // action body
    }
    
    public function postAction(){
    	
    	$target_path  = '/Users/Dani/git/tribanda/server/public/upload/';
    	//$this->getResponse()->appendBody(print_r($_FILES));
    	
    	$target_path = $target_path . basename(rand());
    	
    	if(move_uploaded_file($_FILES['upload']['tmp_name'], $target_path))
    	{
    		$this->getResponse()->appendBody($target_path);    		
    	}
    	else
    	{
    		$this->getResponse()->appendBody(null);
    	}
    }


}

