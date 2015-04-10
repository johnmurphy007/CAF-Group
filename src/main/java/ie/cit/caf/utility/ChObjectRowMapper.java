package ie.cit.caf.utility;

import ie.cit.caf.domain.ChObject;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author 
 * 
 * This class provides the mapping from a SQL resultset to an object. It returns the populated object when complete. 
 * The SQL table attributes are extracted using the rs.getString(<table attribute name here>)
 * 
 * In this case the object is a Cultural Heritage Object (ChObject)
 */
public class ChObjectRowMapper implements RowMapper<ChObject> {

	@Override
	public ChObject mapRow(ResultSet rs, int index) throws SQLException {
		
		ChObject object = new ChObject();
		
		object.setCreditline(rs.getString("creditline"));
		object.setDate(rs.getString("date"));
		object.setDescription(rs.getString("description"));
		object.setGallery_text(rs.getString("gallery_text"));
		object.setId(rs.getString("id"));
		object.setMedium(rs.getString("medium"));
		object.setTitle(rs.getString("title"));
		
		return object;
	}
}
