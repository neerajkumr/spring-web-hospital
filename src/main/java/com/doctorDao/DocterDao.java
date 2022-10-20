package com.doctorDao;

import java.util.List;

import com.doctor.Doctor;
import com.doctor.Patient;

public interface DocterDao {
	public Integer saveDocter(final Doctor d, final Patient p);

	public Integer saveOrUpdatePatient(final Patient p);

	public List<Doctor> getAllDoctors();

	public List<Patient> getAllPatients();

	public Boolean getPatientByDocId(final int id);

	public Doctor getDocbyId();
}
