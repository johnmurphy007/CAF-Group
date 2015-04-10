package ie.cit.caf.repository;

import static org.junit.Assert.*;
import ie.cit.caf.domain.Role;

import java.util.List;

import ie.cit.caf.DefaultConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
* @author 
* 
* This is the test class for JdbcRolesRepository class.
* 
* This test class sets the profile to "test" and uses a H2 database (in-memory).  It regenerates clean data after each test is run.
* Note: 
* 	The schema is loaded from 'caf_assignment1-schema_h2.sql'.
* 	The data is loaded from 'caf_assignment1-data_h2.sql', but is overwritten by JdbcTemplateTestApplication.java.  
* 		Comment out JdbcTemplateTestApplication.java if you want to use the 'caf_assignment1-data_h2.sql'.
* 	The test methods below are based on using h2 schema and JdbcTemplateTestApplication.java derived data.
* 
* Another option to use is to use Profile "default" which uses Mysql.  
* If using this, suggest using transaction 'rollback'. This means the data reverts to it previous state after the test method has run.
* To use Transaction 'rollback', (i) comment out the @DirtiesContext, (ii) change @ActiveProfiles("default") to @ActiveProfiles("test") and
* (iii) remove comments @TransactionConfiguration.  Please note that size metrics in test methods below will need to be adjusted. 
*
*/
@RunWith(SpringJUnit4ClassRunner.class)		//need this annotation when using @Autowired.
@SpringApplicationConfiguration(classes = DefaultConfig.class)		//Using DefaultConfig to setup Application Configuration

@ActiveProfiles("test")  //optional. overrides the default profile to use. application.yml file uses "default" (mysql) profile.


//@TransactionConfiguration(defaultRollback=true)

//used for dirties context only:
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD) //Optional. For in-memory db, this will clean/reload the context (i.e. recreate tables etc).

public class JbdcRolesRepositoryTest {

	@Autowired
	JdbcRolesRepository repo;
	
	
	@Test(expected=DuplicateKeyException.class)
	public void testSave3() 
	{
		//Verifies that if duplicate records are saved to repository that an exception will occur
		
		Role role = repo.get("35236655");
	
		repo.save(role);			//add Role to repo
		repo.save(role);			//add duplicate record (not allowed...should throw exception)
	}
	
	
	@Test
	public void testSave() 
	{
		//Verifies the the number of items in Repository increase by 1 as a result of save operation

		List<Role> roles = repo.findAll();
		assertEquals(5, roles.size());			
		
		Role role = new Role();
		role.setRole_id("2");
		role.setRole_name("2");
		role.setRole_display_name("3");
		role.setRole_url("4");
		
		repo.save(role);			
		assertNotNull(role.getRole_id());
		
		List<Role> roles2 = repo.findAll();		
		assertEquals(6, roles2.size());			
	}
	
	@Test
	public void testSave2() 
	{
		//Verifies that modification of a record works.
		//Gets a Role from the repo and stores it in an object, deletes the Role from repo, 
		//modifies object, saves object to repo. Reads the object back and verifies that the updated object was correctly saved.
		Role role = repo.get("35236655");
		repo.remove(role);
		role.setRole_name("Painter");
		role.setRole_display_name("Art deco");
		repo.save(role);
		
		Role role2 = repo.get("35236655");
		assertTrue("Painter".equals(role2.getRole_name()));
		assertTrue("Art deco".equals(role2.getRole_display_name()));
	}
	
	@Test
	public void testGet() {
		//Testing basic retrieval of data from Database
		Role role = repo.get("35236655");
		assertEquals("35236655", role.getRole_id());
		assertEquals("Designer", role.getRole_name());
		assertEquals("Designed by", role.getRole_display_name());
		assertEquals("http://collection.cooperhewitt.org/roles/35236655/", role.getRole_url());
	}



	@Test
	public void testRemove() 
	{
		//method that removes a Role from the repository
		List<Role> roles = repo.findAll();
		Role role = repo.get("35236655");	
		
		assertEquals(5, roles.size());		
		
		repo.remove(role);
		assertNull(repo.get("2"));			
	
		List<Role> roles2 = repo.findAll();		
		assertEquals(4, roles2.size());			
	}

	
	@Test
	public void testFindAll() 
		//test method that verifies the correct amount of records are returned.
	{
		List<Role> roles = repo.findAll();
		assertEquals(5, roles.size());		
	}
}
