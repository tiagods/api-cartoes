drop database clientes;

create database clientes;

create table account{
    Account_ID int not null;
    Document_Number int not null,
    primary key(Account_ID)
};
create table operation_type{
    OperationType_ID int not null,
    Description0 varchar not null,
    primary key (OperationType_ID)
};

create table transaction {
    Transaction_ID int auto_increment,
    Account_ID int not null,
    OperationType_ID int not null,
    Amount numeric(14,2) not null,
    EventDate datetime not null,
    primary key (Transaction_ID),
    CONSTRAINT fk_account_id foreign key (Account_ID) references account(Account_ID),
    CONSTRAINT fk_operationtype_id foreign key (OperationType_ID) references operation_type(OperationType_ID)
}