insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Francesa');
insert into cozinha (nome) values ('Argentina');


insert into estado(nome) values ('Goiás'),('São Paulo'),('Rio de Janeiro');

insert into cidade(nome, estado_id) values ('Goiânia', 1),('Ribeirão Preto', 2), ('Niterói', 3);

insert into permissao (nome, descricao) values ('Júnior', 'Pouco privilégio no sistema'),('Sênior', 'Privilégio avançado');

insert into forma_pagamento (descricao) value ('Cartão de Crédito'), ('Cartão de Débito'), ('PIX'); 





insert into restaurante (nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_cep, endereco_numero, endereco_bairro, endereco_complemento, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Jacan Restaurante', 16.99, 2, 'Av.Guilherme de Freitas', '75000-000', '2558D', 'Vila Sul', 'Próx à Rede Bandeirantes de Comunucação', 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_cep, endereco_numero, endereco_bairro, endereco_complemento, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Paolla Restaurante', 13.99, 3, 'Av. Coronel Lima', '75488-080', '2558D', 'Zona Norte', 'Próx à Rede Bandeirantes de Comunucação', 3, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_cep, endereco_numero, endereco_bairro, endereco_complemento, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Fogaça Restaurante', 14.55, 1, 'Av. Coronel Dias', '75488-080', '2558D', 'Zona Norte', 'Próx à Rede Bandeirantes de Comunucação', 1, utc_timestamp, utc_timestamp);

insert into restaurante_forma_pagamento( restaurante_id, forma_pagamento_id) values (1,1), (2,2), (3,1), (3,2), (3,3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Petit Gateut','Bolo com sorvete por dentro',78.99,true, 1),('Lagosta','Lagosta assada inteira com molho branco',388.99,true, 2), ('Picanha', 'Picanha assada na grelha', 130.88, true, 3);

insert into grupo (nome) values ('Vallentine'), ('Rooyters');

insert into grupo_permissao(grupo_id, permissao_id) values (1,2), (2,1);

insert into usuario(nome, email, senha, data_cadastro) values ('José Alves', 'j@email.com', '123JJ', utc_timestamp),('Maria Aguiar Alves', 'mary@email.com', '123JJ', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1,2),(2,2);