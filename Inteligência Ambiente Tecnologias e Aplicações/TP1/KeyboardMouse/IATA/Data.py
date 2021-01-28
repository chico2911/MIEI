# *#**# coding: utf*#8 *#**#
"""
Created on Wed Oct 28 22:06:28 2020

@author: peter
"""

from pynput.mouse import Listener
from pynput import keyboard 
import math
import time
import calendar

esquerda = ""
direita = ""
ultimaTecla=""

# detect mouse move
def on_move(x, y):
        global esquerda, direita, ultimaTecla
        if ultimaTecla == "fechar":
         #Stop Listener
         print('Stop')
         return False
        f = open("logger.txt", "a")
        print('Pointer moved to {0}'.format((x, y)))
        ts= calendar.timegm(time.gmtime())
        print(ts)
        f.write("m*#m*#" + str(x) + '*#' + str(y) + '*#' + str(ts) + '\n')
        f.close()
        
# detect mouse click
def on_click(x, y, button, pressed):
    global esquerda, direita, ultimaTecla
    if ultimaTecla == "fechar":
         #Stop Listener
         print('Stop')
         return False
    f = open("logger.txt", "a")
    print('Mouse clicked at ({0}, {1}) with {2}'.format(x, y, button))
    ts= calendar.timegm(time.gmtime())
    print(ts)
    f.write("m*#c*#" + str(x) + '*#' + str(y) + '*#' + str(button) + '*#' + str(ts) +'\n')
    f.close()
   

        
# detect mouse scroll
def on_scroll(x, y, dx, dy):
        global esquerda, direita, ultimaTecla
        if ultimaTecla == "fechar":
         #Stop Listener
         print('Stop')
         return False
        f = open("logger.txt", "a")
        print('Mouse scrolled at ({0}, {1})({2}, {3})'.format(x, y, dx, dy))
        ts= calendar.timegm(time.gmtime())
        print(ts)
        f.write("m_s*#" + str(x) + '*#' + str(y) + '*#' + str(dx) + '*#' + str(dy) + '*#' + str(ts) + '\n')
        f.close()
        


    
    
    
###############################KEYBOARD####################

#detect key press
def pressionado(key):
    try:
        global ultimaTecla
        if(str(key.char)!=ultimaTecla):
            f = open("logger.txt", "a")
            print('alphanumeric key {0} pressed'.format(key.char))
            ts= calendar.timegm(time.gmtime())
            print(ts)
            f.write("t*#p*#" + str(key.char) + "*#" + str(ts) + '\n')
            f.close()
        ultimaTecla = str(key.char)
    except:
        if(str(key)!=ultimaTecla):
            f = open("logger.txt", "a")
            print('special key {0} pressed'.format(key))
            ts= calendar.timegm(time.gmtime())
            print(ts)
            f.write("t*#p*#" + str(key) + "*#" + str(ts) + '\n')
            f.close()
        ultimaTecla = str(key)
            
#detect key releases
def libertado(key):
    global ultimaTecla
    print('{0} released'.format(key))
    f = open("logger.txt", "a")
    ts= calendar.timegm(time.gmtime())
    print(ts)
    f.write("t*#r*#" +str(key) + "*#" + str(ts) + '\n')
    f.close()
    ultimaTecla = ""
    if key == keyboard.Key.esc:
        ultimaTecla = "fechar"
        #Stop Listener
        print('Stop')
        return False

#Collect events

key_listener = keyboard.Listener(on_release=libertado,on_press=pressionado)
key_listener.start()

with Listener(
        on_press=pressionado,
        on_release=libertado,
        on_move=on_move,
        on_click=on_click,
        on_scroll=on_scroll) as listener1:
    listener1.join()

