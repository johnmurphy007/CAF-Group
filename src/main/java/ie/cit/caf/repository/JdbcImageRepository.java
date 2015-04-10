package ie.cit.caf.repository;

import ie.cit.caf.domain.Image;
import ie.cit.caf.utility.ImageRowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * 
 * This class implements the interface for ImagesRepository
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id & image resolution
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */

//Identify this class as Repository (Spring will detect it during @ComponentScan).
@Repository
public class JdbcImageRepository implements ImageRepository 
{
	
	//Using JdbcTemplate to provide repository access using SQL statements
	private JdbcTemplate jdbcTemplate;
	
	//Using Constructor DI, auto-inject the DataBase dependency connection into this object.
	@Autowired
	public JdbcImageRepository(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Image get(String id, String resolution) 
	{
		/* This method retrieves an Image based on a supplied String based 'id' & 'resolution'.
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
		
		String sql = "SELECT COUNT(*) FROM image WHERE image_id = ? AND image_res = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {id, resolution});
		
		if (count > 0)
		{
			sql = "SELECT * FROM image WHERE image_id = ? AND image_res = ?;";
			Image image = jdbcTemplate.queryForObject(sql, new Object [] {id, resolution}, new ImageRowMapper());

			return image;
		}
		else
			return null;
	}

	@Override
	public void save(Image image) 
	{
		//Implementing only the 'add()' part of 'save()' at present.
		
//		if (image.getImage_id() != null || image.getResolution() != null)
//		{
//			update(image);
//		}
//		else
//		{
			add(image);
//		}
	}

	private void add(Image image)
	{
		//Method to add a record to the repository
		String sql = "INSERT INTO image (image_id,image_res,chObject_id,is_primary,height,width,url) VALUES (?,?,?,?,?,?,?);";	
		jdbcTemplate.update(sql, new Object[] {
				image.getImage_id(), image.getResolution(), image.getChObjectId(),image.getIs_primary(),image.getHeight(), image.getWidth(), image.getUrl()});		
	}

	
	private void update(Image image)
	{
		//NOT IMPLEMENTED/TESTED
		//Method to update a record in the repository
		String sql = "UPDATE image SET chObject_id =?, height=?, is_primary = ?, url=?, width=? WHERE image_id = ? AND image_res = ?";
		jdbcTemplate.update(sql, new Object[] {image.getChObjectId(), image.getHeight(), image.getIs_primary(),
				image.getUrl(), image.getWidth(), image.getImage_id(), image.getResolution()});
	}
	
	@Override
	public void remove(Image image) 
	{
		//Method to delete a record from the repository
		String sql = "DELETE FROM image WHERE image_id = ? AND image_res = ?";
		jdbcTemplate.update(sql, new Object[] {image.getImage_id(), image.getResolution()});
	}

	@Override
	public List<Image> findAll() 
	{
		//Return a List of all Image(s) 
		String sql = "SELECT * FROM image";
		return jdbcTemplate.query(sql,  new ImageRowMapper());
	}

}
