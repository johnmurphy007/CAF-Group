package ie.cit.caf.service;


import ie.cit.caf.domain.Participant;
import ie.cit.caf.repository.ParticipantRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author 
 * 
 * This class implements the interface for ParticipantService.  
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
public class ParticipantServiceImpl implements ParticipantService {

	//instance variable that is updated via constructor DI
	ParticipantRepository participantRepository;
	
	//Autowire this object, using constructor DI.	
	@Autowired
	public ParticipantServiceImpl (ParticipantRepository participantRepository)
	{
		this.participantRepository = participantRepository;
	}
	
	@Override
	public Participant get(String id) 
	{
		return participantRepository.get(id);
	}

	@Override
	public void save(Participant participant) 
	{
		participantRepository.save(participant);
	}

	@Override
	public void remove(Participant participant) 
	{
		participantRepository.remove(participant);
	}

	@Override
	public List<Participant> findAll() 
	{
		return participantRepository.findAll();
	}

}
