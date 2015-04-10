package ie.cit.caf.repository;

import ie.cit.caf.domain.Role;
import ie.cit.caf.utility.RolesRowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * @author 
 * 
 * This class implements the interface for ChObjectRepository
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */

//Identify this class as Repository (Spring will detect it during @ComponentScan).
@Repository
public class JdbcRolesRepository implements RolesRepository 
{
	//Using JdbcTemplate to provide repository access using SQL statements
	private JdbcTemplate jdbcTemplate;
	
	//Using Constructor DI, auto-inject the DataBase dependency connection into this object.
	@Autowired
	public JdbcRolesRepository(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Role get(String id) 
	{
		/* This method retrieves an object (ChObject) based on a supplied String based 'id'.
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
		String sql = "SELECT COUNT(*) FROM role WHERE role_id = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {id});
		
		if (count > 0)
		{
			sql = "SELECT * FROM role WHERE role_id = ?";
			
			Role role = jdbcTemplate.queryForObject(sql, new Object [] {id}, new RolesRowMapper());
			return role;
		}
		else
		{
			return null;
		}
			
	}

	@Override
	public void save(Role role) 
	{
		//Implementing only the 'add()' part of 'save()' at present.
		
//		if (role.getRole_id() != null)
//		{
//			update(role);
//		}
//		else
//		{
			add(role);
//		}
	}

	private void add(Role role)
	{
		//Method to add a record to the repository
		String sql = "INSERT INTO role (role_id, role_name, role_display_name, role_url) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {role.getRole_id(),role.getRole_name(),role.getRole_display_name(),role.getRole_url()} );
	}

	
	private void update(Role role)
	{
		//METHOD NOT IMPLEMENTED/TESTED
		//Method to update a record in the repository
		String sql = "UPDATE role SET role_name =?, role_display_name=?, role_url =? WHERE role_id = ?";
		jdbcTemplate.update(sql, new Object[] {role.getRole_name(),role.getRole_display_name(),role.getRole_url(),role.getRole_id()});
	}
	
	@Override
	public void remove(Role role) 
	{
		//Method to delete a record from the repository
		String sql = "DELETE FROM role WHERE role_id = ?";
		jdbcTemplate.update(sql, new Object[] {role.getRole_id()});
	}

	@Override
	public List<Role> findAll() 
	{
		//Return a List of all Roles
		String sql = "SELECT * FROM role";
		return jdbcTemplate.query(sql,  new RolesRowMapper());
	}

}
