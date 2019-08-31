CREATE DATABASE plano;

/* planoDeEstudo-ModelagemUML-projeto: */

CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR,
    email VARCHAR,
    celular VARCHAR
);

CREATE TABLE Materia (
    id SERIAL PRIMARY KEY,
    nome VARCHAR,
    horario TIME,
    cargaHoraria INT,
    media FLOAT,
    aprovado BOOLEAN,
    observacao VARCHAR
);

CREATE TABLE UsuarioMateria (
    id SERIAL PRIMARY KEY,
    idUsuario INT,
    idMateria INT,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id),
    FOREIGN KEY (idMateria) REFERENCES Materia(id)
);

CREATE TABLE Prova (
    id SERIAL PRIMARY KEY,
    nota FLOAT,
    data DATE,
    observacao VARCHAR,
    idConteudo INT,
    FOREIGN KEY (idConteudo) REFERENCES Conteudo(id)
);

CREATE TABLE Conteudo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR,
    observacao VARCHAR,
    data DATE,
    idMateria INT,
    FOREIGN KEY(idMateria) REFERENCES Materia(id)
);

CREATE TABLE Tarefas (
    id SERIAL PRIMARY KEY,
    observacao VARCHAR,
    data DATE,
    feito BOOLEAN,
    importante BOOLEAN,
    dataEntrega DATE,
    idConteudo INT,
    FOREIGN KEY(idConteudo) REFERENCES Conteudo(id)
);
 

