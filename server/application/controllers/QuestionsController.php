<?php

class QuestionsController extends Zend_Controller_Action
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
    	$modelq = new Application_Model_Question();
    	$params = $this->getRequest()->getParams();
    	if($params['id'] == "top"){
    		$data = $modelq->getTopQuestions();
    		
    	}
    	else{
    		if($params['id'] == "last"){
    			$data = $modelq->getLastQuestions();	 
    		}
    		else{
				$data = $modelq->getQuestion($params['id']);    		
    		}
    	}
    	
    	$json = Zend_Json::encode($data);
    	$this->getResponse()->appendBody(Zend_Json::prettyPrint($json));
    }
    
    public function postAction(){
    	$modelq = new Application_Model_Question();
    	$params = $this->getRequest()->getParams();
    	$data = Zend_Json::decode($params['json']);
		$id = $modelq->createQuestion($data);
		$json = Zend_Json::encode($id);
    	$this->getResponse()->appendBody(Zend_Json::prettyPrint($json));
    }

    public function putAction(){
    	$modelq = new Application_Model_Question();
    	$params = $this->getRequest()->getParams();
    	$id = Zend_Json::decode($params['id']);
    	$result = $modelq->addVote($params['id']);
    	//$this->getResponse()->appendBody('put: '.$result); 
    }

}

