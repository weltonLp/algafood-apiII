insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Francesa');
insert into cozinha (nome) values ('Argentina');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Jacan Restaurante', 16.99, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Paolla Restaurante', 13.99, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Fogaça Restaurante', 14.55, 1);

insert into estado(nome) values ('Goiás'),('São Paulo'),('Rio de Janeiro');

insert into cidade(nome, estado_id) values ('Goiânia', 1),('Ribeirão Preto', 2), ('Niterói', 3);

insert into permissao (nome, descricao) values ('Júnior', 'Pouco privilégio no sistema'),('Sênior', 'Privilégio avançado');