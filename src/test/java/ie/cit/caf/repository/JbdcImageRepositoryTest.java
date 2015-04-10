package ie.cit.caf.repository;

import org.springframework.dao.DuplicateKeyException; 
import static org.junit.Assert.*;
import ie.cit.caf.domain.Image;

import java.util.List;

import ie.cit.caf.DefaultConfig;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
* @author 
* 
* This is the test class for JdbcImageRepository class.
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
public class JbdcImageRepositoryTest {

	@Autowired
	JdbcImageRepository repo;
	
	
	@Test(expected=DuplicateKeyException.class)
	public void testSave3() 
	{
		//Verifies that if duplicate records are saved to repository that an exception will occur
		
		Image image = new Image();			//Create new Image and update attributes
		image.setImage_id("4");
		image.setChObjectId("682683");
		image.setHeight(1000);
		image.setIs_primary("2");
		image.setResolution("b");
		image.setUrl("https://images.collection.cooperhewitt.org/test");
		image.setWidth(500);
		
		repo.save(image);			//add image to repo
		repo.save(image);			//add duplicate record (not allowed...should throw exception)
	}
	
	@Test
	public void testSave2() 
	{
		//Verifies that modification of a record works.
		//Gets an image from the repo and stores it in an object, deletes the image from repo, 
		//modifies object, saves object to repo. Reads the object back and verifies that the updated object was correctly saved.
		Image image = repo.get("4249","b");
		repo.remove(image);
		image.setIs_primary("10");
		repo.save(image);
		
		Image image2 = repo.get("4249","b");
		assertTrue("10".equals(image2.getIs_primary()));
	}
	
	
	public void testSave() 
	{
		//Verifies the the number of items in Repository increase by 1 as a result of save operation
		List<Image> images = repo.findAll();
		assertEquals(90, images.size());			

		Image image = new Image();
		image.setImage_id("4");
		image.setChObjectId("682683");
		image.setHeight(1000);
		image.setIs_primary("2");
		image.setResolution("b");
		image.setUrl("https://images.collection.cooperhewitt.org/test");
		image.setWidth(500);
		
		repo.save(image);	
		repo.save(image);
		repo.save(image);
		
	List<Image> images2 = repo.findAll();		
		assertEquals(92, images2.size());			
	}
	
	
	@Test
	public void testGet() {
		//Testing basic retrieval of data from Database
		Image image = repo.get("4249","b");
		
		assertEquals("4249", image.getImage_id());
		assertEquals("68268397", image.getChObjectId());
		assertEquals(1024, image.getHeight());
		assertEquals("https://images.collection.cooperhewitt.org/4249_361c5a6095da4172_b.jpg", image.getUrl());
		assertEquals("1", image.getIs_primary());
		assertEquals("b", image.getResolution());
		assertEquals(846, image.getWidth());	
	}



	@Test
	public void testRemove() 
	//method that removes an image from the repository
	{
		List<Image> images = repo.findAll();
		Image image = repo.get("4249","b");	
		
		assertEquals(90, images.size());		
		
		repo.remove(image);
		assertNull(repo.get("4249","b"));			
	
		List<Image> images2 = repo.findAll();		
		assertEquals(89, images2.size());			
	}

	
	@Test
	public void testFindAll() 
	{
		//test method that verifies the correct amount of records are returned.
		List<Image> images = repo.findAll();
		assertEquals(90, images.size());		
	}
}
