<?php

class Application_Model_DbTable_Questions extends Zend_Db_Table_Abstract
{

    protected $_name = 'questions';


    public function get($id)
    {
    	if(!$id) {
    		throw new Exception("INTERNAL ERROR: null value");
    	}    	 
    	$where = $this->getAdapter()->quoteInto('id = ?', $id);
    	$row = $this->fetchRow($where);    	 
    	if (!$row) {
    		throw new Exception("Could not find user $id");
    	}
    	return $row->toArray();
    }
    
    public function add($values=array())
    {
    	if(!$values) {
    		throw new Exception("INTERNAL ERROR: null value");
    	}
    	$result = $this->insert($values);
    
    	return $result;
    }
    
    public function getList()
    {
    	$rowset = $this->fetchAll();
    	$rowCount = count($rowset);
    	if ($rowCount == 0) {
    		return false;
    	}
    	return $rowset->toArray();
    }
    
    public function getTop()
    {
    	$rowset = $this->fetchAll(array(), array('numVotes DESC'));
    	$rowCount = count($rowset);
    	if ($rowCount == 0) {
    		return false;
    	}
    	return $rowset->toArray();
    }
    
    public function getLast()
    {
    	$rowset = $this->fetchAll(array(), array('timeCreated DESC'));
    	$rowCount = count($rowset);
    	if ($rowCount == 0) {
    		return false;
    	}
    	return $rowset->toArray();
    }
    
    public function update($id, $values)
    {
    	if(!$id) {
    		throw new Exception("INTERNAL ERROR: null value");
    	}
    	$result = '';
    	if($values) {
    		$where = $this->getAdapter()->quoteInto('id = ?', $id);
    		//unset($values['id']);
    		//return $row->toArray();
    		$this->delete($where);
    		//return $row;
    		$this->insert($values);
    		//$result = $this->update($values, $where);
    		//return 'ok';
    		//return $result;
    	}
    	return $result;
    }

}

