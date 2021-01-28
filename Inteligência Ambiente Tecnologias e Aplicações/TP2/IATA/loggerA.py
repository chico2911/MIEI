# -*- coding: utf-8 -*-
"""
Created on Mon Dec 28 11:37:31 2020

@author: Ricardo Carvalho
"""
import socket
import json

from textblob import TextBlob
import os

def textblob_Sentiment(text):
    from textblob import TextBlob
    score =  TextBlob(text).sentiment.polarity
    return score

def readEmotionFile(emo):
    f2 = open(emo+"store.txt","w+")
    f = open(emo+".txt", "r+")
    dictemo = {}
    line = f.readline()
    f2.write(line)
    while line:
        line = line.rstrip()  # remove '\n' at end of line
        temp = line
        dictemo[temp] = []
        line = f.readline()
        f2.write(line)
        line = line.rstrip()
        while line[0:2] == "->":
              dictemo[temp].append(line[2:])
              line = f.readline()
              f2.write(line)
              line = line.rstrip()
    f2.close()
    f.close()
    return dictemo



def mapToFile(emo, file):
    
    f = open(file, "w+")
    
    for s in emo:
        f.write(s + "\n")
        temp = emo.get(s)
        for a in temp:
            f.write("->" + a + "\n")
    f.close()
        
                
def scoreEmotion(score):
    if score>=-0.1 and score <=0.1:
        feeling="(Neutro)"
    if score>0.1 and score <=0.5:
        feeling="(Feliz)"
    if score>0.5:
        feeling="(Excitado)"
    if score>=-0.5 and score <=-0.1:
        feeling="(Triste)"
    if score <-0.5:
        feeling="(Irritado)"
    return feeling

def tratarsugestoes(sugestoes):
    top1=""
    top2=""
    top3=""
    top1vezes=0
    top2vezes=0
    top3vezes=0
    for s in sugestoes:
        x = s.split(",")
        if int(x[0])>top1vezes:
            temp=top1
            tempvezes=top1vezes
            top1vezes=int(x[0])
            top1=x[1]
            x[0]= str(tempvezes)
            x[1]= temp
        if int(x[0])>top2vezes:
            temp=top2
            tempvezes=top2vezes
            top2vezes=int(x[0])
            top2=x[1]
            x[0]= str(tempvezes)
            x[1]= temp
        if int(x[0])>top3vezes:
            temp=top3
            tempvezes=top3vezes
            top3vezes=int(x[0])
            top3=x[1]
            x[0]= str(tempvezes)
            x[1]= temp
    if top3vezes>0:
        return[top1,top2,top3]
    if top2vezes>0:
        return[top1,top2]
    return[top1]
            
            

def updateMap(sug,sugestoes):
    found = False
    array = []
    for s in sugestoes:
        x = s.split(",")
        if x[1]==sug:
            array.append(str(int(x[0])+1) + "," + x[1])
            found = True
        else:
            array.append(s)
    if found== False:
        array.append("1," + sug)
    return array
        
    
    
def server_program():
    neutral = readEmotionFile("(Neutro)")
    excitado = readEmotionFile("(Excitado)")
    feliz = readEmotionFile("(Feliz)")
    triste = readEmotionFile("(Triste)")
    irritado = readEmotionFile("(Irritado)")
   
    
    host = "localhost"
    port = 5000  # initiate port no above 1024

    server_socket = socket.socket()  
    server_socket.bind((host, port))  

    server_socket.listen(1)
    conn, address = server_socket.accept()  # accept new connection
    logger = open("logger" + str(os.getpid()) + ".txt", "w+")
    data = ""
    while True:
        # receive data stream. it won't accept data packet greater than 1024 bytes
        data = conn.recv(1024).decode()
        if data.lower().strip() == 'bye':
            logger.write("logger B -> " + data + "\n")
            print("here")
            break
        logger.write("logger B -> " + data + "\n") 
        if not data:
            # if data is not received break
            break
        
        score = round(textblob_Sentiment(data),2)
        feeling = scoreEmotion(score)
        print("Logger B -> " + str(data) + "   " + feeling)
        if(feeling=="(Neutro)"):
            mymap=neutral
        if(feeling=="(Irritado)"):
            mymap=irritado
        if(feeling=="(Triste)"):
            mymap=triste
        if(feeling=="(Feliz)"):
            mymap=feliz
        if(feeling=="(Excitado)"):
            mymap=excitado
            
        sugestoes = mymap.get(data,[])
        if len(sugestoes) >0:
            sug = tratarsugestoes(sugestoes)
            x=1
            for s in sug:
                print("sugestão " + str(x) +":" + s)
                x=x+1
            datatosend = input('Eu -> ')
            if datatosend == "bye":
                break
            logger.write("logger A -> " + datatosend + "\n")
            mymap[data] = updateMap(datatosend,sugestoes)
            if(feeling=="(Neutro)"):
                neutral=mymap
            if(feeling=="(Irritado)"):
                irritado=mymap
            if(feeling=="(Triste)"):
                triste=mymap
            if(feeling=="(Feliz)"):
                feliz=mymap
            if(feeling=="(Excitado)"):
                excitado=mymap
            conn.sendall(datatosend.encode())
        else:
            datatosend = input('Eu -> ')
            if datatosend == "bye":
                break
            logger.write("logger A -> " + datatosend + "\n")
            mymap[data]=[]
            mymap[data].append("1," +datatosend)
            if(feeling=="(Neutro)"):
                neutral=mymap
            if(feeling=="(Irritado)"):
                irritado=mymap
            if(feeling=="(Triste)"):
                triste=mymap
            if(feeling=="(Feliz)"):
                feliz=mymap
            if(feeling=="(Excitado)"):
                excitado=mymap
            conn.sendall(datatosend.encode())
            
    
    mapToFile(neutral,"(Neutro).txt")
    mapToFile(irritado,"(Irritado).txt")
    mapToFile(triste,"(Triste).txt")
    mapToFile(excitado,"(Excitado).txt")
    mapToFile(feliz,"(Feliz).txt")
    
    
    conn.sendall("bye".encode())
    conn.close()


if __name__ == '__main__':
    server_program()