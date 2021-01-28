use app;

/*****************POVOAMENTO*****************/

/* versoes_bd - (Versao,ReleaseDateTime) -------  DATETIME: YYYY-MM-DD HH:MI:SS */
INSERT INTO versoes_bd VALUES ('1.0.0','2020-05-25 12:30:00');
INSERT INTO versoes_bd VALUES ('1.0.1','2020-05-25 12:35:00');
INSERT INTO versoes_bd VALUES ('1.0.2','2020-05-28 18:00:00');
INSERT INTO versoes_bd VALUES ('2.0.0','2020-06-12 11:30:00');
INSERT INTO versoes_bd VALUES ('2.1.0','2020-06-25 12:05:00');
INSERT INTO versoes_bd VALUES ('2.2.0','2020-07-03 11:10:00');



/* versoes_tabelas - (Users,morada,contactos,restaurantes,ponto_interesse,hoteis,excursões,inscrição,classificacao_restaurantes,classificacao_pontointeresse,classificacao_hoteis,carteira_guia,pedidos_payout) -------  DATETIME: YYYY-MM-DD HH:MI:SS */

INSERT INTO versoes_tabelas VALUES ('2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-16 17:00:00','2020-06-30 11:10:00','2020-07-03 11:10:00');


/* users - (user_id,email,username,primeiro_nome,ultimo_nome,telemovel,guia,password) */
INSERT INTO users VALUES (1,'autista@gmail.com','escuteiro','Arnaldo','Silva', '912345567',1,'teste');
INSERT INTO users VALUES (2,'lexandre@gmail.com','alex','Alexandre','Santos', '252890678',0,'teste');
INSERT INTO users VALUES (3,'ze@gmail.com', 'zedabranca','José','Chocapito', '973456789',0,'teste');


/* pedidos_payout - (id_guia,Valor_pagar,Pago (0 ou 1),IBAN) */
INSERT INTO pedidos_payout VALUES (1,20,1,'PT5034672323473');
INSERT INTO pedidos_payout VALUES (1,50,0,'PT5030325329999');


/* morada - (idMorada,latitude,longitude,cidade,codigo_postal,morada) */
INSERT INTO morada VALUES (1, '41.5577418','-8.3989134','Braga','4710-409','Rua Nova de Santa Cruz, 34');
INSERT INTO morada VALUES (2, '41.5423867','-8.4009138','Braga','4715-282','Rua Eng. Vilalobos 18');
INSERT INTO morada VALUES (3, '41.543588','-8.3766921','Braga','4715-056','Estrada do Bom Jesus, Tenões');
INSERT INTO morada VALUES (4, '41.549935','-8.427262','Braga','4700-424','Rua Dom Paio Mendes, Maximinos, Sé e Cividade');
INSERT INTO morada VALUES (5, '41.5566','-8.3966','Braga','4715-380','Av. Gen. Carrilho da Silva Pinto 8, Tenões');
INSERT INTO morada VALUES (6, '41.5489024','-8.4235369','Braga','4700-308','Largo Carlos Amarante, 150');
INSERT INTO morada VALUES (7, '41.5489024','-8.4235369','Porto','4050-546','R. de São Filipe de Nery');
INSERT INTO morada VALUES (8, '41.5489024','-8.4235369','Porto','4000-018','Pte. Luiz I');
INSERT INTO morada VALUES (9, '41.5489024','-8.4235369','Porto','4050-161','R. das Carmelitas 144');
INSERT INTO morada VALUES (10, '41.5489024','-8.4235369','Porto','4400-088','Rua do Choupelo');
INSERT INTO morada VALUES (11, '41.5489024','-8.4235369','Porto','4150-516','Av. de Montevideu 236');
INSERT INTO morada VALUES (12, '41.5489024','-8.4235369','Porto','4050-417','R. de Mouzinho da Silveira 218');
INSERT INTO morada VALUES (13, '41.5489024','-8.4235369','Porto','4050-253','R. do Comércio do Porto 191');
INSERT INTO morada VALUES (14, '41.5489024','-8.4235369','Vila Real','5000-651','Av. 1º de Maio 76');
INSERT INTO morada VALUES (15, '41.5489024','-8.4235369','Vila Real','5000-051','Estrada Nacional 322');
INSERT INTO morada VALUES (16, '41.5489024','-8.4235369','Vila Real','5000-570','Rua Monsenhor Jerónimo do Amaral');
INSERT INTO morada VALUES (17, '41.5489024','-8.4235369','Vila Real','5000-651','Av. 1º de Maio 188');
INSERT INTO morada VALUES (18, '41.5489024','-8.4235369','Vila Real','5000-291','Fundação da Casa de Mateus');
INSERT INTO morada VALUES (19, '41.5489024','-8.4235369','Vila Real',NULL,'Vila Real');
INSERT INTO morada VALUES (20, '41.5489024','-8.4235369','Coimbra','3000-175','Av. Fernão de Magalhães 25');
INSERT INTO morada VALUES (21, '41.5489024','-8.4235369','Coimbra','3000-226','R. João Machado 5');


