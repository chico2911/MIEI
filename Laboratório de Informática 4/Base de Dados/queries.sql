
use app; 

/******************************************************************/
/***************** APENAS COMO ARQUIVO DE QUERIES *****************/
/******************************************************************/


/*****************LOGIN*****************/

/* 1 -> success */
DELIMITER //
CREATE FUNCTION CheckPassword (usernameCheck VARCHAR(45), passwordCheck VARCHAR(45))
    RETURNS BOOL
    NOT DETERMINISTIC
    READS SQL DATA
BEGIN
    RETURN EXISTS (SELECT username FROM users WHERE username = usernameCheck AND password = passwordCheck);
END;
//
DELIMITER ;

/* FUNCTION CALL EXAMPLE */
/* SELECT CheckPassword('alex','teste'); */

/* mais simples */
SELECT 1 AS LOGIN FROM users
WHERE username='alex' && password='teste';


/*****************RESTAURANTES*****************/

SELECT * FROM restaurantes;

SELECT * FROM restaurantes WHERE restaurantes.nome='Tasquinha Bracarense';

/* Moradas */
SELECT restaurantes.nome, morada.idMorada, morada.latitude, morada.longitude, morada.cidade, morada.codigo_postal, morada.morada
FROM restaurantes,morada
WHERE restaurantes.Morada_idMorada = morada.idMorada;

DELIMITER //
CREATE PROCEDURE RestauranteCidade (IN cidadeCheck VARCHAR(45))
BEGIN
    SELECT restaurantes.nome, morada.cidade
	FROM restaurantes, morada
	WHERE restaurantes.Morada_idMorada = morada.idMorada AND morada.cidade=cidadeCheck;
END;
//
DELIMITER ;

/* PROCEDURE CALL EXAMPLE */
/* CALL RestauranteCidade('Braga'); */

/* BEST */
SELECT restaurantes.nome, restaurantes.image_path, restaurantes.descricao,restaurantes.id,restaurantes.classificacao_media
FROM restaurantes
ORDER BY restaurantes.classificacao_media DESC
LIMIT 1;

/* Contactos */
SELECT restaurantes.nome, contactos.idContactos, contactos.email, contactos.telemovel, contactos.telefone
FROM restaurantes,contactos
WHERE restaurantes.Contactos_idContactos = contactos.idContactos;


/*****************PONTOS INTERESSE*****************/

SELECT * FROM ponto_interesse;

SELECT * FROM ponto_interesse WHERE ponto_interesse.nome='Bom Jesus do Monte';

/* Moradas */
SELECT ponto_interesse.nome, morada.idMorada, morada.latitude, morada.longitude, morada.cidade, morada.codigo_postal, morada.morada
FROM ponto_interesse,morada
WHERE ponto_interesse.Morada_idMorada = morada.idMorada;

DELIMITER //
CREATE PROCEDURE PontoInteresseCidade (IN cidadeCheck VARCHAR(45))
BEGIN
    SELECT ponto_interesse.nome, morada.cidade
	FROM ponto_interesse, morada
	WHERE ponto_interesse.Morada_idMorada = morada.idMorada AND morada.cidade=cidadeCheck;
END;
//
DELIMITER ;

/* PROCEDURE CALL EXAMPLE */
/* CALL PontoInteresseCidade('Braga'); */

/* BEST */
SELECT ponto_interesse.nome, ponto_interesse.image_path, ponto_interesse.descricao, ponto_interesse.id, ponto_interesse.classificacao_media
FROM ponto_interesse
ORDER BY ponto_interesse.classificacao_media DESC
LIMIT 1;

/* Contactos */
SELECT ponto_interesse.nome, contactos.idContactos, contactos.email, contactos.telemovel, contactos.telefone
FROM ponto_interesse,contactos
WHERE ponto_interesse.Contactos_idContactos = contactos.idContactos;

/*****************HOTEIS*****************/

SELECT * FROM hoteis;

SELECT * FROM hoteis WHERE hoteis.nome='Meliá Braga Hotel & Spa';

