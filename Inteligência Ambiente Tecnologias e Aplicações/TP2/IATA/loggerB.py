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
    f = open(emo, "r+")
    dictemo = {}
    line = f.readline()
    while line:
        line = line.rstrip()  # remove '\n' at end of line
        temp = line
        dictemo[temp] = []
        line = f.readline()
        line = line.rstrip()
        while line[0:2] == "->":
              dictemo[temp].append(line[2:])
              line = f.readline()
              line = line.rstrip()
    f.close()
    return dictemo

def dictTofile(emo, file):
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
            

def updatedict(sug,sugestoes):
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

def joinmapsdup(map1, map2):
    for m in map1:
        l = map2.get(m,[])
        if len(l)>0:
            for line in l:
                found = False
                x = line.split(",")
                store = map1.get(m)
                st = map1.get(m)
                for ys in st:
                    y= ys.split(",")
                    if y[1] == x[1]:
                        found=True
                if found==False:
                    li = str(int(x[0])-1) + "," + x[1]
                    store.append(li)
            map1[m] = store
    
    for m in map2:
        l = map1.get(m,[])
        if len(l)>0:
            array = []
            for line in l:
                found = False
                x = line.split(",")
                for ys in map2.get(m):
                    y= ys.split(",")
                    if y[1] == x[1]:
                        found=True
                        k = str(int(y[0]) - int(x[0])) + "," + y[1]
                        array.append(k)
                if found==False:
                    array.append(line)
            map1[m] = array
        else:
            map1[m]=[]
            map1[m] = (map2.get(m))
    return map1
        
def joinmapsoriginals(map1, map2):
    for m in map1:
        l = map2.get(m,[])
        if len(l)>0:
            for line in l:
                found = False
                x = line.split(",")
                store = map1.get(m)
                st = map1.get(m)
                for ys in st:
                    y= ys.split(",")
                    if y[1] == x[1]:
                        found=True
                if found==False:
                    store.append(line)
            map1[m] = store
    
    for m in map2:
        l = map1.get(m,[])
        if len(l)>0:
            array = []
            for line in l:
                found = False
                x = line.split(",")
                for ys in map2.get(m):
                    y= ys.split(",")
                    if y[1] == x[1]:
                        found=True
                        k = str(int(y[0]) + int(x[0])) + "," + y[1]
                        array.append(k)
                if found==False:
                    array.append(line)
            map1[m] = array
        else:
            map1[m]=[]
            map1[m] = (map2.get(m))
            

    return map1
                
    
    
    
def client_program():
    
    neutral = readEmotionFile("(Neutro).txt")
    excitado = readEmotionFile("(Excitado).txt")
    feliz = readEmotionFile("(Feliz).txt")
    triste = readEmotionFile("(Triste).txt")
    irritado = readEmotionFile("(Irritado).txt")
   
    
    host = socket.gethostname()  # as both code is running on same pc
    port = 5000  # socket server port number

    client_socket = socket.socket()  # instantiate
    client_socket.connect(("localhost", port))  # connect to the server

    datatosend = input("Eu -> ")  # take input
    client_socket.sendall(datatosend.encode())
    while True:
          # send message
        # receive data stream. it won't accept data packet greater than 1024 bytes
        data = client_socket.recv(1024).decode()
        if data.lower().strip() == 'bye':
            break
        
        if not data:
            # if data is not received break
            break
        
        score = round(textblob_Sentiment(data),2)
        feeling = scoreEmotion(score)
        print("Logger A -> " + str(data) + "   " + feeling + "\n")
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
                print("-----------------------------")
                client_socket.sendall(datatosend.encode())
                break
            
            mymap[data] = updatedict(datatosend,sugestoes)
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
            client_socket.sendall(datatosend.encode())
        else:
            datatosend = input('Eu -> ')
            if datatosend == "bye":
                client_socket.sendall(datatosend.encode())
                break
            
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
            client_socket.sendall(datatosend.encode())
            
           
    client_socket.recv(1024).decode()
    
    
    m1= joinmapsdup(readEmotionFile("(Neutro)store.txt"), neutral)
    m2= joinmapsdup(readEmotionFile("(Triste)store.txt"), triste)
    m3= joinmapsdup(readEmotionFile("(Irritado)store.txt"), irritado)
    m4= joinmapsdup(readEmotionFile("(Excitado)store.txt"), excitado)
    m5= joinmapsdup(readEmotionFile("(Feliz)store.txt"), feliz)
    
    n1=joinmapsoriginals(readEmotionFile("(Neutro).txt"), m1)
    n2=joinmapsoriginals(readEmotionFile("(Triste).txt"), m2)
    n3=joinmapsoriginals(readEmotionFile("(Irritado).txt"), m3)
    n4=joinmapsoriginals(readEmotionFile("(Excitado).txt"), m4)
    n5=joinmapsoriginals(readEmotionFile("(Feliz).txt"), m5)
    
    
    
    dictTofile(n1,"(Neutro).txt")
    dictTofile(n2,"(Irritado).txt")
    dictTofile(n3,"(Triste).txt")
    dictTofile(n4,"(Excitado).txt")
    dictTofile(n5,"(Feliz).txt")
    
    client_socket.close()  


if __name__ == '__main__':
    client_program()
    
    
    
    
    