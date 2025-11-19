-- Enums essenciais
CREATE TYPE marca_t AS ENUM ('VW','GM','FIAT','HONDA','MERCEDES');
CREATE TYPE estado_t AS ENUM ('NOVO','LOCADO','DISPONIVEL','VENDIDO');
CREATE TYPE categoria_t AS ENUM ('POPULAR','INTERMEDIARIO','LUXO');
CREATE TYPE tipo_t AS ENUM ('AUTOMOVEL','MOTOCICLETA','VAN');

-- Tabela de clientes
CREATE TABLE cliente (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  sobrenome VARCHAR(100) NOT NULL,
  rg VARCHAR(20),
  cpf VARCHAR(14) NOT NULL UNIQUE,
  endereco TEXT
);

-- Tabela de veículos
CREATE TABLE veiculo (
  id SERIAL PRIMARY KEY,
  tipo tipo_t NOT NULL,
  marca marca_t NOT NULL,
  estado estado_t NOT NULL,
  categoria categoria_t NOT NULL,
  modelo VARCHAR(50) NOT NULL,
  valor_compra NUMERIC(12,2) NOT NULL,
  placa VARCHAR(8) NOT NULL UNIQUE,
  ano INT NOT NULL
);

-- Tabela de locações
CREATE TABLE locacao (
  id SERIAL PRIMARY KEY,
  veiculo_id INT NOT NULL REFERENCES veiculo(id) ON DELETE RESTRICT,
  cliente_id INT NOT NULL REFERENCES cliente(id) ON DELETE RESTRICT,
  dias INT NOT NULL,
  valor NUMERIC(12,2) NOT NULL,
  data_locacao DATE NOT NULL
);



select * from cliente;

select * from locacao;

select * from veiculo;
