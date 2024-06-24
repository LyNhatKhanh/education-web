
-- INSERT MEMBER -- 
INSERT INTO `test3`.`member` (`user_id`, `active`, `password`)
VALUES 
('nhatkhanh', '1', '{noop}test123'),
('lehang', '1', '{noop}test123'),
('chaddarby', '1', '{noop}test123'),
('lehang1', '1', '{noop}test123'),
('leminhchau', '1', '{noop}test123'),
('luonghienanh', '1', '{noop}test123'),
('khanhlinh', '1', '{noop}test123'),
('anhtuyet', '1', '{noop}test123');

-- INSERT PEOPLE -- 
INSERT INTO `test3`.`people` (`first_name`, `last_name`, `user_name`)
VALUES 
('Ly', 'Khanh', 'nhatkhanh'),
('Le', 'Hang', 'lehang'),
('Chad', 'Darby', 'chaddarby'),
('Le', 'Hang', 'lehang1'),
('Le', 'Minh Chau', 'leminhchau'),
('Luong', 'Hien Anh', 'luonghienanh'),
('Nguyễn', 'Trịnh Khánh Linh', 'khanhlinh'),
('Ánh', 'Tuyết', 'anhtuyet');

-- INSERT INSTRUCTOR -- 
INSERT INTO `test3`.`instructor` (`id`) 
VALUES 
('1'), ('2'), ('3'), ('4');

-- INSERT STUDENT -- 
INSERT INTO `test3`.`student` (`id`) 
VALUES 
('5'), ('6'), ('7'), ('8');

-- INSERT ROLE -- 
INSERT INTO `test3`.`role` (`role`, `user_id`) 
VALUES 
('ROLE_ADMIN', 'nhatkhanh'),
('ROLE_ADMIN', 'lehang'),
('ROLE_INSTRUCTOR', 'chaddarby'),
('ROLE_INSTRUCTOR', 'lehang1'),
('ROLE_STUDENT', 'leminhchau'),
('ROLE_STUDENT', 'luonghienanh'),
('ROLE_STUDENT', 'khanhlinh'),
('ROLE_STUDENT', 'anhtuyet');
