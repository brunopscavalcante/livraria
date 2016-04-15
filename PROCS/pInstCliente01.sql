CREATE PROCEDURE `pInstCliente01` ( 
	IN nome VARCHAR(50), 
    IN email VARCHAR(50), 
    IN endereco VARCHAR(100),
    IN dataNascimento DATE
)
BEGIN
		INSERT INTO Cliente (nome, email, endereco, dataNascimento)
        VALUES (nome, email, endereco, dataNascimento);
END