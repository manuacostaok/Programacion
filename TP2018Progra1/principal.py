#! /usr/bin/env python
import os, random, sys, math

import pygame
from pygame.locals import *

from configuracion import *
#from funcionesRESUELTO import *
from funcionesVACIAS import *

from extras import *

#Funcion principal
def main():
        #Centrar la ventana y despues inicializar pygame
        os.environ["SDL_VIDEO_CENTERED"] = "1"
        pygame.init()
        #pygame.mixer.init()

        #Preparar la ventana
        pygame.display.set_caption("Pasa palabra...")
        screen = pygame.display.set_mode((ANCHO, ALTO))

        #musica de inicio
        pygame.mixer.music.load("Boomberman.mp3")
        #reprod del sonido
        pygame.mixer.music.play()

        #sonido de PALABRA EXACTA
        acierto2=pygame.mixer.Sound("acierto2.mp3")

        #sonido de bonificacion
        bonus3=pygame.mixer.Sound("bonus3.mp3")

        #sonido de fin del juego
        gameover=pygame.mixer.Sound("gameover.mp3")

        #sonido de error
        error=pygame.mixer.Sound("error.mp3")

        #sonido de ACIERTO
        acierto=pygame.mixer.Sound("acierto.mp3")

##        #sonido de fondo mientras se está jugando
##        jugando=pygame.mixer.Sound("jugando.mp3")

        #tiempo total del juego
        gameClock = pygame.time.Clock()
        totaltime = 0
        segundos = TIEMPO_MAX
        fps = FPS_inicial

        #iniciacion de variables

        puntos = 0   #puntos
        candidata = ""      #palabra candidata ingresada por usuario
        listaFrases=[]     #lista de frases que usamos en la funcion "lectura"
        listaPalabrasDiccionario=[] #Diccionario de palabras que usamos en la funcion "lectura2"
        palabras_ocupadas=[] #lista con palabras ya usadas
        listaPalabrasLargasOcupadas=[] #lista para la funcion elegir la mas larga
        cont=0 #contador  SUMA DE ACIERTOS

        archivo= open("Matrix1999.txt","r")
        archivo2= open ("lemario.txt","r")
        #lectura del diccionario
        lectura2( listaPalabrasDiccionario)

        #lectura del archivo. Cada linea es una frase
        lectura( listaFrases)

        #de cada frase elige la palabra mas larga
        listaPalabrasPeli=frasesToPalabras(listaFrases)

        palabraActual=nuevaPalabra(listaPalabrasPeli,palabras_ocupadas)  #toma una nueva palabra
        palabraActual=elegirLaMasLarga(listaPalabrasPeli,listaPalabrasLargasOcupadas)  #elige la mas larga

        palabraActual=quitarCaracteresEspeciales(palabraActual)     #quita caracteres especiales
        oculta=ocultar(palabraActual)  #oculta letras reemplazando con ***

        dibujar(screen, candidata, palabraActual, oculta, puntos,segundos) #funcion de los profes

        #Este ciclo sirve para inicializar una pantalla de presentación cuando el usuario aprete "espacio" inicia el juego

        banderin=True #bandera de control para la condicion del while
        fondo = pygame.image.load("cosmos1.png").convert() #carga una imagen de inicio de pantalla(surface)
        fondo2 = pygame.image.load("cosmos.png").convert() #fondo del juego corriendo

        while banderin: #ESTE CICLO MUESTRA LA IMAGEN DE INICIO DEL JUEGO Y SE TERMINA CON LA TECLA ESPACIO

            # Indicamos la posicion de las "Surface" sobre la ventana
            screen.blit(fondo, (5,0))
            pygame.display.flip()


            for event in pygame.event.get(): #buscamos si el usuario preciona un tecla
                if event.type==QUIT: #si el usuario quiere salir que se termine el juego
                    pygame.quit()
                    sys.exit()


                keys=pygame.key.get_pressed()
                if (keys[pygame.K_ESCAPE]):# para salir del juego apretando escape por si no kiere cerrar la ventana
                    pygame.quit()
                    sys.exit()

                if keys[K_SPACE]:# si la tecla apretada es "espacio" cambia el valor boleano para terminar
                             # con el cilco de pantalla de inicio
                    banderin=False
                    pygame.mixer.music.stop()

        bandera=True #VALOR BOOLEANO PARA GENERAR EL CICLO SIGUIENTE:

        while bandera: #ciclo de fondo de juego y fondo de "game over"


            if segundos > fps/1000: #CONDICION TIEMPO MAYOR A FPS
            # 1 frame cada 1/fps segundos
                gameClock.tick(fps)
                totaltime += gameClock.get_time()

                if True:
                	fps = 3

                #Buscar la tecla apretada del modulo de eventos de pygame
                for e in pygame.event.get():

                    #QUIT es apretar la X en la ventana
                    if e.type == QUIT:
                        pygame.quit()
                        return()

                    #Ver si fue apretada alguna tecla
                    if e.type == KEYDOWN:
                        letra = dameLetraApretada(e.key)
                        candidata += letra
                        if e.key == K_BACKSPACE:   #si borra
                            candidata = candidata[0:len(candidata)-1]

                        if e.key == K_RETURN: #si toca enter "PASA PALABRA!" por lo tanto Cambia la varibale  "palabraActual"
                            if candidata=="":    #por otra palabra
                                palabraActual=elegirLaMasLarga(listaPalabrasPeli,listaPalabrasLargasOcupadas)
                                palabraActual=quitarCaracteresEspeciales(palabraActual)
                                oculta=ocultar(palabraActual)
                                candidata = ""
                            elif(esValida(candidata,oculta, palabraActual)==1):#si acierta

                                    pygame.mixer.music.load("acierto2.mp3")   #carga sonido de  acierto
                                    pygame.mixer.music.play() # si la palabra es correcta sonara el sonido de acierto
                                    cont=cont+1  # contador que suma los aciertos
                                    #procesocotidiano pero explioado
                                    puntos += procesar(candidata, oculta, palabraActual, listaPalabrasPeli, listaPalabrasDiccionario)
                                    #se vuelve a cambiar el contenido de palabraActual por otra palabra
                                    palabraActual=elegirLaMasLarga(listaPalabrasPeli,listaPalabrasLargasOcupadas) #agarra la mas larga
                                    palabraActual=quitarCaracteresEspeciales(palabraActual) #quita caracteres inutiles
                                    oculta=ocultar(palabraActual)    #oculta
                                    candidata = ""  #vuelve la pal candidata vacia
                                    if cont>=2: #si acerto 2 veces seguidas reproduce el sonido de seguidilla
                                        pygame.mixer.music.load("bonus3.mp3")   #carga sonido  de seguidilla
                                        pygame.mixer.music.play()                    #lo reproduce
                                        puntos+=procesar(candidata, oculta, palabraActual, listaPalabrasPeli, listaPalabrasDiccionario)
                                        cont1=0    #ciclo siguiente
                                        while cont1<250: #Creamos este ciclo para mostrar durante mas tiempo el "DALEDALE" que nos incita a seguir acertando

                                            defaultFont = pygame.font.Font(None, 50)
                                            ren = defaultFont.render("DALEDALEDALEDALEDALE!!!: " + str(int(puntos)), 1, COLOR_TEXTO) #mostramos los puntos ya que va sumando
                                            screen.blit(ren, (160,470))
                                            pygame.display.flip()
                                            cont1+=1
                            elif(esValida(candidata,oculta,palabraActual)==2):
                                    pygame.mixer.music.load("acierto.mp3")   #carga sonido de  acierto
                                    pygame.mixer.music.play() # si la palabra es correcta sonara el sonido de acierto
                                    cont=cont+1  # contador que suma los aciertos
                                    #procesocotidiano
                                    puntos += procesar(candidata, oculta, palabraActual, listaPalabrasPeli, listaPalabrasDiccionario)
                                    palabraActual=elegirLaMasLarga(listaPalabrasPeli,listaPalabrasLargasOcupadas)
                                    palabraActual=quitarCaracteresEspeciales(palabraActual)
                                    oculta=ocultar(palabraActual)
                                    candidata = ""
                            else:
                                    #print("candi",candidata,"ocul",oculta,"palabraactual:",palabraActual) #Prints de prueba
                                    pygame.mixer.music.load("error.mp3")   #carga sonido de error
                                    pygame.mixer.music.play() #suena sonido error
                                    pygame.display.flip()
                                    cont=0 #si la palabra no es correcta el contador vuelve a 0
                                    cont1=0 #ciclo siguiente
                                    while cont1<250:  #Creamos este ciclo para mostrar durante mas tiempo la palabra que el usuario no pudo adivinar

                                            defaultFont = pygame.font.Font(None, 50) #muestro palabra que el usuario no adivino
                                            ren = defaultFont.render(palabraActual, 1, COLOR_TEXTO)
                                            screen.blit(ren, (160,470))
                                            pygame.display.flip()
                                            cont1+=1 #varible de control de ciclo                                 ##FUNCION INUTIL PERO LA DEJAMOS COMO PRUEBA
                                            #dibujoerrada(screen,palabraActual) ##llamo a la funcion dibujoerrada #ANTERIORMENTE HABIAMOS CREADO ESTA FUNCION
                                                                                # Para dibujar la palabra hasta que encontramos la falicidad de dicho ciclo


                                    #proceso cotidiano pero restando
                                    puntos-= len(palabraActual)
                                    palabraActual=elegirLaMasLarga(listaPalabrasPeli,listaPalabrasLargasOcupadas)
                                    palabraActual=quitarCaracteresEspeciales(palabraActual)
                                    oculta=ocultar(palabraActual)
                                    candidata = ""

                segundos = TIEMPO_MAX - pygame.time.get_ticks()/1000

                #Limpiar pantalla anterior

                screen.blit(fondo2,(0,0))
                #screen.fill(COLOR_FONDO)#lo cambiamos

                #Dibujar de nuevo todo
                dibujar(screen, candidata, palabraActual, oculta, puntos,segundos)


                pygame.display.flip()

            if len(palabras_ocupadas)>=len(listaPalabrasPeli): #este if termina el juego si la lista de palabras ocupadas es mayor a
                                                                #lista palabras peli, es decir si se ocuparon todas las palabras
                #parar la musica de fondo al terminar el juego
                    pygame.mixer.music.stop()

                #reproduce el sonido de fin del juego
                    pygame.mixer.music.load("gameover.mp3")   #carga sonido de fin del juego
                    pygame.mixer.music.play()

                    fondo = pygame.image.load("gameover.png").convert()# carga imagen de fin del juego
                # Indicamos la posicion de las "Surface" sobre la ventana
                    screen.blit(fondo, (0,0))  #cambiamos el color a negro ya que nos favorecia mas con el fondo elegido
                    defaultFont = pygame.font.Font(None, 50) #muestro los puntos totales obtenidos al final del juego
                    ren = defaultFont.render("PUNTUACION FINAL: " + str(int(puntos)), 1, COLOR_TEXTO)
                    screen.blit(ren, (260,160))
                    pygame.display.flip()

                    bandera=False
            elif segundos < fps/1000: # este ciclo termina el juego cuando termine el tiempo


            #parar la musica de fondo al terminar el juego
                    pygame.mixer.music.stop()

            #reproduce el sonido de fin del juego
                    pygame.mixer.music.load("gameover.mp3")   #carga sonido de
                    pygame.mixer.music.play()
                    fondo = pygame.image.load("gameover.png").convert()# carga imagen de fin del juego
            # Indicamos la posicion de las "Surface" sobre la ventana
                    screen.blit(fondo, (0,0))
                    defaultFont= pygame.font.Font( pygame.font.get_default_font(), TAMANNO_LETRA)
                    ren=defaultFont.render("PUNTUACION FINAL: " + str(int(puntos)), 1, (0,0,0))
                    screen.blit(ren, (260,160))
                    pygame.display.flip()
                    bandera=False



                    pygame.display.flip()

        while 1:
            #Esperar el QUIT del usuario
            for e in pygame.event.get():
                if e.type == QUIT:
                    pygame.quit()
                    return

#Programa Principal ejecuta Main
if __name__ == "__main__":
    main()