/* contactos - (idContactos,email,telemovel,telefone) */
INSERT INTO contactos VALUES (1, 'nuno.fernandesesteves@gmail.com','924 151 329','253283123');
INSERT INTO contactos VALUES (2, 'info@degema.pt',NULL,'253254062');
INSERT INTO contactos VALUES (3, 'geral@bomjesus.pt','912 261 528','253676636');
INSERT INTO contactos VALUES (4, 'info@se-braga.pt',NULL,'253263317');
INSERT INTO contactos VALUES (5, 'melia.braga@meliaportugal.com',NULL,'253144000');
INSERT INTO contactos VALUES (6, 'braga@vilagale.com',NULL,'253146000');
INSERT INTO contactos VALUES (7, 'info@torredosclerigos.pt',NULL,'220 145 489');
INSERT INTO contactos VALUES (8, NULL,NULL,'22 013 3100');
INSERT INTO contactos VALUES (9, NULL,NULL,'22 244 9700');
INSERT INTO contactos VALUES (10, NULL,'913 753 002',NULL);
INSERT INTO contactos VALUES (11, NULL,'960 244 918',NULL);
INSERT INTO contactos VALUES (12, NULL,NULL,'259 325 001');
INSERT INTO contactos VALUES (13, NULL,NULL,'259 340 790');
INSERT INTO contactos VALUES (14, NULL,NULL,'259 351 209');
INSERT INTO contactos VALUES (15, NULL,NULL,'259 374 440');
INSERT INTO contactos VALUES (16, NULL,NULL,'239 829 071');
INSERT INTO contactos VALUES (17, NULL,NULL,'239 858 300');


/* restaurantes - (id,nome,classificacao_media,Morada_idMorada,Contactos_idContactos,categoria,image_path,descricao) */
INSERT INTO restaurantes VALUES (1,'Tasquinha Bracarense',0,1,1,'Tradicional','https://i.pinimg.com/236x/c9/70/98/c9709800c52543291ce8c53ff109ad81.jpg',NULL);
INSERT INTO restaurantes VALUES (2,'Hamburgueria DeGema (Braga-Lamaçães)',0,2,2,'Hamburgueria','https://media-cdn.tripadvisor.com/media/photo-s/0e/4d/d5/00/hamburguer-romano.jpg',NULL);
INSERT INTO restaurantes VALUES (3,'Artesão Bistrô',6,12,10,'Bistrô','https://b.zmtcdn.com/data/pictures/9/18976249/1ab0a0700ebd9b6779f79690ecba300d.jpg',NULL);
INSERT INTO restaurantes VALUES (4,'Bota&Bira',6,13,11,'Bistrô','https://b.zmtcdn.com/data/pictures/9/18976249/1ab0a0700ebd9b6779f79690ecba300d.jpg',NULL);
INSERT INTO restaurantes VALUES (5,'Cais da Villa',6,16,14,'Bistrô','https://www.evasoes.pt/files/2018/05/cais-da-villa222-960x640.jpg',NULL);
INSERT INTO restaurantes VALUES (6,'Amadeus',6,17,15,'Bistrô','https://media-cdn.tripadvisor.com/media/photo-s/13/cd/8f/a9/img-20180722-145758-largejpg.jpg',NULL);



