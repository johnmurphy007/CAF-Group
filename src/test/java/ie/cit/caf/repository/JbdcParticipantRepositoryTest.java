package ie.cit.caf.repository;

import static org.junit.Assert.*;
import ie.cit.caf.domain.Participant;

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
* This is the test class for JdbcParticipantRepository class.
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
public class JbdcParticipantRepositoryTest {

	@Autowired
	JdbcParticipantRepository repo;
	
	@Test(expected=DuplicateKeyException.class)
	public void testSave3() 
	{
		//Verifies that if duplicate records are saved to repository that an exception will occur
		
		Participant participant = repo.get("18049369");
	
		repo.save(participant);			//add Participant to repo
		repo.save(participant);			//add duplicate record (not allowed...should throw exception)
	}
	
	@Test
	public void testSave() 
	{
		//Verifies the the number of items in Repository increase by 1 as a result of save operation
		List<Participant> participants = repo.findAll();
		assertEquals(18, participants.size());			
		
		Participant participant = new Participant();
		participant.setPerson_id("6000");
		participant.setPerson_name("Paul Murray");
		participant.setPerson_date("1901, Feb");
		participant.setPerson_url("http://collection.cooperhewitt.org/people/18049369/test");
		
		repo.save(participant);			
		
		List<Participant> participants2 = repo.findAll();		
		assertEquals(19, participants2.size());			
	}
	
	@Test
	public void testSave2() 
	{
		//Verifies that modification of a record works.
		//Gets an Participant from the repo and stores it in an object, deletes the Participant from repo, 
		//modifies object, saves object to repo. Reads the object back and verifies that the updated object was correctly saved.
		Participant participant = repo.get("18049369");
		repo.remove(participant);
		participant.setPerson_name("Thomas Jefferson");
		repo.save(participant);
		
		Participant participant2 = repo.get("18049369");
		assertTrue("Thomas Jefferson".equals(participant2.getPerson_name()));
	}
	
	
	@Test
	public void testGet() {
		//Testing basic retrieval of data from Database
		Participant participant = repo.get("18049369");
		
		assertEquals("18049369", participant.getPerson_id());
		assertEquals("Estate of James Hazen Hyde", participant.getPerson_name());
		assertEquals("http://collection.cooperhewitt.org/people/18049369/", participant.getPerson_url());
		assertEquals("", participant.getPerson_date());	
	}
	



	@Test
	public void testRemove() 
	//method that removes an Participant from the repository
	{
		List<Participant> participants = repo.findAll();
		Participant participant = repo.get("18049369");	
		
		assertEquals(18, participants.size());		
		
		repo.remove(participant);
		assertNull(repo.get("6000"));			
	
		List<Participant> participants2 = repo.findAll();		
		assertEquals(17, participants2.size());			
	}

	
	@Test
	public void testFindAll() 
	{
		//test method that verifies the correct amount of records are returned.
		List<Participant> participants = repo.findAll();
		assertEquals(18, participants.size());		
	}
}
