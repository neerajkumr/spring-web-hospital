package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.Doctor;
import com.doctor.Patient;
import com.doctorDao.DoctorRepository;
import com.google.gson.Gson;

@RestController
public class ControllerClass {

	@Autowired
	public DoctorRepository docRep;

	@GetMapping(path = "/Alldoc", produces = "application/json")
	@ResponseBody
	public String getDoc() {
		return (new Gson().toJson(docRep.getAllDoctors()));
	}

	@GetMapping(path = "/All/Allpat", produces = "application/json")
	public String getPat() {
		return (new Gson().toJson(docRep.getAllPatients()));
	}

	@GetMapping(path = "/getPatByDocId/{id}", produces = "application/json")
	public String getDoc(@PathVariable int id) {
		return (new Gson().toJson(docRep.getPatientByDocId(id)));
	}

	@PostMapping(path = "/Getboth", consumes = "application/json", produces = "application/json")
	public String InsertBoth(@RequestBody Doctor d) {
		Patient p = d.getPat().get(0);
		return (new Gson().toJson(docRep.saveDocter(d, p)));
	}

}
