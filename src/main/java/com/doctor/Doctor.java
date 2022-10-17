package com.doctor;

import java.util.List;

public class Doctor {
	private int id;
	private String name;
	private String specialist;
	private String experience;
	private List<Patient> pat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public List<Patient> getPat() {
		return pat;
	}

	public void setPat(List<Patient> pat) {
		this.pat = pat;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialist=" + specialist + ", experience=" + experience
				+ ", pat=" + pat + "]";
	}



}
