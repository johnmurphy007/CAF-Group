package ie.cit.caf.utility;

import ie.cit.caf.domain.Image;

import java.sql.ResultSet;
import java.sql.SQLException;




import org.springframework.jdbc.core.RowMapper;
/**
 * @author 
 * 
 * This class provides the mapping from a SQL resultset to an object. It returns the populated object when complete. 
 * The SQL table attributes are extracted using the rs.getString(<table attribute name here>) for String attributes, and 
 * rs.getInt(<table attribute name here>) for Integer attributes.
 * 
 * In this case the object is an Image
 */
public class ImageRowMapper implements RowMapper<Image> {

	@Override
	public Image mapRow(ResultSet rs, int index) throws SQLException {
		
		//Given that Image is now different to what was originally mapped...
		
		Image image = new Image();
		
		image.setImage_id(rs.getString("image_id"));
		image.setIs_primary(rs.getString("is_primary"));
		image.setHeight(rs.getInt("height"));
		image.setWidth(rs.getInt("width"));
		image.setUrl(rs.getString("url"));
		image.setChObjectId(rs.getString("chObject_id"));
		image.setResolution(rs.getString("image_res"));
		
		return image;
	}
}
