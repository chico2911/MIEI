module Tarefa2_2017li1g2 where

import LI11718 
import Data.List

testesT2 :: [Tabuleiro]
testesT2 = [[[Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0],
			 [Peca Lava 0,Peca (Curva Norte) 0,Peca Recta 0,Peca (Curva Este) 0,Peca Lava 0],
			 [Peca Lava 0,Peca (Rampa Sul) 0,Peca Lava 0,Peca (Rampa Sul) 0,Peca Lava 0],
			 [Peca Lava 0,Peca (Curva Oeste) 1,Peca Recta 1,Peca (Curva Sul) 1,Peca Lava 0],
			 [Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0]],
			 
			 [[Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0],
			 [Peca Lava 0,Peca (Curva Norte) 0,Peca Recta 0,Peca (Curva Este) 0,Peca Lava 0,Peca Lava 0,Peca Lava 0],
			 [Peca Lava 0,Peca Recta 0,Peca Lava 0,Peca Recta 0,Peca Lava 0,Peca Lava 0,Peca Lava 0],
			 [Peca Lava 0,Peca (Curva Oeste) 0,Peca Recta 0,Peca Recta 0,Peca Recta 0,Peca (Curva Este) 0,Peca Lava 0],
			 [Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Recta 0,Peca Lava 0,Peca Recta 0,Peca Lava 0],
			 [Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca (Curva Oeste) 0,Peca Recta 0,Peca (Curva Sul) 0,Peca Lava 0],
			 [Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0,Peca Lava 0]]]


valida :: Mapa -> Bool
valida m = (lavaRetanguloi m) && (alturaLav m) && (orieInicialFinal m) && (validaPosInit m) && (validaLava m) && (validaAltura m) && (linhasColunas m)

linhasColunas :: Mapa -> Bool
linhasColunas (Mapa p []) = False
linhasColunas (Mapa p (t)) = linhasColunasX t 

linhasColunasX :: Tabuleiro -> Bool 
linhasColunasX [t] = True
linhasColunasX (h:t) | length h == length (head t) = linhasColunasX t
					 | otherwise = False 


posicaoMTabuleiro :: Mapa -> Peca
posicaoMTabuleiro (Mapa p (h:t)) = ((!!) ((!!) (h:t) (snd(fst p))) (fst(fst p)))

posicaoTabuleiro :: Posicao -> Tabuleiro -> Peca
posicaoTabuleiro (x,y) h = ((!!) ((!!) (h) (y)) x) 

lavaRetanguloi :: Mapa -> Bool
lavaRetanguloi (Mapa p []) = False
lavaRetanguloi (Mapa p t) = lavaRetangulo t

lavaRetangulo :: Tabuleiro -> Bool
lavaRetangulo [] = False
lavaRetangulo [[Peca Lava 0]] = True
lavaRetangulo (h:t) = lavaRetanguloAux h && lavaRetanguloAux (last (h:t)) && lavaRetanguloAuxi (h:t)

lavaRetanguloAux :: [Peca] -> Bool
lavaRetanguloAux [] = False
lavaRetanguloAux [Peca Lava 0] = True
lavaRetanguloAux (h:t) = h == (Peca Lava 0) && lavaRetanguloAux t 

lavaRetanguloAuxi :: Tabuleiro -> Bool
lavaRetanguloAuxi [] = True
lavaRetanguloAuxi [[Peca Lava 0]] = True
lavaRetanguloAuxi (h:t) = aux h && lavaRetanguloAuxi t
            where 
             	aux :: [Peca] -> Bool
                aux h = head h == Peca Lava 0 && last h == Peca Lava 0

alturaLav :: Mapa -> Bool
alturaLav (Mapa	p []) = False
alturaLav (Mapa p t)  = alturaLava t

alturaLava :: Tabuleiro -> Bool
alturaLava [] = True
alturaLava [[Peca Lava 0]] = True
alturaLava (h:t) = alturaLavaAux h && alturaLava t

alturaLavaAux :: [Peca] -> Bool
alturaLavaAux [Peca Lava 0] = True
alturaLavaAux ((Peca t a):s) | t == Lava  = altaux a && alturaLavaAux s
						     | otherwise = alturaLavaAux s							

altaux :: Altura -> Bool
altaux x | x == 0 = True
         | otherwise = False

vizinho :: (Posicao,Orientacao) -> Posicao
vizinho ((x,y),o) | o == Este  = (x-1,y)
				  | o == Oeste = (x+1,y)
				  | o == Norte = (x,y+1)
				  | otherwise   = (x,y-1)


