package com.configration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.doctorDao.DoctorRepository;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class JdbcConfig {

	@Bean
	public DataSource data() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/hospitals",
				"root", "root");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbc = new JdbcTemplate(data());
		return jdbc;
	}

	@Bean
	public DoctorRepository Dao() {
		DoctorRepository nDao = new DoctorRepository();
		nDao.setJd(jdbcTemplate());
		return nDao;
	}

}
