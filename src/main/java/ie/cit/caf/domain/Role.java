package ie.cit.caf.domain;

/**
 * @author 
 * 
 * This class is used to store attributes about a Role that relates to Cultural Heritage Objects.
 * Jackson automatically extracts the data from the Json file for the attributes listed below.
 * Each Role can have: role_id, role_name, role_display_date, role_url.
 * 
 */

public class Role {
	//Role

	private String role_id;
	private String role_name;
	private String role_display_name;
	private String role_url;
	
	
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name
				+ ", role_display_name=" + role_display_name + ", role_url="
				+ role_url + "]";
	}
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_display_name() {
		return role_display_name;
	}
	public void setRole_display_name(String role_display_name) {
		this.role_display_name = role_display_name;
	}
	public String getRole_url() {
		return role_url;
	}
	public void setRole_url(String role_url) {
		this.role_url = role_url;
	}

}
