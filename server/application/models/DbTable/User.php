<?php

class Application_Model_DbTable_User extends Zend_Db_Table_Abstract
{

    protected $_name = 'user';

    public function getUser($id)
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

}

