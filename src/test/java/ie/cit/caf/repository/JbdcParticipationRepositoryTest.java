package ie.cit.caf.repository;

import static org.junit.Assert.*;
import ie.cit.caf.domain.Participant;
import ie.cit.caf.domain.Participation;
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
* This is the test class for JdbcParticipationRepository class.
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
public class JbdcParticipationRepositoryTest {

	@Autowired
	JdbcParticipationRepository repo;
	
	
	@Test(expected=DuplicateKeyException.class)
	public void testSave3() 
	{
		//Verifies that if duplicate records are saved to repository that an exception will occur
		
		Participation part = repo.get("68268301","68263859","35236657");
	
		repo.save(part,"68268301");			//add Participation to repo
		repo.save(part,"68268301");			//add duplicate record (not allowed...should throw exception)
	}
	
	@Test
	public void testSave() 
	{
		//Verifies the the number of items in Repository increase by 1 as a result of save operation
		List<Participation> partslist = repo.findAll();
		assertEquals(53, partslist.size());			

		Participant participant = new Participant(); 
		participant.setPerson_id("300");

		Role role = new Role();
		role.setRole_id("400");
		
		Participation part = new Participation();	
		part.setParticipant(participant);
		part.setRole(role);
		
		repo.save(part,"200");			
	//	assertNotNull(part.getRole().getRole_id());
		
		List<Participation> parts2 = repo.findAll();		
		assertEquals(54, parts2.size());			
	}
	
	@Test
	public void testSave2() 
	{
		//Verifies that modification of a record works.
		//Gets an Participation from the repo and stores it in an object, deletes the Participation from repo, 
		//modifies object, saves object to repo. Reads the object back and verifies that the updated object was correctly saved.
		Participation part = repo.get("68268301","68263859","35236657");
		repo.remove(part,"68268301");
		part.getParticipant().setPerson_id("8000");
		part.getRole().setRole_id("5000");
		repo.save(part, "68268301");
		
		Participation part2 = repo.get("68268301", "8000", "5000");
		assertTrue("8000".equals(part2.getParticipant().getPerson_id()));
		assertTrue("5000".equals(part2.getRole().getRole_id()));
	}
	
	@Test
	public void testGet() {
		//Testing basic retrieval of data from Database
		Participation part = repo.get("68268301","68263859","35236657");

		assertEquals("68263859", part.getParticipant().getPerson_id());
		assertEquals("35236657", part.getRole().getRole_id());
	}



	@Test
	public void testRemove() 
		//method that removes an Participation from the repository
	{
		List<Participation> parts = repo.findAll();
		Participation part = repo.get("68268301","68263859","35236657");	
		
		assertEquals(53, parts.size());		
		
		repo.remove(part, "68268301");
		assertNull(repo.get("68268301","68263859","35236657"));			
	
		List<Participation> parts2 = repo.findAll();		
		assertEquals(52, parts2.size());			
	}

	
	@Test
	public void testFindAll() 
			//test method that verifies the correct amount of records are returned.
	{
		List<Participation> parts = repo.findAll();
		assertEquals(53, parts.size());		
	}
}
