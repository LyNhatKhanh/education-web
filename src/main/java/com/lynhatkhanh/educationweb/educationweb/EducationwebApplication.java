package com.lynhatkhanh.educationweb.educationweb;

import com.lynhatkhanh.educationweb.educationweb.dao.MemberDao;
import com.lynhatkhanh.educationweb.educationweb.model.Member;
import org.h2.command.Command;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EducationwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationwebApplication.class, args);
	}

}
