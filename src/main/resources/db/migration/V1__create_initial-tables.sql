CREATE TABLE users (
    id_user UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE notebook (
    id_notebook UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE notebook_membership (
    id_user UUID,
    id_notebook UUID,
    role VARCHAR(6) NOT NULL CHECK ( role in ('OWNER', 'EDITOR', 'VIEWER') ),
    PRIMARY KEY (id_user, id_notebook),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE ,
    FOREIGN KEY (id_notebook) REFERENCES notebook(id_notebook) ON DELETE CASCADE

);