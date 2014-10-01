package ch.puzzle.quickstart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Department extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Boolean active;
	
	@OneToMany(mappedBy="department")
    private List<Employee> employees = new ArrayList<>();

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
