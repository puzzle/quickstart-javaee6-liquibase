package ch.puzzle.quickstart.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Department department;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
