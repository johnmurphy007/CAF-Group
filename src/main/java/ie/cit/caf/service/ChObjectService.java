package ie.cit.caf.service;

/**
 * @author 
 * 
 * This interface defines the contract/methods that a ChObject can use at the Service Layer.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */

import ie.cit.caf.domain.ChObject;

import java.util.List;

public interface ChObjectService {
	
	public ChObject get (String id);
	
	public void save(ChObject chobject);
	
	public void remove(ChObject chobject);
	
	public List<ChObject> findAll();


}
