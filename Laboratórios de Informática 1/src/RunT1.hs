module Main where

import LI11718
import qualified Tarefa1_2017li1g2 as T1

import System.Environment
import Text.Read

main = do
    args <- getArgs
    case args of
        ["constroi"] -> do
            str <- getContents
            let caminho = readMaybe str
            case caminho of
                Nothing -> error "caminho invalido"
                Just c -> print $ T1.constroi c
        ["testes"] -> print $ T1.testesT1
        otherwise -> error "RunT1 argumentos invalidos"