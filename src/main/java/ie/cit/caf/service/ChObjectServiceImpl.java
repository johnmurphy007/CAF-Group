package ie.cit.caf.service;

import ie.cit.caf.domain.ChObject;
import ie.cit.caf.repository.ChObjectRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 
 * 
 * This class implements the interface for ChObjectService.  
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
public class ChObjectServiceImpl implements ChObjectService {
	
	//instance variable that is updated via constructor DI
	ChObjectRepository chObjectRepository;

	//Autowire this object, using constructor DI.
	@Autowired
	public ChObjectServiceImpl (ChObjectRepository chObjectRepository)
	{
		this.chObjectRepository = chObjectRepository;
	}
	
	@Override
	public ChObject get(String id) 
	{
		return chObjectRepository.get(id);
	}

	@Override
	public void save(ChObject chobject) 
	{
		chObjectRepository.save(chobject);
	}

	@Override
	public void remove(ChObject chobject) 
	{
		chObjectRepository.remove(chobject);
	}

	@Override
	public List<ChObject> findAll() 
	{
		return chObjectRepository.findAll();
	}

}
