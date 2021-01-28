{-|
Module : Tarefa1_2017li1g2
Description : Construir Mapas
-}
module Tarefa1_2017li1g2 where

import LI11718

-- | Lista de caminhos para realização de testes do programa.
testesT1 :: [Caminho]
testesT1 = [[Avanca,CurvaDir,Sobe,CurvaDir,Avanca,CurvaDir,Desce,CurvaDir], 
			[Avanca,Avanca,CurvaDir,Avanca,CurvaDir,Avanca,CurvaDir,Avanca,Avanca,Avanca,CurvaEsq,Avanca,CurvaEsq,Avanca,CurvaEsq,Avanca],
			[Avanca,CurvaEsq,Avanca,Avanca,Avanca,CurvaDir,CurvaEsq,CurvaDir,Avanca,CurvaDir,CurvaEsq,Avanca,Sobe,Avanca,CurvaDir,Avanca,CurvaDir,Desce,CurvaEsq,Avanca,CurvaDir,Avanca,CurvaDir,Avanca,CurvaEsq,Avanca,CurvaEsq,Desce,Avanca,CurvaDir,Sobe,Avanca,CurvaDir,CurvaDir]]

-- | Função que constroi um mapa a partir de um caminho.
constroi :: Caminho -> Mapa
constroi c = Mapa(partida c,dirInit) (atualizaTabuleiro c)

-- | Função que recebe uma orientação de uma peça e ao rodar à Direita dá uma nova orientação da peça.
rodaDir :: Orientacao -> Orientacao
rodaDir orie | orie == Norte = Este
			 | orie == Este  = Sul
			 | orie == Sul   = Oeste
			 | otherwise     = Norte

-- | Função que recebe uma orientação de uma peça e ao rodar à Esquerda dá uma nova orientação da peça.
rodaEsq :: Orientacao -> Orientacao
rodaEsq orie | orie == Norte = Oeste
			 | orie == Oeste = Sul
			 | orie == Sul   = Este
			 | otherwise     = Norte

-- | Função que recebe uma posição e uma orientação e dá uma nova posição, depois de ter movido a peça na orientação indicada.
move :: Posicao -> Orientacao -> Posicao
move (x, y) posi | posi == Norte = (x,y-1)
				 | posi == Sul   = (x,y+1)
   				 | posi == Este  = (x+1,y)
   				 | otherwise     = (x-1,y)


-- | Função que cria uma linha que é uma lista de peças e posições.
replicatex :: Dimensao -> Peca -> Posicao -> [(Peca,Posicao)]
replicatex (x,y) peca (a,b)| (x == 0)  = []
		  			  	   |  x > 0    = (peca,(a,b)) : replicatex (x-1, y) peca (a+1,b)
		  			   	   | otherwise = []

-- | Função que cria uma lista de listas de peças e posições com base numa linha, replicando essa linha de acordo com a dimensão adequada.
replicatey :: Dimensao -> Peca -> Posicao -> [[(Peca,Posicao)]]
replicatey (x,y) peca (a,b)  | y == 0    = []
					  		 | y  > 0    = (replicatex (x,y) peca (a,b)) : replicatey (x,y-1) peca (a,b+1)
		   	   			     | otherwise = [] 	

-- | Função que cria um tabuleiro de lava, de acordo com a dimensão adequada, recorrendo a uma função auxiliar de nome: replicatey, em que todas as peças são do tipo: Peca Lava 0.
tabuleiroLava :: Caminho -> [[(Peca,Posicao)]]
tabuleiroLava c = (replicatey (dimensao c) (Peca Lava 0) (0,0))


-- | Função que transforma um passo, com uma orientação, uma posição e uma altura, numa peça e posição, com base em várias funções auxiliares.
transpassopeca :: Passo -> Orientacao -> Posicao -> Altura -> (Peca,Posicao)
transpassopeca x k y z | x == CurvaDir = transCurvaDir x k y z 
	   				   | x == CurvaEsq = transCurvaEsq x k y z
					   | x == Sobe     = transSobe x k y z
					   | x == Desce    = transDesce x k y z
					   | otherwise     = (Peca Recta z , y)

-- | Função auxiliar que transforma um passo, Desce, com uma orientação, uma posição e uma altura, numa peça e posição.
transDesce :: Passo -> Orientacao -> Posicao -> Altura -> (Peca,Posicao)
transDesce x k y z = (Peca (Rampa (sobeDesce k)) (z-1) , y)

-- | Função auxiliar que transforma um passo, Sobe,  com uma orientação, uma posição e uma altura, numa peça e posição.
transSobe :: Passo -> Orientacao -> Posicao -> Altura -> (Peca,Posicao)
transSobe x k y z = (Peca (Rampa k) (z)  , y)

-- | Função auxiliar que transforma um passo, Curva Direita, com uma orientação, uma posição e uma altura, numa peça e posição.
transCurvaDir :: Passo -> Orientacao -> Posicao -> Altura -> (Peca,Posicao)
transCurvaDir x k y z = (Peca (Curva (curvaDux k)) z , y)

curvaDux :: Orientacao -> Orientacao
curvaDux k | k == Este = Este
		   | k == Oeste = Oeste
		   | k == Norte = Norte
	       | otherwise = Sul

