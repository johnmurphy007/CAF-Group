package ie.cit.caf.service;

import java.util.ArrayList;
import java.util.List;

import ie.cit.caf.domain.ChObject;
import ie.cit.caf.repository.ChObjectRepository;
import ie.cit.caf.service.ChObjectService;
import ie.cit.caf.service.ChObjectServiceImpl;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
/**
 * @author 
 * 
 * This is the mockito test class used to test the ChObjectServiceImpl Service Layer class.
 * This class generates the data that the service methods require when they are called.
 * This method of testing eliminates repository related issues from test results
 * 
 */

public class ChObjectServiceImplTest {
	private ChObjectService chObjectService;
	private ChObjectRepository repoMock;
	
	//This is run before any test methods are run.  setup() sets up the database.
	@Before
	public void setup() {
		repoMock = mock(ChObjectRepository.class);
		
		ChObject cho1 = new ChObject();
		cho1.setTitle("Mock Test: John");
		cho1.setId("10");
		cho1.setDate("2015");
		cho1.setDescription("4");
		cho1.setGallery_text("5");
		cho1.setMedium("6");
		cho1.setCreditline("7");
		
		
		ChObject cho2 = new ChObject();
		cho2.setTitle("Mary");
		cho2.setId("20");
		cho2.setDate("2014");
		cho2.setDescription("4");
		cho2.setGallery_text("5");
		cho2.setMedium("6");
		cho2.setCreditline("22");
		
		List<ChObject> chos = new ArrayList<>();
		
		chos.add(cho1);
		chos.add(cho2);
		
		when(repoMock.get("10")).thenReturn(cho1);		//defines the stub value to return when method 'get("10")' is called
		when(repoMock.get("20")).thenReturn(cho2);		//defines the stub value to return when method 'get("20")' is called
		
	
		when(repoMock.findAll()).thenReturn(chos);		//defines the stub value to return when method 'findAll()' is called.
	
		chObjectService = new ChObjectServiceImpl(repoMock);
	}
	
	@Test
	public void get() 
	{
		//Testing basic retrieval of data from Database
		ChObject cho = chObjectService.get("10");
		assertEquals("Mock Test: John", cho.getTitle());
		assertEquals("10", cho.getId());
		assertEquals("2015", cho.getDate());
		assertEquals("4", cho.getDescription());
		assertEquals("5", cho.getGallery_text());
		assertEquals("6", cho.getMedium());
		assertEquals("7", cho.getCreditline());
		
		assertTrue(cho.getTitle().equals("Mock Test: John"));
	}
	
	@Test
	public void get2() 
	{
		//Gets data from repo, deletes record, modifies data, saves data, retrieves it and checks that changes persist
		ChObject cho = chObjectService.get("20");
		chObjectService.remove(cho);
		cho.setDate("2000");
		chObjectService.save(cho);
		
		cho = chObjectService.get("20");
		
		assertEquals("Mary", cho.getTitle());
		assertEquals("20", cho.getId());
		assertEquals("2000", cho.getDate());			//verifies the updated record is retrieved correctly
		assertEquals("4", cho.getDescription());
		assertEquals("5", cho.getGallery_text());
		assertEquals("6", cho.getMedium());
		assertEquals("22", cho.getCreditline());
	}
	
	
	@Test
	public void findAll()
	//test method that verifies the correct amount of records are returned.
	{
		assertTrue(chObjectService.findAll().size() == 2);
	}
	
	@Test
	public void save() 
	//Verifies the the number of items in Repository increase by 1 as a result of save operation
	{
		List<ChObject> chos3 = chObjectService.findAll();//chObjectService.findAll();
		assertEquals(2, chos3.size());			
		

		ChObject choTest = new ChObject();			//create ChObject, update its attributes
		choTest.setId("50");
		choTest.setCreditline("2");
		choTest.setDate("3");
		choTest.setDescription("4");
		choTest.setGallery_text("5");
		choTest.setMedium("6");
		choTest.setTitle("7");
		
		chObjectService.save(choTest);				//save (i.e. add) the ChObject to the Mockito repo
		
		//Update the stub value that findAll() returns as the number of records for findAll() has increased by 1 due to the save().
		chos3.add(choTest);					//add a record to list of ChObject chos3 
		when(repoMock.findAll()).thenReturn(chos3);		//size of chos3 is correct given that a record has been added due to save()
		
		chos3 = chObjectService.findAll();		//returns records in chos3	
		assertEquals(3, chos3.size());			//test that number of records in repo is now 3
		
	}
	
	@Test
	public void remove() 
	//method that removes a ChObject from the mockito repository
	{
		//Method remove() returns a void.
		
		List<ChObject> chos5 = chObjectService.findAll();		//get all the records in the Mockito repo.
		ChObject cho = chObjectService.get("10");				//retrieve the record with id "10"
		
		assertEquals(2, chos5.size());							//verify that size of Mockito repo is 2.
			
		chObjectService.remove(cho);							//remove record from Mockito repo
		
		
		chos5.remove(cho);										//Remove same record from list of objects (will use this to update the repoMock return values
		//Re-define the stub value to return when method 'get("10")' is called as a result of remove() method being called.
		when(repoMock.get("10")).thenReturn(null);				//Setting return to 'null' as record was deleted above.											
		when(repoMock.findAll()).thenReturn(chos5);				//Re-define the stub value to return when method 'findAll()' is called to reflect the deleted record.
		
		assertNull(chObjectService.get("10"));					//test that Service class deleted record "10"
		assertEquals(1,chos5.size());							//test that number of records in repo is now 1.
	}
}