module Tarefa3_2017li1g2 where

import LI11718
import Tarefa2_2017li1g2

testesT3 :: [(Tabuleiro,Tempo,Carro)]
testesT3 = []

movimenta :: Tabuleiro -> Tempo -> Carro -> Maybe Carro
movimenta [] t c = Nothing
movimenta m 0 c = Just c
movimenta m t (Carro (x,y) a (vx,vy)) = movimentaAux t (vx,vy) m (Carro (x,y) a (vx,vy))


movimentaAux :: Tempo -> Velocidade -> Tabuleiro -> Carro -> Maybe Carro
movimentaAux t (0,0) m c = Just c
movimentaAux t (vx, vy) m (Carro (x,y) a (vs,vz)) | posicaoTabuleiro (truncate x, truncate y) m == Peca Lava 0 = Nothing
                                                  | otherwise = verificaPeca (posicaoTabuleiro (truncate x,truncate y) m) (posicaoTabuleiro (truncate (x+t*vx),truncate (y+t*vy)) m) (truncate (x+t*vx),truncate (y+t*vy)) (Carro (x,y) a (vs,vz))

movimentaAuxi :: Posicao -> Posicao -> Tabuleiro -> Carro -> Maybe Carro
movimentaAuxi x y [] c = Nothing 
movimentaAuxi x y t c  = (verificaPeca (posicaoTabuleiro x t) (posicaoTabuleiro y t) y c) 

verificaPeca :: Peca -> Peca -> Posicao -> Carro -> Maybe Carro
verificaPeca (Peca t a) (Peca t2 a2) x (Carro (b,d) z (vx,vy)) | (t2 == Lava) && (a == a2)  = Nothing
                                                               | trueCurva (Peca t2 a2) && (a == a2) = movimentaC (b,d) x (Peca t2 a2) (Carro (b,d) z (vx,vy))
                                                               | otherwise = Just (Carro (b,d) z (vx,vy)) 

trueCurva :: Peca -> Bool
trueCurva (Peca t a) | t == Curva Norte || t == Curva Sul || t == Curva Este || t == Curva Oeste = True
                     | otherwise                                                                 = False


movimentaC :: Ponto -> Posicao -> Peca -> Carro -> Maybe Carro
movimentaC (b,d) (x,y) (Peca (Curva t) a ) c | t == Norte && ( dx > dy) && (dx + dy) >= 1  = Just c
                                             | t == Sul   && ( dx < dy) && (dx + dy) <= 1  = Just c
                                             | t == Este  && ( dx < dy) && (dx + dy) >= 1  = Just c
                                             | t == Oeste && ( dx > dy) && (dx + dy) <= 1  = Just c
                                             | otherwise                 = Nothing
    where dx = abs(b-(toEnum x))
          dy = abs(d-(toEnum y))