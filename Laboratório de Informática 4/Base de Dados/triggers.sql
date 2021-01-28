/**********************DELETES**********************/
/****************** use if needed ******************/
DROP TRIGGER IF EXISTS app.classificacao_media_restaurantes;
DROP TRIGGER IF EXISTS app.classificacao_media_ponto_interesse;
DROP TRIGGER IF EXISTS app.classificacao_media_hoteis;
DROP TRIGGER IF EXISTS app.versao_tabela_users_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_users_update;
DROP TRIGGER IF EXISTS app.versao_tabela_morada_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_morada_update;
DROP TRIGGER IF EXISTS app.versao_tabela_contactos_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_contactos_update;
DROP TRIGGER IF EXISTS app.versao_tabela_restaurantes_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_restaurantes_update;
DROP TRIGGER IF EXISTS app.versao_tabela_ponto_interesse_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_ponto_interesse_update;
DROP TRIGGER IF EXISTS app.versao_tabela_hoteis_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_hoteis_update;
DROP TRIGGER IF EXISTS app.versao_tabela_excursoes_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_excursoes_update;
DROP TRIGGER IF EXISTS app.versao_tabela_inscricao_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_inscricao_update;
DROP TRIGGER IF EXISTS app.versao_tabela_classificacao_restaurantes_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_classificacao_restaurantes_update;
DROP TRIGGER IF EXISTS app.versao_tabela_classificacao_pontointeresse_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_classificacao_pontointeresse_update;
DROP TRIGGER IF EXISTS app.versao_tabela_classificacao_hoteis_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_classificacao_hoteis_update;
DROP TRIGGER IF EXISTS app.versao_tabela_carteira_guia_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_carteira_guia_update;
DROP TRIGGER IF EXISTS app.versao_tabela_pedidos_payout_insert;
DROP TRIGGER IF EXISTS app.versao_tabela_pedidos_payout_update;
DROP TRIGGER IF EXISTS app.criar_carteira_guia;

/***************************************************/


/********* TRIGGERS classificacao_media *********/

	/* restaurantes */
delimiter //
CREATE TRIGGER classificacao_media_restaurantes
	AFTER INSERT ON classificacao_restaurantes
		FOR EACH ROW
        BEGIN
			UPDATE restaurantes
			SET classificacao_media = (SELECT AVG(valor) FROM classificacao_restaurantes
										WHERE classificacao_restaurantes.restaurante_id = restaurantes.id)
			WHERE restaurantes.id = NEW.restaurante_id;
		END;//
delimiter ;

	/* ponto_interesse */
delimiter //
CREATE TRIGGER classificacao_media_ponto_interesse
	AFTER INSERT ON classificacao_pontointeresse
		FOR EACH ROW
        BEGIN
			UPDATE ponto_interesse
			SET classificacao_media = (SELECT AVG(valor) FROM classificacao_pontointeresse
										WHERE classificacao_pontointeresse.ponto_interesse_id = ponto_interesse.id)
			WHERE ponto_interesse.id = NEW.ponto_interesse_id;
		END;//
delimiter ;

	/* hoteis */
delimiter //
CREATE TRIGGER classificacao_media_hoteis
	AFTER INSERT ON classificacao_hoteis
		FOR EACH ROW
        BEGIN
			UPDATE hoteis
			SET classificacao_media = (SELECT AVG(valor) FROM classificacao_hoteis
										WHERE classificacao_hoteis.hoteis_id = hoteis.id)
			WHERE hoteis.id = NEW.hoteis_id;
		END;//
delimiter ;

/************************************************/


/*********** TRIGGERS versoes_tabelas ***********/

/************** users **************/
delimiter //
CREATE TRIGGER versao_tabela_users_insert
	AFTER INSERT ON Users FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.Users = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_users_update
	AFTER UPDATE ON Users FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.Users = NOW();
        END;//
delimiter ;

/************** morada **************/
delimiter //
CREATE TRIGGER versao_tabela_morada_insert
	AFTER INSERT ON morada FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.morada = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_morada_update
	AFTER UPDATE ON morada FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.morada = NOW();
        END;//
delimiter ;

