<?php

class Application_Model_Question
{

	private $id;
	private $title;
	private $userId;
	private $path;
	private $videoId;
	private $numVotes;
	private $timeCreated = null;
	private $timeModified = null;

	/**
	 *
	 * Setters dispacher
	 *
	 * @param	string 	$name 	Name of the field to set
	 * @param	Object	$value	Value to set to the field
	 */
	public function __set($name, $value)
	{
		$method = 'set' . $name;
		if (('mapper' == $name) || !method_exists($this, $method)) {
			throw new Exception('invalidProperty'.$name);
		}
		$this->$method($value);
	}
	
	/**
	 *
	 * Getters dispacher
	 *
	 * @param	string 	$name 	Name of the field to set
	 * @return Object			Value to get to the field
	 */
	public function __get($name)
	{
		$method = 'get' . $name;
		if (('mapper' == $name) || !method_exists($this, $method)) {
			throw new Exception('invalidProperty');
		}
		return $this->$method();
	}
	
	/**
	 *
	 * Set differents fields for the constructor in one action
	 *
	 * @param	array 	$option			Array with key -> field value -> value to set
	 * @return Application_Model_User
	 */
	public function setOptions(array $options)
	{
		$methods = get_class_methods($this);
		foreach ($options as $key => $value) {
			$method = 'set' . ucfirst($key);
			if (in_array($method, $methods)) {
				$this->$method($value);
			}
		}
		return $this;
	}
	
	public function getQuestion($key)
	{
		$data = array();
		$db = new Application_Model_DbTable_Questions();
		try{
			//if(is_int($key)){
			$data = $db->get((int)$key);
			//}
			//else{
			//	exit;
			//	throw new Exception();
			//}
			
			// Get user	
			$user = new Application_Model_User();
			$infouser = $user->getInfo($data['userId']);
			
			$data['infoUser'] = $infouser;
	
			//Get Answers
			$answer = new Application_Model_Answer();
			$listAnsw = $answer->getAnswersByQuestion($data['id']);
			$data['answers'] = $listAnsw;
			
			//Get ThumbImage
			$data['thumb'] = $data['path'].'thumb.png';
			
			return $data;
		}
		catch(Exception $e){
				
		}
	}
	
	public function createQuestion($data)
	{
		$db = new Application_Model_DbTable_Questions();
		try{
			$time = time();
			$this->timeCreated = $time;
			$this->timeModified = $time;
			$this->numVotes = 0;
			$this->path = APPLICATION_PATH.'../public/questions/'.rand();
			$this->title = $data->title;
			$this->userId = $data->userId;
			$this->videoId = $data->videoId;
			
			$id = $dbUser->addUser($this->question2Array());
			$aux = explode('/', $data->videoId);
				
			move_uploaded_file('/Users/Dani/git/tribanda/server/public/upload/'.$aux[count($aux)-1], '/Users/Dani/git/tribanda/server/public/files/questions/'.$id.'/'.$aux[count($aux)-1]);
				
			
			return $id;
		}
		catch(Exception $e){
	
		}
	}
	
	public function getListQuestions()
	{
		$q = array();
		$db = new Application_Model_DbTable_Questions();
					
		$q = $db->getList();
	
		return $q;
	}
	
	public function getTopQuestions(){
		$q = array();
		$db = new Application_Model_DbTable_Questions();
					
		$q = $db->getTop();
		$q = array_slice($q, 0, 25);
		foreach($q as $k => $question){
			// Get user
			$user = new Application_Model_User();
			$infouser = $user->getInfo($question['userId']);
				
			$question['infoUser'] = $infouser;
			
			//Get Answers
			$answer = new Application_Model_Answer();
			$listAnsw = $answer->getAnswersByQuestion($question['id']);
			$question['answers'] = $listAnsw;
			$q[$k] = $question;
		}
		
		return $q;
	}
	
	public function getLastQuestions(){
		$q = array();
		$db = new Application_Model_DbTable_Questions();
					
		$q = $db->getLast();
		foreach($q as $k => $question){
			// Get user
			$user = new Application_Model_User();
			$infouser = $user->getInfo($question['userId']);
		
			$question['infoUser'] = $infouser;
				
			//Get Answers
			$answer = new Application_Model_Answer();
			$listAnsw = $answer->getAnswersByQuestion($question['id']);
			$question['answers'] = $listAnsw;
			$q[$k] = $question;
		}
	
		return $q;
	}
	
	public function question2Array()
	{
		$result = array();
		foreach($this as $attr => $value){
				$result[$attr] = $value;
		}
		return $result;
	}
	
	public function addVote($key)
	{
		$data = array();
		$db = new Application_Model_DbTable_Questions();
		try{
			//if(is_int($key)){
			$data = $db->get((int)$key);
			//return print_r($data);
			$data['numVotes'] = (int)$data['numVotes']+1;
			//unset($data['id']);
			$result = $db->update($key, $data);
			//return print_r($result);
		}
		catch(Exception $e){
			return 'ERROR'.$e->getMessage();
		}
	}
	
}

