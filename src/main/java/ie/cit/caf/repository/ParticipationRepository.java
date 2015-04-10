package ie.cit.caf.repository;


import ie.cit.caf.domain.Participation;
import java.util.List;

/**
 * @author 
 * 
 * This interface defines the contract/methods that a Participation can use on a Repository.
 * 
 * Activities are:
 * 	get			Retrieve record(s) that match a given: cultural heritage object id, participation id, role id 
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */


public interface ParticipationRepository {
	
	public Participation get (String chObject_id, String participation_id, String role_id);
	
	public void save(Participation participation, String chObject_id);
	
	public void remove(Participation participation, String chObject_id);
	
	public List<Participation> findAll();

}
