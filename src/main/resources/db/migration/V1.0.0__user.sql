USE usersdb;

DROP TABLE IF EXISTS roles;

CREATE TABLE users (
                        user_id bigint(20) NOT NULL AUTO_INCREMENT,
                        first_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                        last_name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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