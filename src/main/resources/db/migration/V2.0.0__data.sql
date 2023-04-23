INSERT INTO roles(role_name, description) VALUES('user', 'May create, edit, delete user account');
INSERT INTO roles(role_name, description) VALUES('admin', 'May delete users accounts');
INSERT INTO roles(role_name, description) VALUES('manager', 'May create, edit, delete course-epack');

INSERT INTO epacks(category_id, epack_name, manager_name, description, price, date_created, last_updated, is_active, urllink) VALUES(1, 'Java Advanced', 'One Geek', 'JDK 19 news', 60.00, '2022-06-18T10:34:09','2023-02-18T10:34:09',  1, 'https://www.geeksforgeeks.org/java/');
INSERT INTO epacks(category_id, epack_name, manager_name, description, price, date_created, last_updated, is_active, urllink) VALUES(1, 'Spring/Spring Boot', 'Another Geek', 'Master skills in all kinds of application', 70.00, '2022-07-18T11:35:09', '2023-02-18T10:34:09', 1, 'https://www.geeksforgeeks.org/python-programming-language/');
INSERT INTO epacks(category_id, epack_name, manager_name, description, price, date_created, last_updated, is_active, urllink) VALUES(2, 'Microservices', 'The third Geek', 'Best practices', 80.00, '2023-02-18T22:00:00','2023-03-03T10:34:09',  1, 'https://www.geeksforgeeks.org/javascript/');

INSERT INTO categories(category_name, date_created, last_updated) VALUES('Programming', '2023-02-18T22:00:00','2023-03-03T10:34:09');
INSERT INTO categories(category_name, date_created, last_updated) VALUES('DevOps','2023-02-18T22:00:00','2023-03-03T10:34:09' );
INSERT INTO categories(category_name, date_created, last_updated) VALUES('Interchangable', '2023-02-18T22:00:00','2023-03-03T10:34:09');