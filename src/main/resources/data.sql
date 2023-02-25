CREATE SEQUENCE blog_seq
    START WITH 200
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE blog (
  id NUMBER(8) NOT NULL,
  title VARCHAR2(50) NOT NULL,
  description VARCHAR2(400) NOT NULL,
   CONSTRAINT blog_pk PRIMARY KEY (id)
);

CREATE SEQUENCE reader_seq
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE reader (
   id NUMBER(8) NOT NULL,
   name VARCHAR2(8) NOT NULL,
   CONSTRAINT reader_pk PRIMARY KEY (id)
);

CREATE SEQUENCE blog_reader_seq
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE blog_reader (
    b_id NUMBER(8) NOT NULL,
    r_id NUMBER(8) NOT NULL,
    CONSTRAINT blog_reader_fk1 FOREIGN KEY (b_id) REFERENCES blog(id),
    CONSTRAINT blog_reader_fk2 FOREIGN KEY (r_id) REFERENCES reader(id)
);

CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;


CREATE TABLE users (
    id NUMBER(19) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    firstname VARCHAR2(50) NOT NULL,
    lastname VARCHAR2(50) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    password VARCHAR2(100) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE SEQUENCE roles_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE roles (
    id NUMBER (19) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

CREATE SEQUENCE users_roles_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE users_roles (
    user_id NUMBER NOT NULL,
    role_id NUMBER NOT NULL,
    CONSTRAINT user_role_fk1 FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT user_role_fk2 FOREIGN KEY (role_id) REFERENCES roles (id)
);