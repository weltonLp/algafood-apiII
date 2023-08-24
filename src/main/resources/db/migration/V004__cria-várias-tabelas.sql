CREATE TABLE forma_pagamento(
	id bigint primary key not null auto_increment,
    descricao varchar (100) not null

)engine=InnoDB default charset=utf8;

CREATE TABLE permissao(
	id bigint primary key not null auto_increment,
    nome varchar(60) not null,
    descricao varchar(255) 
    
    
)engine=InnoDB default charset=utf8;
CREATE TABLE grupo(
	id bigint primary key not null auto_increment,
    nome varchar(60) not null
    
)engine=InnoDB default charset=utf8;
CREATE TABLE usuario(
	id bigint primary key not null auto_increment,
	nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(100) not null,
    data_cadastro datetime
)engine=InnoDB default charset=utf8;


CREATE TABLE restaurante(
	id bigint primary key not null auto_increment,
	nome varchar(60) not null,
	taxa_frete decimal(19,2) not null,
    ativo boolean,
    aberto boolean,
	data_cadastro datetime not null,
    data_atualizacao datetime not null,
    cozinha_id bigint not null,
    endereco_logradouro varchar(100) not null,
    endereco_complemento varchar(100) not null,
    endereco_cep varchar(100) not null,
    endereco_numero varchar(100) not null,
    endereco_bairro varchar(100) not null,
    endereco_cidade_id bigint not null,
    
    constraint FK_restaurante_cidade foreign key (endereco_cidade_id) references cidade(id),
    CONSTRAINT FK_restaurante_cozinha foreign key (cozinha_id) references cozinha(id)
    
)engine=InnoDB default charset=utf8;
CREATE TABLE produto(
	id bigint primary key not null auto_increment,
	nome varchar(100) not null,
    descricao varchar(100) not null,
    preco decimal(19,2) not null,
    ativo boolean,
    restaurante_id bigint not null,
    
    constraint foreign key (restaurante_id) references restaurante(id)
)engine=InnoDB default charset=utf8;


CREATE TABLE usuario_grupo(
	usuario_id bigint not null,
    grupo_id bigint not null
	
)engine=InnoDB default charset=utf8;

CREATE TABLE grupo_permissao(
	grupo_id bigint not null,
    permissao_id bigint not null
)engine=InnoDB default charset=utf8;

CREATE TABLE restaurante_forma_pagamento(
	restaurante_id bigint not null,
    forma_pagamento_id bigint not null
)engine=InnoDB default charset=utf8;

CREATE TABLE restaurante_usuario(
	restaurante_id bigint not null,
    usuario_id bigint not null
)engine=InnoDB default charset=utf8;
