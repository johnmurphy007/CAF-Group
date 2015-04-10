package ie.cit.caf.service;

import ie.cit.caf.domain.Role;
import ie.cit.caf.repository.RolesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 
 * 
 * This class implements the interface for RoleService.  
 * 
 * This class passes directly through to the Repository layer.
 * 
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */


//Identify this class as Service (Spring will detect it during @ComponentScan & create a bean of this type).
@Service
public class RolesServiceImpl implements RolesService {

	//instance variable that is updated via constructor DI
	private RolesRepository rolesRepository;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public RolesServiceImpl (RolesRepository rolesRepository)
	{
		this.rolesRepository = rolesRepository;
	}
	
	@Override
	public Role get(String id) 
	{
		return rolesRepository.get(id);
	}

	@Override
	public void save(Role role) 
	{
		rolesRepository.save(role);
	}

	@Override
	public void remove(Role role) 
	{
		rolesRepository.remove(role);
	}

	@Override
	public List<Role> findAll() 
	{
		return rolesRepository.findAll();
	}
}
