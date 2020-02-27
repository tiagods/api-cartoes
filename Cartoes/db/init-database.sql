create database clientes;

use clientes;

create table accounts(
    Account_ID int auto_increment,
    Document_Number bigint not null,
    primary key(Account_ID)
);

create table operation_type(
    Operation_Type_ID int auto_increment,
    Description0 varchar(255) default '',
    primary key(Operation_Type_ID)
);

create table transactions (
    Transaction_ID int auto_increment,
    Account_ID int not null,
    Operation_Type_ID int not null,
    Amount numeric(14,2) not null,
    Event_Date datetime not null,
    primary key (Transaction_ID),
    foreign key (Account_ID) references accounts(Account_ID),
    foreign key (Operation_Type_ID) references operation_type(Operation_Type_ID)
);

INSERT INTO accounts (Account_ID,Document_Number) values (1,12345678900);

INSERT INTO operation_type (Operation_Type_ID,Description0) values (1,'COMPRA A VISTA');
INSERT INTO operation_type (Operation_Type_ID,Description0) values (2,'COMPRA PARCELADA');
INSERT INTO operation_type (Operation_Type_ID,Description0) values (3,'SAQUE');
INSERT INTO operation_type (Operation_Type_ID,Description0) values (4,'PAGAMENTO');
INSERT INTO transactions VALUES
			(1,1,1,-50.00,'2020-02-05 09:34:18'),
			(2,1,1,-23.50,'2020-02-05 09:34:18'),
            (3,1,1,-18.70,'2020-02-05 09:34:18'),
            (4,1,4,60.00,'2020-02-05 09:34:18');