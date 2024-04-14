--Banco de dados PostGre

--tabela funcionario
create table funcionario (
	id uuid primary key,
	nome varchar(250) not null,
	cpf varchar(14) not null,
	matricula varchar(10) not null,
	salario numeric(10,2) not null
);

--tabela endereco
create table endereco (
	id uuid primary key,
	logradouro varchar(250) not null,
	complemento varchar(150) not null,
	numero varchar(50) not null,
	bairro varchar(150) not null,
	cidade varchar(150) not null,
	estado varchar(2) not null,
	cep varchar(10) not null,
	funcionario_id uuid not null,
	foreign key (funcionario_id) references funcionario (id)
);