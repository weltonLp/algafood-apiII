CREATE TABLE pedido(
	id bigint primary key auto_increment not null,
    sub_total decimal(19,2) not null,
    taxa_frete decimal(19,2) not null,
    valor_total decimal(19,2) not null,
    data_criacao datetime,
    data_confirmacao datetime,
    data_cancelamento datetime,
    data_entrega datetime,
    status varchar(20),
    
    endereco_logradouro varchar(50),
    endereco_cep varchar(50),
    endereco_numero varchar(50),
    endereco_bairro varchar(50),
    endereco_complemento varchar(50),
    endereco_cidade_id bigint not null,
    
    usuario_id bigint not null,
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    
    
    CONSTRAINT FK_pedido_endereco_cidade_id foreign key (endereco_cidade_id) references cidade(id),
	CONSTRAINT FK_pedido_usuario_id foreign key(usuario_id) references usuario(id),
    CONSTRAINT FK_pedido_restaurante_id foreign key(restaurante_id) references restaurante(id),
    CONSTRAINT FK_pedido_forma_pagamento_id foreign key (forma_pagamento_id) references forma_pagamento(id)
   
	
)engine=InnoDB default charset=utf8;

CREATE TABLE itemPedido(
	id bigint primary key auto_increment not null,
    quantidade integer not null,
    preco_unitario decimal(9,2) not null,
    preco_total decimal(19,2) not null,
	pedido_id bigint not null,
    produto_id bigint not null,
    
    CONSTRAINT FK_itemPedido_pedido FOREIGN KEY (pedido_id) references pedido(id),
    CONSTRAINT FK_itemPedido_produto FOREIGN KEY (produto_id) references produto(id)
    
    
)engine=InnoDB default charset=utf8;

