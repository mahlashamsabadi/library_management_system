CREATE TABLE "user" (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE person (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT UNIQUE REFERENCES "user"(id) ON DELETE CASCADE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
	national_code varchar(50) not null,
    phone VARCHAR(20),
    birth_date DATE
);
CREATE TABLE book_status (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT
);

CREATE TABLE reservation_status (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT
);
CREATE TABLE role (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
	effective_date DATE not NULL,
	disable_date DATE,
    description TEXT
);

CREATE TABLE user_role (
    user_id INT REFERENCES "user"(id) ON DELETE CASCADE,
    role_id INT REFERENCES role(id) ON DELETE CASCADE,
	effective_date DATE not NULL,
	disable_date DATE,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE resource (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
	effective_date DATE not NULL,
	disable_date DATE
);

CREATE TABLE resource_role (
    resource_id INT REFERENCES resource(id) ON DELETE CASCADE,
    role_id INT REFERENCES role(id) ON DELETE CASCADE,
	effective_date DATE not NULL,
	disable_date DATE,
    PRIMARY KEY (resource_id, role_id)
);

CREATE TABLE position (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE position_role (
    position_id INT REFERENCES position(id) ON DELETE CASCADE,
    role_id INT REFERENCES role(id) ON DELETE CASCADE,
    PRIMARY KEY (position_id, role_id)
);

CREATE TABLE person_position (
    person_id INT REFERENCES person(id) ON DELETE CASCADE,
    position_id INT REFERENCES position(id) ON DELETE CASCADE,
	effective_date DATE not NULL,
	disable_date DATE,
    PRIMARY KEY (person_id, position_id)
);

CREATE TABLE book (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    status_id INT REFERENCES book_status(id) NOT NULL
);

CREATE TABLE loan (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_id INT REFERENCES book(id) NOT NULL,
    user_id INT REFERENCES "user"(id) NOT NULL,
    staff_id INT REFERENCES "user"(id) NOT NULL,
    checkout_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    due_date DATE NOT NULL,
    returned_date TIMESTAMP WITH TIME ZONE
);

CREATE TABLE reservation (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_id INT REFERENCES book(id) NOT NULL,
    person_id INT REFERENCES person(id) NOT NULL,
    reservation_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    status_id INT REFERENCES reservation_status(id) NOT NULL
);
