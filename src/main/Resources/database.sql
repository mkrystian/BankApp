DROP TABLE db_bank;
DROP TABLE db_client;
DROP TABLE db_bank_client;
DROP TABLE db_account;


CREATE TABLE db_bank (
  bank_id   INT PRIMARY KEY,
  bank_name VARCHAR(50) UNIQUE,


);

CREATE TABLE db_client (
  client_id                INT PRIMARY KEY,
  client_pesel             VARCHAR(11) UNIQUE,
  client_gender            VARCHAR(1)  NOT NULL,
  client_initial_overdraft INT,
  client_email             VARCHAR(50) NOT NULL,
  client_city              VARCHAR(50) NOT NULL
);

CREATE TABLE db_bank_client (
  bank_client_id        INT PRIMARY KEY,
  bank_client_bank_id   INT,
  bank_client_client_id INT,
  FOREIGN KEY (bank_client_bank_id) REFERENCES db_bank (bank_id),
  FOREIGN KEY (bank_client_client_id) REFERENCES db_client (client_id)

);

CREATE TABLE db_account (
  account_id             INT PRIMARY KEY,
  account_bank_client_id INT         NOT NULL,
  account_type           VARCHAR(20) NOT NULL,
  account_balance        INT         NOT NULL,
  account_overdraft      INT,
  account_is_active      BOOLEAN,
  FOREIGN KEY (account_bank_client_id) REFERENCES db_bank_client (bank_client_id)
);
