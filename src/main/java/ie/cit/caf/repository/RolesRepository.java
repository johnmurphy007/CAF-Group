package ie.cit.caf.repository;

import ie.cit.caf.domain.Role;
import java.util.List;

/**
 * @author 
 * 
 * This interface defines the contract/methods that a Role can use on a Repository.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */

public interface RolesRepository {
	
	public Role get (String id);
	
	public void save(Role role);
	
	public void remove(Role role);
	
	public List<Role> findAll();

}
