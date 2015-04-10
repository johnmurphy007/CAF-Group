package ie.cit.caf.repository;

import static org.junit.Assert.*;

import java.util.List;

import ie.cit.caf.DefaultConfig;
import ie.cit.caf.domain.ChObject;
import ie.cit.caf.repository.JdbcChObjectRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Ignore;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 
 * 
 * This is the test class for JdbcChObjectRepository class.
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
@SpringApplicationConfiguration(classes = DefaultConfig.class)

@ActiveProfiles("test")  //optional. overrides the default profile to use. yml file has..."default" (mysql) or "test" (h2)

//@TransactionConfiguration(defaultRollback=true)

//used for dirties context only:
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD) //Optional. For in-memory db, this will clean/reload the context (i.e. recreate tables etc).
	public class JdbcChObjectRepositoryTest {
		
		@Autowired
		JdbcChObjectRepository repoTest;
	
		
		@Test(expected=DuplicateKeyException.class)
		public void testSave3() 
		{
			//Verifies that if duplicate records are saved to repository that an exception will occur
			
			ChObject cho = repoTest.get("68268301");
		
			repoTest.save(cho);			//add chObject to repo
			repoTest.save(cho);			//add duplicate record (not allowed...should throw exception)
		}
		
		@Test
		public void testSave() 
				//Verifies the the number of items in Repository increase by 1 as a result of save operation

		{
			List<ChObject> chos = repoTest.findAll();
			assertEquals(26, chos.size());			
			
			ChObject choTest = new ChObject();
			choTest.setId("2");
			choTest.setCreditline("2");
			choTest.setDate("3");
			choTest.setDescription("4");
			choTest.setGallery_text("5");
			choTest.setMedium("6");
			choTest.setTitle("7");
			repoTest.save(choTest);			
			assertNotNull(choTest.getId());
			
			List<ChObject> chos2 = repoTest.findAll();		
			assertEquals(27, chos2.size());			
		}
		
		@Test
		public void testSave2() 
		{
			//Verifies that modification of a record works.
			//Gets a ChObject from the repo and stores it in an object, deletes the ChObject from repo, 
			//modifies object, saves object to repo. Reads the object back and verifies that the updated object was correctly saved.
			ChObject cho = repoTest.get("68268301");
			repoTest.remove(cho);
			cho.setDate("1900");
			cho.setDescription("test input");
			repoTest.save(cho);
			
			ChObject cho2 = repoTest.get("68268301");
			assertTrue("1900".equals(cho2.getDate()));
			assertTrue("test input".equals(cho2.getDescription()));
		}
		
		@Test
		public void testGet() {
			//Testing basic retrieval of data from Database
			ChObject cho = repoTest.get("68268301");
			assertEquals("68268301", cho.getId());
			assertEquals("Gift of Vlisco", cho.getCreditline());
			assertEquals("2011", cho.getDate());
			assertEquals("Women's high-heeled shoes. Printed in indigo, emerald green, lime green, and yellow on a white ground.", cho.getDescription());
			assertEquals(null, cho.getGallery_text());
			//assertEquals("Medium: 100% cotton Technique: wax-resist printed on plain weave", cho.getMedium());
			assertEquals("cotton",cho.getMedium());
			assertEquals("Textile (Netherlands), 2011", cho.getTitle());
		}
	


		@Test
		public void testRemove() 
				//method that removes a ChObject from the repository
		{
			List<ChObject> chos = repoTest.findAll();
			ChObject cho = repoTest.get("68268301");	
			
			assertEquals(26, chos.size());		
			
			repoTest.remove(cho);
			assertNull(repoTest.get("68268301"));			
		
			List<ChObject> chos2 = repoTest.findAll();		
			assertEquals(25, chos2.size());			
		}

		
		@Test
		public void testFindAll() 
				//test method that verifies the correct amount of records are returned.
		{
			List<ChObject> chos = repoTest.findAll();
			assertEquals(26, chos.size());		
		}

	}

	
