-- INSERT COURSE -- 
INSERT INTO `test3`.`course` (`id`, `instructor_id`, `title`) 
VALUES 
('1', '1', 'JSP, Servlets and JDBC for Beginners: Build a Database App'),
('2', '2', 'Hibernate: Advanced Development Techniques'),
('3', '2', '[NEW] Spring Boot 3, Spring 6 & Hibernate for Beginners'),
('4', '3', 'Spring Boot Unit Testing with JUnit, Mockito and MockMvc'),
('5', '3', 'Deploy Java Spring Apps Online to Amazon Cloud (AWS)'),
('6', '4', 'Full Stack: React and Java Spring Boot - The Developer Guide');

-- INSERT LECTURE -- 
TRUNCATE `test3`.`lecture`;
INSERT INTO `test3`.`lecture` (`course_id`, `content`, `title`) 
VALUES 
('1', 'Content', 'JSP, Servlets and JDBC for Beginners: Build a Database App'),
('2', 'Content', 'Hibernate: Advanced Development Techniques'),
('3', 'Content', '[NEW] Spring Boot 3, Spring 6 & Hibernate for Beginners'),
('4', 'Content', 'Spring Boot Unit Testing with JUnit, Mockito and MockMvc'),
('5', 'Content', 'Deploy Java Spring Apps Online to Amazon Cloud (AWS)'),
('6', 'Content', 'Full Stack: React and Java Spring Boot - The Developer Guide');