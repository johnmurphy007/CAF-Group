package ie.cit.caf.repository;

import ie.cit.caf.domain.Participation;
import ie.cit.caf.utility.ParticipationRowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * 
 * This class implements the interface for ParticipationRepository
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */



//Identify this class as Repository (Spring will detect it during @ComponentScan).
@Repository
public class JdbcParticipationRepository implements ParticipationRepository 
{
	//Using JdbcTemplate to provide repository access using SQL statements
	private JdbcTemplate jdbcTemplate;
	
	//Using Constructor DI, auto-inject the DataBase dependency connection into this object.
	@Autowired
	public JdbcParticipationRepository(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Participation get(String chObject_id, String participant_id, String role_id) 
	{
		/* This method retrieves an object (ChObject) based on a supplied String based CH Object id, participant id  and role id.
		 * 
		 * Need to check that the record exists in the database first before returning it.
		 * Otherwise if the record does not exist, you will get an exception that the application
		 * cannot handle.
		 * Using a SQL COUNT statement to return the number of records.  This will return an integer
		 * 'count'.  If this is 0, then no match exists and the return type of the object is set to 
		 * null. If 'count' is >0, then run the SQL query to return the number of records that match
		 * the supplied String 'id'.
		 * 
		 * */
		String sql = "SELECT COUNT(*) FROM participation WHERE chObject_id = ? AND participant_id = ? AND role_id = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {chObject_id,participant_id,role_id});
		
		if (count > 0)
		{
			//The following query is used to check if there is an entry in the Participation table
			sql = "SELECT * FROM participation WHERE chObject_id = ? AND participant_id = ? AND role_id = ?";
	
			Participation participation = jdbcTemplate.queryForObject(sql, new Object [] {chObject_id, 
																					participant_id, 
																						role_id}, new ParticipationRowMapper());
			return participation;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public void save(Participation participation, String chObject_id) 
	{
		//Implementing only the 'add()' part of 'save()' at present.
		
//		if (chObject_id !=null || participation.getParticipant().getPerson_id() != null || participation.getRole().getRole_id() != null)
//		{
//			update(participation, chObject_id);
//		}
//		else
//		{
			add(participation, chObject_id);
//		}
	}

	private void add(Participation participation, String chObject_id)
	{
		//Method to add a record to the repository
		String sql = "INSERT INTO participation (chObject_id,participant_id,role_id) VALUES (?,?,?)";
		
		jdbcTemplate.update(sql, new Object[] {chObject_id, 
												participation.getParticipant().getPerson_id(), 
												participation.getRole().getRole_id()});
	}

	private void update(Participation participation, String chObject_id)
	{
		//NOT IMPLEMENTED/TESTED
		//Method to update a record in the repository
		String sql = "UPDATE participation SET participant_id = ?, role_id = ? WHERE chObject_id = ?";
		jdbcTemplate.update(sql, new Object[] {participation.getParticipant().getPerson_id(), participation.getRole().getRole_id(), chObject_id});
	}
	
	@Override
	public void remove(Participation participation, String chObject_id) 
	{
		//Method to delete a record from the repository
		String sql = "DELETE FROM participation WHERE chObject_id = ? AND participant_id = ? AND role_id = ?";
		jdbcTemplate.update(sql, new Object[] {chObject_id, participation.getParticipant().getPerson_id(), participation.getRole().getRole_id()});
	}

	@Override
	public List<Participation> findAll() 
	{
		//Return a List of all Participations
		String sql = "SELECT * FROM participation";
		return jdbcTemplate.query(sql,  new ParticipationRowMapper());
	}

}
