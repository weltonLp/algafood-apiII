CREATE TABLE cidade(
	id bigint primary key not null auto_increment,
    nome varchar(60) not null,
    estado_id bigint not null,
    
    constraint Fk_cidade_estado foreign key (estado_id) references estado(id)
    
)engine=InnoDB default charset=utf8;