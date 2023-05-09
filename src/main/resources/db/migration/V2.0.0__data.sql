INSERT INTO roles(role_name, description) VALUES('user', 'May create, edit, delete user account');
INSERT INTO roles(role_name, description) VALUES('admin', 'May delete users accounts');
INSERT INTO roles(role_name, description) VALUES('manager', 'May create, edit, delete course-epack');

INSERT INTO epacks(category_id, epack_name, duration_h, manager_name, description, price, date_created, last_updated, is_active, urllink) VALUES(1, 'Java Advanced', 120, 'One Geek', 'JDK 19 news', 60.00, '2022-06-18','2023-02-18',  1, 'java.html');
INSERT INTO epacks(category_id, epack_name, duration_h, manager_name, description, price, date_created, last_updated, is_active, urllink) VALUES(1, 'Spring/Spring Boot', 160, 'Another Geek', 'Master skills in all kinds of application', 70.00, '2022-07-18', '2023-02-18', 1, 'spring.html');
INSERT INTO epacks(category_id, epack_name, duration_h, manager_name, description, price, date_created, last_updated, is_active, urllink) VALUES(2, 'Microservices', 60, 'The third Geek', 'Best practices', 80.00, '2023-02-18','2023-03-03',  1, 'micro.html');

INSERT INTO categories(category_name, date_created, last_updated) VALUES('Programming', '2023-02-18','2023-03-03');
INSERT INTO categories(category_name, date_created, last_updated) VALUES('DevOps','2023-02-18','2023-03-03' );
INSERT INTO categories(category_name, date_created, last_updated) VALUES('Interchangable', '2023-02-18','2023-03-03');