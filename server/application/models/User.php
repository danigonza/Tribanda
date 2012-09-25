<?php

class Application_Model_User
{

	private $username;
	private $firstName;
	private $lastName;
	private $role;
	private $phone = null;
	private $address = null;
	private $addressCity = null;
	private $addressZip = null;
	private $addressProvince = null;
	private $addressCountry = null;
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
	
	public function getUser($key)
	{
		$data = array();
		$dbUser = new Application_Model_DbTable_User();
		try{
			if(is_int($key)){
				$data = $dbUser->getUser($key);
			}
			else{
				throw new Exception();
			}
				
			foreach ($data as $attr => $value){
				if(property_exists($this, $attr)){
					$this->$attr = $value;
				}
			}
				
			return $this;
		}
		catch(Exception $e){
			
		}
	}
	
	public function getInfo($key)
	{
		$data = array();
		$dbUser = new Application_Model_DbTable_User();
		try{
			//if(is_int($key)){
				$data = $dbUser->getUser((int)$key);
			//}
			//else{
			//	throw new Exception();
			//}
	
			//	return $data;
			return array('id' => $data['id'], 'name' => $data['firstname'].' '.$data['lastname']);
		}
		catch(Exception $e){
				
		}
	}
	
	/**
	 *
	 * Return the list of all the user stored
	 *
	 * @return array
	 */
	public function getListUsers()
	{
		$users = array();
		$dbUser = new Application_Model_DbTable_User();
			
		$users = $dbUser->getList();
	
		return $users;
	}
	


}

