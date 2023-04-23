USE usersdb;

DROP TABLE IF EXISTS roles;

CREATE TABLE users (
                        user_id bigint(20) NOT NULL AUTO_INCREMENT,
                        first_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        last_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        gender varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
                        username varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        email varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        password varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        date_of_birth date,
                        phone_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        occupation varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        date_created date,
                        PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE addresses (
                         address_id bigint(20) NOT NULL AUTO_INCREMENT,
                         user_id bigint(20) NOT NULL,
                         country varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         region varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         city varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         street varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         house_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         apartment_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
                         postal_code varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         PRIMARY KEY (address_id),
                         FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE workplaces (
                           workplace_id bigint(20) NOT NULL,
                           user_id bigint(20) NOT NULL,
                           company_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                           position varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                           company_phone_number varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
                           PRIMARY KEY (workplace_id),
                           FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE roles (
                        role_id bigint(20) NOT NULL AUTO_INCREMENT,
                        role_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        description varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE epacks (
                       epack_id bigint(20) NOT NULL AUTO_INCREMENT,
                       category_id bigint(8) NOT NULL,
                       epack_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                       manager_name varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                       description varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                       price decimal NOT NULL,
                       date_created date,
                       last_updated date,
                       is_active tinyint(1),
                       urllink varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                       PRIMARY KEY (epack_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE categories (
                       category_id bigint(20) NOT NULL AUTO_INCREMENT,
                       category_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                       date_created date,
                       last_updated date,
                       PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;