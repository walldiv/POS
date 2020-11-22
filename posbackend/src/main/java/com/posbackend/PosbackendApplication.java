package com.posbackend;

import com.posbackend.model.Employee;
import com.posbackend.service.EmployeeServiceImpl_PGR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PosbackendApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(PosbackendApplication.class, args); }

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeServiceImpl_PGR dao;

	private Employee thisEmp = new Employee("walldiv@gmail.com", "1234");


	@Override
	public void run(String... args) throws Exception {
		logger.info("EMPLOYEES -> {}", dao.findAll());
		logger.info("EMPLOYEE => {}", dao.getEmployeeByLogin(thisEmp));
	}

}