/************** contactos **************/
delimiter //
CREATE TRIGGER versao_tabela_contactos_insert
	AFTER INSERT ON contactos FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.contactos = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_contactos_update
	AFTER UPDATE ON contactos FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.contactos = NOW();
        END;//
delimiter ;

/************* restaurantes *************/
delimiter //
CREATE TRIGGER versao_tabela_restaurantes_insert
	AFTER INSERT ON restaurantes FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.restaurantes = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_restaurantes_update
	AFTER UPDATE ON restaurantes FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.restaurantes = NOW();
        END;//
delimiter ;

/************* pontos interesse *************/
delimiter //
CREATE TRIGGER versao_tabela_ponto_interesse_insert
	AFTER INSERT ON ponto_interesse FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.ponto_interesse = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_ponto_interesse_update
	AFTER UPDATE ON ponto_interesse FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.ponto_interesse = NOW();
        END;//
delimiter ;

/************** hoteis **************/
delimiter //
CREATE TRIGGER versao_tabela_hoteis_insert
	AFTER INSERT ON hoteis FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.hoteis = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_hoteis_update
	AFTER UPDATE ON hoteis FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.hoteis = NOW();
        END;//
delimiter ;

/************** excursões **************/
delimiter //
CREATE TRIGGER versao_tabela_excursoes_insert
	AFTER INSERT ON excursões FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.excursões = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_excursoes_update
	AFTER UPDATE ON excursões FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.excursões = NOW();
        END;//
delimiter ;

/************** inscrição **************/
delimiter //
CREATE TRIGGER versao_tabela_inscricao_insert
	AFTER INSERT ON inscrição FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.inscrição = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_inscricao_update
	AFTER UPDATE ON inscrição FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.inscrição = NOW();
        END;//
delimiter ;

/************** classificacao_restaurantes **************/
delimiter //
CREATE TRIGGER versao_tabela_classificacao_restaurantes_insert
	AFTER INSERT ON classificacao_restaurantes FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.classificacao_restaurantes = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_classificacao_restaurantes_update
	AFTER UPDATE ON classificacao_restaurantes FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.classificacao_restaurantes = NOW();
        END;//
delimiter ;

/************** classificacao_pontointeresse **************/
delimiter //
CREATE TRIGGER versao_tabela_classificacao_pontointeresse_insert
	AFTER INSERT ON classificacao_pontointeresse FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.classificacao_pontointeresse = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_classificacao_pontointeresse_update
	AFTER UPDATE ON classificacao_pontointeresse FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.classificacao_pontointeresse = NOW();
        END;//
delimiter ;

/************** classificacao_hoteis **************/
delimiter //
CREATE TRIGGER versao_tabela_classificacao_hoteis_insert
	AFTER INSERT ON classificacao_hoteis FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.classificacao_hoteis = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_classificacao_hoteis_update
	AFTER UPDATE ON classificacao_hoteis FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.classificacao_hoteis = NOW();
        END;//
delimiter ;

/************** Carteira_Guia **************/
delimiter //
CREATE TRIGGER versao_tabela_carteira_guia_insert
	AFTER INSERT ON Carteira_Guia FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.Carteira_Guia = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_carteira_guia_update
	AFTER UPDATE ON Carteira_Guia FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.Carteira_Guia = NOW();
        END;//
delimiter ;

/************** Pedidos_Payout **************/
delimiter //
CREATE TRIGGER versao_tabela_pedidos_payout_insert
	AFTER INSERT ON pedidos_payout FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.pedidos_payout = NOW();
        END;//
delimiter ;

delimiter //
CREATE TRIGGER versao_tabela_pedidos_payout_update
	AFTER UPDATE ON pedidos_payout FOR EACH ROW
        BEGIN
			UPDATE versoes_tabelas
			SET versoes_tabelas.pedidos_payout = NOW();
        END;//
delimiter ;

/*******************************************************/

/********* TRIGGER criar Carteira_Guia *********/
delimiter //
CREATE TRIGGER criar_carteira_guia
	AFTER INSERT ON users FOR EACH ROW
        BEGIN 
			IF (new.guia = 1) THEN
				INSERT INTO Carteira_Guia VALUES (new.user_id,'0.0');
            END IF;
        END;//
delimiter ;
/************************************************/


