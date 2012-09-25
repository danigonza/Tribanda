<?php

class Application_Model_Answer
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
	
	public function getAnswersByQuestion($key)
	{
		$data = array();
		$db = new Application_Model_DbTable_Answers();
		try{
			//if(is_int($key)){
				$list = $db->getListByQuestion($key);
			//}
			//else{
			//	throw new Exception();
			//}
			
			foreach($list as $k => $ans){
				// Get user
				$user = new Application_Model_User();
				$infouser = $user->getInfo($ans['userId']);
			
				$ans['infoUser'] = $infouser;
				$ans['thumb'] = $ans['path'].'thumb.png';
				$list[$k] = $ans;
			}
				
	
				
			return $list;
		}
		catch(Exception $e){
	
		}
	}
	
	public function createAnswer($data)
	{
		$db = new Application_Model_DbTable_Answers();
		try{
			$time = time();
			$this->timeCreated = $time;
			$this->timeModified = $time;
			$this->path = APPLICATION_PATH.'../public/questions/'.rand();
			$this->title = $data->title;
			$this->questionId = $data->questionId;
			$this->numVotes = $data->numVotes;		
			$this->userId = $data->userId;
			$this->videoId = $data->videoId;
				
			$id = $dbUser->addUser($this->answer2Array());
				
			return $id;
		}
		catch(Exception $e){
	
		}
	}
	
	public function answer2Array()
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
		$db = new Application_Model_DbTable_Answer();
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