/* ponto_interesse - (id,nome,classificacao_media,Morada_idMorada,Contactos_idContactos,image_path,descricao) */
INSERT INTO ponto_interesse VALUES (1,'Bom Jesus do Monte',0,3,3,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Treppenaufgang_Bom_Jesus_do_Monte.jpg/1200px-Treppenaufgang_Bom_Jesus_do_Monte.jpg','O Santuário do Bom Jesus do Monte localiza-se na freguesia de Tenões, na cidade, concelho e distrito de Braga, em Portugal. Fica situado nas proximidades do Santuário de Nossa Senhora do Sameiro.');
INSERT INTO ponto_interesse VALUES (2,'Sé Catedral de Braga',0,4,4,'https://upload.wikimedia.org/wikipedia/commons/3/3f/Se_Catedral_de_Braga.jpg','A Sé de Braga localiza-se na freguesia da Sé, cidade e concelho de Braga, distrito de mesmo nome, em Portugal. Constitui-se na sede do bispado fundado, segundo a tradição, por São Tiago Maior que aqui terá deixado como primeiro bispo o seu discípulo, São Pedro de Rates');
INSERT INTO ponto_interesse VALUES (3,'Torre dos Clérigos',8,7,7,'https://content.gulbenkian.pt/wp-content/uploads/2018/11/02122359/vila1.jpg','A Igreja e Torre dos Clérigos é um notável conjunto arquitetónico situado na cidade do Porto, Portugal, sendo considerado o ex-libris dessa cidade. O conjunto localiza-se no topo da Rua dos Clérigos, entre as ruas de São Filipe Néri e da Assunção');
INSERT INTO ponto_interesse VALUES (4,'Ponte Luís I',6,7,7,'https://i0.wp.com/ncultura.pt/wp-content/uploads/2017/10/porto-ponte-luis-innapartments-destaque-e1509446524499.jpg','A Ponte Luíz I, é uma ponte em estrutura metálica com dois tabuleiros, construída entre os anos 1881 e 1888, ligando as cidades do Porto e Vila Nova de Gaia separadas pelo rio Douro, em Portugal.');
INSERT INTO ponto_interesse VALUES (5,'Livraria Lello',3,8,7,'https://media.timeout.com/images/105654462/750/422/image.jpg','A Livraria Lello, também conhecida como Livraria Lello & Irmão ou Livraria Chardron, situa-se na Rua das Carmelitas, 144, no Centro Histórico da cidade do Porto, em Portugal.');
INSERT INTO ponto_interesse VALUES (6,'Fundação Da Casa De Mateus',9,18,7,'https://upload.wikimedia.org/wikipedia/commons/2/2b/MateusPalace1.jpg','O Palácio de Mateus está situado na freguesia de Mateus, concelho de Vila Real, Distrito de Vila Real. A Casa de Mateus foi mandada construir na primeira metade do século XVIII pelo 3º Morgado de Mateus, António José Botelho Mourão. A casa foi sempre administrada pela família Sousa Botelho.');
INSERT INTO ponto_interesse VALUES (7,'Parque do Corgo',3,19,7,'https://aetransmontana.pt/aesite/wp-content/uploads/2015/07/29693598-1024x695.jpg',	'O Parque Corgo situa-se nas margens do rio que lhe dá nome, e tem uma área de cerca de 33 hectares; está ligado ao Parque Florestal, um verdadeiro pulmão da cidade, e incorpora vários equipamentos: campos polidesportivos, itinerários pedestres, parque de merendas de Codessais (equipado com grelhadores e mesas), piscinas municipais abertas, parque infantil, cafés e casas de chá; é ainda possível ver antigos moinhos, alguns deles recuperados. Na área correspondente ao Parque Florestal está instalado um circuito de manutenção, que convida à prática de hábitos de vida saudáveis. É nesta zona que se situam alguns organismos públicos: Direcção Geral das Florestas do Norte, e o S.E.P.N.A. da G.N.R.. Numa das margens do rio estão as instalações do Centro de Ciência Viva. Esta área da cidade assume um papel primordial na vida dos vila-realenses, havendo uma notória ligação criada entre estes, o rio e todo o ambiente do Parque. Deixe que o rio Corgo seja o seu anfitrião… ele dar-lhe-á a conhecer cada recanto deste Parque! Venha desfrutá-lo!');



/* hoteis - (id,nome,classificacao_media,Morada_idMorada,Contactos_idContactos,image_path,descricao) */
INSERT INTO hoteis VALUES (1,'Meliá Braga Hotel & Spa',0,5,5,'https://r-cf.bstatic.com/images/hotel/max1024x768/256/256175832.jpg',NULL);
INSERT INTO hoteis VALUES (2,'Hotel Vila Galé Collection Braga',0,6,6,'https://r-cf.bstatic.com/images/hotel/max1024x768/155/155755371.jpg',NULL);
INSERT INTO hoteis VALUES (3,'The Yeatman',8,10,8,'https://www.the-yeatman-hotel.com/fotos/gca/6h0p5792_23895183558dcf2ab1861c.jpg',NULL);
INSERT INTO hoteis VALUES (4,'Vila Foz Hotel & Spa',8,11,9,'https://www.vilafozhotel.pt/media/vila-foz-hotel-amp-spa-bannervf-30291.jpg',NULL);
INSERT INTO hoteis VALUES (5,'Hotel Mira Corgo',8,14,12,'https://lifecooler.com/files/registos/imagens/326326/78328.jpg',NULL);
INSERT INTO hoteis VALUES (6,'Quinta do Paço Hotel',8,15,13,'https://q-cf.bstatic.com/images/hotel/max1280x900/197/1973803.jpg',NULL);
INSERT INTO hoteis VALUES (7,'Hotel Oslo Coimbra',8,20,16,'https://www.hoteloslo-coimbra.pt/wp-content/uploads/Hotel-com-vista-coimbra-hotel-oslo.jpg',NULL);
INSERT INTO hoteis VALUES (8,'Tivoli Coimbra Hotel',8,21,17,'https://assets.tivolihotels.com/image/upload/q_auto,f_auto/media/minor/tivoli/images/hotels/tcoi/dinning/570x350/tcoi_simone_570x350.jpg',NULL);


/* excursões - (id,data_hora,ponto_interesse_id,guia_id,preço,descricao)  -------  DATETIME: YYYY-MM-DD HH:MI:SS */
INSERT INTO excursões VALUES (1,'2020-05-25 12:30:00',1,1,7.50,'passeio de barco pelo rio Este');
INSERT INTO excursões VALUES (2,'2020-04-20 17:00:00',2,1,5.0,NULL);
INSERT INTO excursões VALUES (3,'2020-12-24 17:00:00',2,1,15.0,'missa de Natal com presença do Pai Natal');
INSERT INTO excursões VALUES (4,'2020-12-24 17:00:00',3,1,15.0,'Célebre Visita pela cidade Invicta');

/* inscrição - (excursão_id,user_id,pagamento_id) */
INSERT INTO inscrição VALUES (1,1,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (1,2,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (1,3,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (2,1,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (2,2,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (2,3,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (3,1,'pi_1GksdqLCCdFDqLN2JTjUoBXM');
INSERT INTO inscrição VALUES (3,2,'pi_1GksdqLCCdFDqLN2JTjUoBXM');


/* classificacao_restaurantes - (user_id,restaurantes_id,valor,comentario) */
INSERT INTO classificacao_restaurantes VALUES (2,1,4,'Desconto do CESIUM impec');
INSERT INTO classificacao_restaurantes VALUES (3,2,5,'Melhor que chocapitos do LIDL');


/* classificacao_pontointeresse - (user_id,ponto_interesse_id,valor,comentario) */
INSERT INTO classificacao_pontointeresse VALUES (2,1,5,'Bonito');
INSERT INTO classificacao_pontointeresse VALUES (3,2,5,'Conhecer a Sé de Braga é voltar centenas de anos na história da cidade. O prédio é magnífico e merece ser admirado com atenção e calma em todos os seus detalhes arquitetônicos. Após a visita, vale entrar na lojinha que fica na esquina à esquerda de quem olha para a igreja. Ali é possível ouvir a Sra. Esperança contar a história dos famosos Lenços dos Namorados.');
INSERT INTO classificacao_pontointeresse VALUES (2,3,7,'Uma vista incrível sobre a cidade Invicta.');
INSERT INTO classificacao_pontointeresse VALUES (2,4,10,'Deslumbre-se pela vista magnífica na golden hour!');
INSERT INTO classificacao_pontointeresse VALUES (1,5,2,'Arquitetura deslumbrante, no entanto uma deceção. Quando visitei pensei que iria remontar o passado e imaginar a grande J. K. Rowling a escrever o que se tornaria uma das maiores sagas de sempre, e a inspirar-se na livraria. Sinto-me enganado, pois cobram por entrada para algo que não passa de uma fraude!');


/* classificacao_hoteis - (user_id,hoteis_id,valor,comentario) */
INSERT INTO classificacao_hoteis VALUES (2,1,5,'Tá demais o bar da praia, oh maninho');
INSERT INTO classificacao_hoteis VALUES (3,2,3,NULL);



/************************ PROCEDURES **************************/

DELIMITER //
CREATE PROCEDURE ChangePassword (IN emailCheck VARCHAR(45), IN passwordNew VARCHAR(45))
BEGIN
    UPDATE users
    SET users.password = passwordNew
    WHERE email = emailCheck;
END;
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE ChangeTelemovel (IN emailCheck VARCHAR(45), IN telemovelNew VARCHAR(45))
BEGIN
    UPDATE users 
    SET users.telemovel = telemovelNew
    WHERE email = emailCheck;
END;
//
DELIMITER ;

