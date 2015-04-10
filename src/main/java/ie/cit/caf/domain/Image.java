package ie.cit.caf.domain;


/**
 * @author 
 * 
 * This class is used to store image attributes of interest from the Cultural Heritage Objects JSON files.
 * Jackson automatically extracts the data from the Json file into a Map<key,value> format for this object to use.
 * The "value" attributes are:
 * 		url, width, height, is_primary, image_id
 * The "key" attribute is the 'resolution'.  The key needs to be updated by the object that calls/uses this object.
 * Likewise, the chObjectId needs to be updated by the object that calls/uses this object.  chObjectId is the id of the
 * Cultural Heritage Object the images is associated with.
 *
 */

public class Image {
	
	private String url;
	private int width;
	private int height;
	private String is_primary;
	private String image_id;
	private String resolution;
	private String chObjectId;
	
	
	
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return "Image [url=" + url + ", width=" + width + ", height=" + height
				+ ", is_primary=" + is_primary + ", image_id=" + image_id
				+ ", resolution=" + resolution + ", chObjectId=" + chObjectId
				+ "]";
	}



	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getIs_primary() {
		return is_primary;
	}
	public void setIs_primary(String is_primary) {
		this.is_primary = is_primary;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getChObjectId() {
		return chObjectId;
	}

	public void setChObjectId(String chObjectId) {
		this.chObjectId = chObjectId;
	}

}
