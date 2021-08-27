CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    celular VARCHAR(11),
    ativo BOOL NOT NULL,
    
    PRIMARY KEY (id_usuario)
);

CREATE TABLE permissao (
    id_permissao INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,

    PRIMARY KEY (id_permissao)
);

CREATE TABLE usuario_permissao (
    id_usuario_permissao INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_permissao INT NOT NULL,
    
    PRIMARY KEY (id_usuario_permissao),
    
    FOREIGN KEY (id_usuario)
    	REFERENCES usuario(id_usuario),
    
    FOREIGN KEY (id_permissao)
    	REFERENCES permissao(id_permissao)
);

INSERT INTO permissao (descricao) VALUES ("ROLE_ADMINISTRADOR"), ("ROLE_USUARIO"), ("ROLE_USUARIO_VIP");
