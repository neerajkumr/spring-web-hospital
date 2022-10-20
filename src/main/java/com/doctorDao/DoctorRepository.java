package com.doctorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.doctor.Doctor;
import com.doctor.Patient;

public class DoctorRepository implements DocterDao{

	private JdbcTemplate jd;

	public JdbcTemplate getJd() {
		return jd;
	}

	public void setJd(JdbcTemplate jd) {
		this.jd = jd;
	}
	List<Patient> p=new ArrayList<Patient>();

	public Integer saveDocter(final Doctor d, final Patient p) {
		String sql = "insert into doctor(doc_id,doc_name,specialist,experience) values(?,?,?,?)";
		Integer list = jd.execute(sql, new PreparedStatementCallback<Integer>() {
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, d.getId());
				ps.setString(2, d.getName());
				ps.setString(3, d.getSpecialist());
				ps.setString(4, d.getExperience());
				return ps.executeUpdate();
			}

		});
		saveOrUpdatePatient(p);
		return list;
	}

	public Integer saveOrUpdatePatient(final Patient p) {
		String sql = "insert into patient values(?,?,?,?)";
		Integer list = jd.execute(sql, new PreparedStatementCallback<Integer>() {
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, p.getId());
				ps.setString(2, p.getName());
				ps.setInt(3, p.getAge());
				ps.setInt(4, p.getDoc_id());
				return ps.executeUpdate();
			}
		});

		return list;

	}

	public List<Doctor> getAllDoctors() {
		String sql = "select * from doctor";
		//return jd.query(sql, BeanPropertyRowMapper.newInstance(Doctor.class));
		return jd.query(sql, new ResultSetExtractor<List<Doctor>>() {

			public List<Doctor> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Doctor> l = new ArrayList<Doctor>();
				while (rs.next()) {
					Doctor d = new Doctor();
					d.setId(rs.getInt(1));
					d.setName(rs.getString(2));
					d.setSpecialist(rs.getString(3));
					d.setExperience(rs.getString(4));
					l.add(d);

				}
				return l;
			}

		});
			
	}

	public List<Patient> getAllPatients() {
		String sql = "select * from patient";
		
	//	return jd.query(sql,BeanPropertyRowMapper.newInstance(Patient.class));
		List<Patient> plist = jd.query(sql, new RowMapper<Patient>() {

			public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
				Patient p = new Patient();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAge(rs.getInt(3));
				p.setDoc_id(rs.getInt(4));
				return p;
			}

		});
		return plist;
	}

	public Boolean getPatientByDocId(final int id) {
		String sql = "select * from patient where doc_id=?";
		return jd.execute(sql, new PreparedStatementCallback<Boolean>() {

			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, id);
				return ps.execute();
			}
		});
		
	}
	
	public Doctor getDocbyId() {
		String sql="select * from doctor where doc_id=?";
		return jd.queryForObject(sql, BeanPropertyRowMapper.newInstance(Doctor.class));
	}

}
