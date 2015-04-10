package ie.cit.caf.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author 
 * 
 * This class is used to capture the Participation associated with a Cultural Heritage Object.
 * 
 * @JsonUnwrapped does the parsing of Participation data from the Json file into the 'Participant' and 'Role' objects
 */


public class Participation {
	
	@JsonUnwrapped
	private Participant participant;
	@JsonUnwrapped
	private Role role;

	
	public Participation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Participation [participant=" + participant + ", role=" + role
				+ "]";
	}	
	
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	

}
