package ch.puzzle.quickstart.model;

import javax.persistence.Entity;

@Entity
public class Department extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Boolean active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