/* Moradas */
SELECT hoteis.nome, morada.idMorada, morada.latitude, morada.longitude, morada.cidade, morada.codigo_postal, morada.morada
FROM hoteis,morada
WHERE hoteis.Morada_idMorada = morada.idMorada;

DELIMITER //
CREATE PROCEDURE HoteisCidade (IN cidadeCheck VARCHAR(45))
BEGIN
    SELECT hoteis.nome, morada.cidade
	FROM hoteis, morada
	WHERE hoteis.Morada_idMorada = morada.idMorada AND morada.cidade=cidadeCheck;
END;
//
DELIMITER ;

/* PROCEDURE CALL EXAMPLE */
/* CALL HoteisCidade('Braga'); */

/* BEST */
SELECT hoteis.nome, hoteis.image_path, hoteis.descricao, hoteis.id, hoteis.classificacao_media
FROM hoteis
ORDER BY hoteis.classificacao_media DESC
LIMIT 1;

/* Contactos */
SELECT hoteis.nome, contactos.idContactos, contactos.email, contactos.telemovel, contactos.telefone
FROM hoteis,contactos
WHERE hoteis.Contactos_idContactos = contactos.idContactos;


/**************Controlo de Versões**************/

SELECT versoes_bd.Versao, versoes_bd.ReleaseDateTime
FROM versoes_bd
ORDER BY versoes_bd.ReleaseDateTime DESC
LIMIT 1;


/*****************EDIT ACCOUNT*****************/

DELIMITER //
CREATE PROCEDURE ChangePassword (IN emailCheck VARCHAR(45), IN passwordNew VARCHAR(45))
BEGIN
    UPDATE users
    SET users.password = passwordNew
    WHERE email = emailCheck;
END;
//
DELIMITER ;

/* PROCEDURE CALL EXAMPLE */
/* CALL ChangePassword('example@uminho.pt','new password'); */

DELIMITER //
CREATE PROCEDURE ChangeTelemovel (IN emailCheck VARCHAR(45), IN telemovelNew VARCHAR(45))
BEGIN
    UPDATE users 
    SET users.telemovel = telemovelNew
    WHERE email = emailCheck;
END;
//
DELIMITER ;

/* PROCEDURE CALL EXAMPLE */
/* CALL ChangeTelemovel('example@uminho.pt','new telemovel'); */


/***************** Comentários *****************/

SELECT users.username, classificacao_restaurantes.comentario, classificacao_restaurantes.valor
	FROM users, classificacao_restaurantes 
	WHERE classificacao_restaurantes.restaurante_id = 1 
		AND users.user_id = classificacao_restaurantes.user_id;

SELECT users.username , classificacao_pontointeresse.comentario, classificacao_pontointeresse.valor
	FROM users, classificacao_pontointeresse
    WHERE classificacao_pontointeresse.ponto_interesse_id = 1
		AND users.user_id = classificacao_pontointeresse.users_id;

SELECT users.username , classificacao_hoteis.comentario, classificacao_hoteis.valor
	FROM users, classificacao_hoteis
	WHERE classificacao_hoteis.hoteis_id = 1
		AND users.user_id = classificacao_hoteis.users_id;


/***************** Excursões *****************/
DELETE FROM inscrição 
	WHERE (SELECT 1 FROM excursões 
			WHERE inscrição.excursão_id=excursões.id
				AND excursões.guia_id = 1
				AND excursões.data_hora < now());
        
SELECT excursões.id, excursões.data_hora, excursões.ponto_interesse_id, excursões.guia_id, excursões.preço 
	FROM excursões 
    WHERE excursões.guia_id = 1
		AND excursões.data_hora < now();
        
SELECT excursões.id AS id, ponto_interesse.id AS idp, excursões.preço AS preço, excursões.data_hora AS date_hora, excursões.descricao AS descr 
	FROM excursões, ponto_interesse, morada 
    WHERE excursões.ponto_interesse_id = ponto_interesse.id 
		AND ponto_interesse.Morada_idMorada = morada.idMorada 
		AND morada.cidade = "Braga";

/***************** Carteira_Guia *****************/

UPDATE `app`.`carteira_guia` SET `Saldo` = 100 WHERE (`id_Guia` = '1');
        
/***********************************************/

