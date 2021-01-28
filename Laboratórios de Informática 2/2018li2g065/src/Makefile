CFLAGS=-std=c11 -Wall -Wextra -pedantic -g
FICHEIROS=cgi.h estado.c estado.h GandaGalo65.c Makefile
EXECUTAVEL=GandaGalo65

install: $(EXECUTAVEL)
	sudo cp $(EXECUTAVEL) /usr/lib/cgi-bin
	touch install

$(EXECUTAVEL): GandaGalo65.o estado.o
	cc -o $(EXECUTAVEL) GandaGalo65.o estado.o

imagens:
	sudo mkdir -p /var/www/html/images
	sudo cp *.png /var/www/html/images

estados:
	sudo mkdir -p /var/www/html/estados
	sudo cp *.txt /var/www/html/estados

tabuleiros:
	sudo mkdir -p /var/www/html/tabuleiros
	sudo cp *.txt /var/www/html/tabuleiros

GandaGalo65.zip: $(FICHEIROS)
	zip -9 GandaGalo65.zip $(FICHEIROS)

doc:
	doxygen -g
	doxygen

clean:
	rm -rf *.o $(EXECUTAVEL) Doxyfile latex html install

estado.o: estado.c estado.h
GandaGalo65.o: GandaGalo65.c cgi.h estado.h
