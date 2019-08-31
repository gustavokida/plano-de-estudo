/* planoDeEstudo-ModelagemUML-projeto: */
DROP SCHEMA IF EXISTS alg CASCADE;

CREATE SCHEMA alg;

CREATE TABLE alg.Usuario (
    id INT SERIAL PRIMARY KEY,
    nome String,
    email String,
    celular String
);

CREATE TABLE alg.Materia (
    id INT SERIAL PRIMARY KEY,
    nome String,
    horario TIME,
    cargaHoraria INT,
    media FLOAT,
    aprovado BOOLEAN,
    observacao String,
    idUsuario INT
);

CREATE TABLE alg.Prova (
    id INT SERIAL PRIMARY KEY,
    nota FLOAT,
    data DATE,
    observacao String,
    idConteudo INT
);

CREATE TABLE alg.Conteudo (
    id INT SERIAL PRIMARY KEY,
    nome String,
    observacao String,
    data DATE,
    idMateria INT
);

CREATE TABLE alg.Tarefas (
    id INT SERIAL PRIMARY KEY,
    observacao String,
    data DATE,
    feito BOOLEAN,
    importante BOOLEAN,
    dataEntrega DATE,
    idConteudo INT
);
 
ALTER TABLE alg.Materia ADD CONSTRAINT FK_Materia_2
    FOREIGN KEY (idUsuario)
    REFERENCES Usuario (id);
 
ALTER TABLE alg.Prova ADD CONSTRAINT FK_Prova_2
    FOREIGN KEY (idConteudo)
    REFERENCES Conteudo (id);
 
ALTER TABLE alg.Conteudo ADD CONSTRAINT FK_Conteudo_2
    FOREIGN KEY (id)
    REFERENCES Prova (id);
 
ALTER TABLE Conteudo ADD CONSTRAINT FK_Conteudo_3
    FOREIGN KEY (idMateria)
    REFERENCES Materia (id);
 
ALTER TABLE Tarefas ADD CONSTRAINT FK_Tarefas_1
    FOREIGN KEY (idConteudo)
    REFERENCES Conteudo (id);
