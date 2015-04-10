package ie.cit.caf.domain;

/**
 * @author 
 * 
 * This class is used to store attributes about Participants that have associations with Cultural Heritage Objects.
 * Jackson automatically extracts the data from the Json file for the attributes listed below.
 * Each Participant can have: person_id, person_name, person_date, person_url.
 * 
 */

public class Participant {

	//Participant
	private String person_id;
	private String person_name;
	private String person_date;
	private String person_url;
	
	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Participant [person_id=" + person_id + ", person_name="
				+ person_name + ", person_date=" + person_date
				+ ", person_url=" + person_url + "]";
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getPerson_date() {
		return person_date;
	}
	public void setPerson_date(String person_date) {
		this.person_date = person_date;
	}
	public String getPerson_url() {
		return person_url;
	}
	public void setPerson_url(String person_url) {
		this.person_url = person_url;
	}
	
	
	
}