-- | Função auxiliar que transforma um passo, Curva Esquerda, com uma orientação, uma posição e uma altura, numa peça e posição.
transCurvaEsq :: Passo -> Orientacao -> Posicao -> Altura -> (Peca,Posicao)
transCurvaEsq x k y z = (Peca (Curva (curvaEux k)) z , y)

curvaEux :: Orientacao -> Orientacao
curvaEux k | k == Este = Sul
		   | k == Oeste = Norte
		   | k == Norte = Este
	       | otherwise = Oeste


-- | Função que define uma rampa dependendo se sobe ou se desce.
sobeDesce :: Orientacao -> Orientacao
sobeDesce m | m == Norte = Sul
			| m == Oeste = Este
			| m == Este = Oeste
			| otherwise = Norte


-- | Função que recebendo uma orientação e um passo, devolve uma orientação, em que devolve uma orientação depois de dado um passo e uma orientação inicial.
atualizaOrienta :: Orientacao -> Passo -> Orientacao
atualizaOrienta x y | y == Avanca && x == Norte = Norte
			        | y == Avanca && x == Sul = Sul
				    | y == Avanca && x == Este = Este
				    | y == Avanca && x == Oeste = Oeste
		 		    | y == Sobe && x == Norte = Norte
				    | y == Sobe && x == Sul = Sul
			  	    | y == Sobe && x == Este = Este
		   			| y == Sobe && x == Oeste = Oeste
		   			| y == Desce && x == Norte = Norte
		   			| y == Desce && x == Sul = Sul
		   			| y == Desce && x == Este = Este
		   			| y == Desce && x == Oeste = Oeste
		   			| y == CurvaEsq = rodaEsq x  
		   			| otherwise = rodaDir x

-- | Função que recebendo uma altura e um passo, devolve uma altura, em que a altura final é a altura dada depois de dado um passo e uma determinada altura.
atualizaAltura ::  Altura -> Passo -> Altura
atualizaAltura a p | p == Sobe  = a + 1
				   | p == Desce = a - 1 
				   | otherwise = a


-- | Função que dado um caminho recebe uma lista de peças e posições, traduzindo os passos em peças e posições com recurso a uma função auxiliar.
passospeca :: Caminho -> [(Peca, Posicao)]
passospeca [] = []
passospeca c = passospecaaux c Este (partida c) 0


-- | Função auxiliar de "passospeca", que recebe um caminho, uma orientação, uma posição e uma altura e devolve uma lista de peças e posições, com recurso a várias funções auxiliares.
passospecaaux :: Caminho -> Orientacao -> Posicao -> Altura -> [(Peca,Posicao)]
passospecaaux [] o p a = []
passospecaaux (h:t) o p a = (transpassopeca (h) o p a) : passospecaaux t (atualizaOrienta o h) (move p (atualizaOrienta o h)) (atualizaAltura a h)


-- | Função que recebe um caminho e devolve uma lista de listas de peças e posições, com recurso a várias funções auxiliares, de modo a dar uma lista das listas de peças e posições.
atuaTabuleiro :: Caminho -> [[(Peca,Posicao)]]
atuaTabuleiro c = (atuaTabuleiroAux (tabuleiroLava c) (passospeca c))

-- | Função auxiliar da função "atuaTabuleiro", que recebe uma lista de listas de peças e posições e uma lista de peças e posições, devolvendo uma lista de peças e posições.
atuaTabuleiroAux :: [[(Peca,Posicao)]] -> [(Peca,Posicao)] -> [[(Peca,Posicao)]]
atuaTabuleiroAux [] l = []
atuaTabuleiroAux l [] = []
atuaTabuleiroAux (h:t) l = [(tabuaux h l)] ++ (atuaTabuleiroAux t l)


-- | Função auxiliar da função "atuaTabuleiroAux", que recebe duas listas de peças e posições e devolve uma lista de peças e posições.
tabuaux :: [(Peca,Posicao)] -> [(Peca,Posicao)] -> [(Peca,Posicao)]
tabuaux [] y = []
tabuaux (h:t) (x:xs) = tabauxi h (x:xs) ++ tabuaux t (x:xs)

-- | Função auxiliar da função "tabuaux", que recebe uma lista e uma posição e uma lista de peças e posições, devolvendo uma lista de peças e posições.
tabauxi :: (Peca,Posicao) -> [(Peca,Posicao)] -> [(Peca,Posicao)] 
tabauxi h [] = [h]
tabauxi h (x:xs) = if snd h == snd x
				   then [x]
				   else tabauxi h xs	

-- | Função que recebe um caminho e devolve um Tabuleiro, com decurso a várias funções auxiliares, de modo a atualizar o Tabuleiro.
atualizaTabuleiro :: Caminho -> Tabuleiro 
atualizaTabuleiro c = tabauxy (atuaTabuleiro c)

-- | Função auxiliar da função "atualizaTabuleiro", que recebe uma lista de listas de peças e posições e devolve um tabuleiro.
tabauxy :: [[(Peca,Posicao)]] -> Tabuleiro
tabauxy [] = []
tabauxy (h:t) = [tabauxz (h)] ++ tabauxy t

-- | Função auxiliar da função "tabauxy", que recebe uma lista de peças e posições e devolve uma peça.
tabauxz :: [(Peca,Posicao)] -> [Peca]
tabauxz [] = []
tabauxz (h:t) = fst h : tabauxz t
