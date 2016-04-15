CREATE DEFINER=`root`@`localhost` PROCEDURE `pSeltCliente01`(
    IN _nome VARCHAR(50) )
BEGIN
	SET _nome = IFNULL(_nome, '');
	SELECT 
		id, nome, email, endereco, dataNascimento FROM Cliente
    WHERE 
		nome LIKE CONCAT('%', _nome, '%');        
END