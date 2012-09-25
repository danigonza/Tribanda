<?php

class Application_Model_DbTable_Answers extends Zend_Db_Table_Abstract
{

    protected $_name = 'answers';

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
    
    public function getListByQuestion($id)
    {
    	$rowset = $this->fetchAll(array('questionId' => $id));
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