orieInicialFinal :: Mapa -> Bool 
orieInicialFinal (Mapa p []) = False
orieInicialFinal (Mapa p t) = orieInicialFinalAux p t

orieInicialFinalAux :: (Posicao,Orientacao) -> Tabuleiro -> Bool
orieInicialFinalAux p [] = False
orieInicialFinalAux p (h:t) = orien p (h:t)

orien :: (Posicao,Orientacao) -> Tabuleiro -> Bool
orien p (h:t) = orienAux ((!!) (h:t) (fst (vizinho (p)))) (p)

orienAux :: [Peca] -> (Posicao,Orientacao) -> Bool
orienAux (h:t) p = orienauxi ((!!) (h:t) (snd (fst p))) p

orienauxi :: Peca -> (Posicao,Orientacao) -> Bool
orienauxi (Peca t a) (x,y) | y == Este && t == (Recta) && a == 0  = True
						   | y == Este && t == (Curva Norte) && a == 0 = True
						   | y == Este && t == (Rampa Este) && a == 0 = True
						   | y == Oeste && t == (Recta) && a == 0  = True
						   | y == Oeste && t == (Curva Sul) && a == 0 = True
						   | y == Oeste && t == (Rampa Oeste) && a == 0 = True
						   | y == Norte && t == (Recta) && a == 0  = True
						   | y == Norte && t == (Curva Oeste) && a == 0 = True
						   | y == Norte && t == (Rampa Norte) && a == 0 = True
						   | y == Sul && t == (Recta) && a == 0  = True
						   | y == Sul && t == (Curva Este) && a == 0 = True
						   | y == Sul && t == (Rampa Sul) && a == 0 = True
						   | otherwise = False

							 

validaPosInit :: Mapa -> Bool
validaPosInit (Mapa p []) = False
validaPosInit m  = (validaPosInitAux (posicaoMTabuleiro m) m)

validaPosInitAux :: Peca -> Mapa -> Bool
validaPosInitAux (Peca c a) (Mapa p t) = if c == (Rampa Este) 
										 then True
										 else if c == (Curva Este) 
										 	  then if (validaAux (fst p) t) == True
										 	  		then True
										 	  		else False
										 	  else if c == Recta
										 	  	   then	True
										 	  	   else False

validaAux :: Posicao -> Tabuleiro -> Bool
validaAux p [] = False
validaAux (x,y) (h:t) = (validaauxi (posicaoTabuleiro (x,y+1) (h:t))) 

validaauxi :: Peca -> Bool
validaauxi (Peca c a) = if c == (Rampa Sul)
						then True
						else if c == Recta 
							then True
							else False


percurso :: Mapa -> [(Posicao,Orientacao)]	
percurso (Mapa p []) = []
percurso (Mapa p t) = percursoaux p (vizinho p) t


percursoaux :: (Posicao,Orientacao) -> Posicao -> Tabuleiro -> [(Posicao,Orientacao)]
percursoaux o (x,y) [] = []
percursoaux o (x,y) t  | o == ((x,y),snd o) = [((x,y),(snd o))]
					   | otherwise        = o : percursoaux (posicaon o (posicaoTabuleiro (fst o) t)) (x,y) t


posicaon :: (Posicao,Orientacao) -> Peca -> (Posicao,Orientacao)
posicaon ((x,y),z) (Peca t a) | t == Recta && z == Este               = ((x+1,y), Este)
							  | t == Recta && z == Oeste              = ((x-1,y), Oeste)
							  | t == Recta && z == Norte              = ((x,y-1), Norte)
							  | t == Recta && z == Sul                = ((x,y+1), Sul)
							  | t == (Curva Norte)  && z == Norte     = ((x+1,y), Este)
							  | t == (Curva Sul)    && z == Sul   	  = ((x-1,y), Oeste)
							  | t == (Curva Este)   && z == Este	  = ((x,y+1), Sul)
							  | t == (Curva Oeste)  && z == Oeste     = ((x,y-1), Norte)
							  | t == (Curva Norte) 	&& z == Oeste     = ((x,y+1), Sul)
							  | t == (Curva Sul) 	&& z == Este	  = ((x,y-1), Norte)
							  | t == (Curva Este)   && z == Norte	  = ((x-1,y), Oeste)
							  | t == (Curva Oeste)  && z == Sul		  = ((x+1,y), Este)
							  | t == (Rampa Norte)  && z == Norte 	  = ((x,y-1), Norte)
							  | t == (Rampa Norte)  && z == Sul   	  = ((x,y+1), Sul)
							  | t == (Rampa Sul)    && z == Norte     = ((x,y-1), Norte)
							  | t == (Rampa Sul)    && z == Sul       = ((x,y+1), Sul)
							  | t == (Rampa Oeste)  && z == Oeste     = ((x-1,y), Oeste)
							  | t == (Rampa Oeste)  && z == Este      = ((x,y-1), Este)
							  | t == (Rampa Este)   && z == Oeste     = ((x-1,y), Oeste)
							  | otherwise                             = ((x+1,y), Este)

contaLava :: Mapa -> Int
contaLava (Mapa p [])    = 0	 
contaLava (Mapa p (h:t)) = contaLavaX (Peca Lava 0)(h) + contaLava (Mapa p t) 

contaLavaX ::  Peca -> [Peca] -> Int
contaLavaX p [] = 0
contaLavaX p (h:t) | p == h    = 1 + contaLavaX p t
				   | otherwise = contaLavaX p t

validaLava :: Mapa -> Bool
validaLava (Mapa p t) | contaLava (Mapa p t) + length (nub (percursoPos (percurso (Mapa p t)))) == tamanhoTabuleiro (Mapa p t) = True
					  | otherwise                                                = False

tamanhoTabuleiro :: Mapa -> Int
tamanhoTabuleiro (Mapa p []) = 0 
tamanhoTabuleiro (Mapa p (h:t)) = (length h) + tamanhoTabuleiro (Mapa p t)

validaAltura :: Mapa -> Bool
validaAltura (Mapa m []) = True
validaAltura (Mapa m t)  = validaAlturaAux (percurso (Mapa m t)) (t)
 
validaAlturaAux :: [(Posicao,Orientacao)] -> Tabuleiro -> Bool
validaAlturaAux [x] t =True
validaAlturaAux [] t = True
validaAlturaAux (((x,y),o):((xs,ys),os):s) (h:t) = validaAlturaAuxi (posicaoTabuleiro (x,y) (h:t)) o os (posicaoTabuleiro (xs,ys) (h:t)) && validaAlturaAux (((xs,ys),os):s) (h:t)

validaAlturaAuxi :: Peca -> Orientacao -> Orientacao -> Peca -> Bool
validaAlturaAuxi (Peca t a) o os (Peca t1 a1) | t  == Recta          && a == a1     				  = True
                                   		      | t  == (Curva Norte)  && a == a1					 	  = True
		                                      | t  == (Curva Sul)    && a == a1 				      = True
		                                   	  | t  == (Curva Este)   && a == a1 					  = True
		                                   	  | t  == (Curva Oeste)  && a == a1						  = True
		                                   	  | t  == (Curva Oeste)  && a == a1 					  = True
		                                   	  | t  == (Rampa Norte)  && o == Norte && (a+1) == a1 	  = True
		                                   	  | t  == (Rampa Norte)  && o == Sul && a == a1       	  = True
		                                  	  | t  == (Rampa Oeste)  && o == Oeste && (a+1) == a1 	  = True
		                                   	  | t  == (Rampa Oeste)  && o == Este && a == a1      	  = True
		                                      | t  == (Rampa Este)   && o == Este && (a+1) == a1      = True
		                                      | t  == (Rampa Este)   && o == Oeste && a == a1      	  = True
		                                      | t  == (Rampa Sul)    && o == Sul && (a+1) == a1       = True
		                                      | t  == (Rampa Sul)    && o == Norte && a == a1         = True
		                                      | t1 == (Rampa Norte) && os == Norte && a == a1   	  = True
		                                      | t1 == (Rampa Norte) && os == Sul && a == a1+1         = True
		                                      | t1 == (Rampa Oeste) && os == Oeste && a == a1   	  = True
		                                      | t1 == (Rampa Oeste) && os == Este && a == a1 +1       = True
		                                      | t1 == (Rampa Este)  && os == Este && a == a1          = True
		                                      | t1 == (Rampa Este)  && os == Oeste && a == a1+1       = True
		                                      | t1 == (Rampa Sul)   && os == Sul && a == a1           = True
		                                      | t1 == (Rampa Sul)   && os == Norte && a == (a1+1)     = True 
                                  		      | otherwise                                       	  = False


percursoPos :: [(Posicao,Orientacao)] -> [Posicao]
percursoPos [] = []
percursoPos (h:t) = (fst h) : (percursoPos t)