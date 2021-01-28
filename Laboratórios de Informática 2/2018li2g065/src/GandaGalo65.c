/**
@file GandaGalo65.c
Esqueleto do programa
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cgi.h" 
#include "estado.h"
#include "Stack.h"

#define MAX_BUFFER      10240
#define GRELHA      4
#define TAM     55
#define PATH_TABULEIROS "/var/www/html/tabuleiros/"
#define PATH_ESTADOS "/var/www/html/estados/"

const char *ficheiro [] = {"bloq.png", "X.png", "O.png", "vazio.png", "X.png", "O.png"};
int cont_lin;

/**
Função que efetua a ajuda
@param e Estado do tabuleiro
@returns e Estado do tabuleiro com a ajuda
*/
ESTADO help (ESTADO e) {
  int l,c;
  // linha XX_
  for(l=0;l<e.num_lins;l++){
    for(c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l][c+1]==SOL_X) || (e.grelha[l][c+1]==FIXO_X))) && (e.grelha[l][c+2]==VAZIA)){
        (e.grelha[l][c+2]=SOL_O);
        return e;
      } else {
        if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l][c+1]==SOL_X) || (e.grelha[l][c+1]==FIXO_X))) && (e.grelha[l][c-2]==VAZIA)){
        (e.grelha[l][c-2]=SOL_O);
        return e;
        }
      }
    }
  }
  //linha OO_
  for(l=0;l<e.num_lins;l++){
    for(c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l][c+1]==SOL_O) || (e.grelha[l][c+1]==FIXO_O))) && (e.grelha[l][c+2]==VAZIA)){
        (e.grelha[l][c+2]=SOL_X);
        return e;
      } else {
        if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l][c+1]==SOL_O) || (e.grelha[l][c+1]==FIXO_O))) && (e.grelha[l][c-2]==VAZIA)){
        (e.grelha[l][c-2]=SOL_X);
        return e;
        }
      }
    }
  }
  // linha X_X
  for(l=0;l<e.num_lins;l++){
    for(c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l][c+2]==SOL_X) || (e.grelha[l][c+2]==FIXO_X))) && (e.grelha[l][c+1]==VAZIA)){
        (e.grelha[l][c+1]=SOL_O);
        return e;
      } 
      }
    }
  // linha O_O
  for(l=0;l<e.num_lins;l++){
    for(c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l][c+2]==SOL_O) || (e.grelha[l][c+2]==FIXO_O))) && (e.grelha[l][c+1]==VAZIA)){
        (e.grelha[l][c+1]=SOL_X);
        return e;
      } 
      }
    }
  //coluna XX_
  for(c=0;c<e.num_lins;c++){
    for(l=0;l<e.num_cols;l++){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+1][c]==SOL_X) || (e.grelha[l+1][c]==FIXO_X))) && (e.grelha[l+2][c]==VAZIA)){
        (e.grelha[l+2][c]=SOL_O);
        return e;
      } else {
        if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+1][c]==SOL_X) || (e.grelha[l+1][c]==FIXO_X))) && (e.grelha[l-2][c]==VAZIA)){
        (e.grelha[l-2][c]=SOL_O);
        return e;
        }
      }
    }
  }
  //coluna OO_
  for(c=0;c<e.num_lins;c++){
    for(l=0;l<e.num_cols;l++){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+1][c]==SOL_O) || (e.grelha[l+1][c]==FIXO_O))) && (e.grelha[l+2][c]==VAZIA)){
        (e.grelha[l][c+2]=SOL_X);
        return e;
      } else {
        if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+1][c]==SOL_O) || (e.grelha[l+1][c]==FIXO_O))) && (e.grelha[l-2][c]==VAZIA)){
        (e.grelha[l-2][c]=SOL_X);
        return e;
        }
      }
    }
  }
  //coluna X_X
  for(c=0;c<e.num_lins;c++){
    for(l=0;l<e.num_cols;l++){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+2][c]==SOL_X) || (e.grelha[l+2][c]==FIXO_X))) && (e.grelha[l+1][c]==VAZIA)){
        (e.grelha[l+1][c]=SOL_O);
        return e;
      } 
      }
    }
  //colunac O_O
  for(c=0;c<e.num_lins;c++){
    for(l=0;l<e.num_cols;l++){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+2][c]==SOL_O) || (e.grelha[l+2][c]==FIXO_O))) && (e.grelha[l+1][c]==VAZIA)){
        (e.grelha[l+1][c]=SOL_X);
        return e;
      } 
    }
  }
  // Diagonal 1 XX_
  for(l=0,c=0;l<e.num_lins && c<e.num_cols;l++,c++){
    if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+1][c+1]==SOL_X) || (e.grelha[l+1][c+1]==FIXO_X))) && (e.grelha[l+2][c+2]==VAZIA)){
      (e.grelha[l+2][c+2]=SOL_O);
      return e;
    }
  }
  // Diagonal 1 X_X
  for(l=0,c=0;l<e.num_lins && c<e.num_cols;l++,c++){
    if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+2][c+2]==SOL_X) || (e.grelha[l+2][c+2]==FIXO_X))) && (e.grelha[l+1][c+1]==VAZIA)){
      (e.grelha[l+1][c+1]=SOL_O);
      return e;
    }
  }
  // Diagonal 1 OO_
  for(l=0,c=0;l<e.num_lins && c<e.num_cols;l++,c++){
    if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+1][c+1]==SOL_O) || (e.grelha[l+1][c+1]==FIXO_O))) && (e.grelha[l+2][c+2]==VAZIA)){
      (e.grelha[l+2][c+2]=SOL_X);
      return e;
    }
  }
  // Diagonal 1 O_O
  for(l=0,c=0;l<e.num_lins && c<e.num_cols;l++,c++){
    if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+2][c+2]==SOL_O) || (e.grelha[l+2][c+2]==FIXO_O))) && (e.grelha[l+1][c+1]==VAZIA)){
      (e.grelha[l+1][c+1]=SOL_X);
      return e;
    }
  }
  // Diagonal 2 XX_
  for(l=0,c=e.num_cols;l<e.num_lins && c>0;l++,c--){
    if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+1][c-1]==SOL_X) || (e.grelha[l+1][c-1]==FIXO_X))) && (e.grelha[l+2][c-2]==VAZIA)){
      (e.grelha[l+2][c-2]=SOL_O);
      return e;
    }
  }
  // Diagonal 2 X_X
  for(l=0,c=e.num_cols;l<e.num_lins && c>0;l++,c--){
    if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+2][c-2]==SOL_X) || (e.grelha[l+2][c-2]==FIXO_X))) && (e.grelha[l+1][c-1]==VAZIA)){
      (e.grelha[l+1][c-1]=SOL_O);
      return e;
    }
  }
  // Diagonal 2 OO_
  for(l=0,c=e.num_cols;l<e.num_lins && c>0;l++,c--){
    if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+1][c-1]==SOL_O) || (e.grelha[l+1][c-1]==FIXO_O))) && (e.grelha[l+2][c-2]==VAZIA)){
      (e.grelha[l+2][c-2]=SOL_X);
      return e;
    }
  }
  // Diagonal 2 O_O
  for(l=0,c=e.num_cols;l<e.num_lins && c>0;l++,c--){
    if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+2][c-2]==SOL_O) || (e.grelha[l+2][c-2]==FIXO_O))) && (e.grelha[l+1][c-1]==VAZIA)){
      (e.grelha[l+1][c-1]=SOL_X);
      return e;
    }
  }
  // Diagonais XX_
  for(l=1;l<e.num_lins;l++){
    for (c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+1][c+1]==SOL_X) || (e.grelha[l+1][c+1]==FIXO_X))) && (e.grelha[l+2][c+2]==VAZIA)){
      (e.grelha[l+2][c+2]=SOL_O);
      return e;
      }
    l++;
    }
  }
  // Diagonais X_X
  for(l=1;l<e.num_lins;l++){
    for (c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+2][c+2]==SOL_X) || (e.grelha[l+2][c+2]==FIXO_X))) && (e.grelha[l+1][c+1]==VAZIA)){
      (e.grelha[l+1][c+1]=SOL_O);
      return e;
      }
    l++;
    }
  }
  // Diagonais OO_
  for(l=1;l<e.num_lins;l++){
    for (c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+1][c+1]==SOL_O) || (e.grelha[l+1][c+1]==FIXO_O))) && (e.grelha[l+2][c+2]==VAZIA)){
      (e.grelha[l+2][c+2]=SOL_X);
      return e;
      }
    l++;
    }
  }
  // Diagonais O_O
  for(l=1;l<e.num_lins;l++){
    for (c=0;c<e.num_cols;c++){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+2][c+2]==SOL_O) || (e.grelha[l+2][c+2]==FIXO_O))) && (e.grelha[l+1][c+1]==VAZIA)){
      (e.grelha[l+1][c+1]=SOL_X);
      return e;
      }
    l++;
    }
  }
    // Diagonais 2 XX_
  for(l=1;l<e.num_lins;l++){
    for (c=e.num_cols;c>0;c--){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+1][c+1]==SOL_X) || (e.grelha[l+1][c+1]==FIXO_X))) && (e.grelha[l+2][c+2]==VAZIA)){
      (e.grelha[l+2][c+2]=SOL_O);
      return e;
      }
    l++;
    }
  }
  // Diagonais 2 X_X
  for(l=1;l<e.num_lins;l++){
    for (c=e.num_cols;c>0;c--){
      if ((((e.grelha[l][c]==SOL_X) || (e.grelha[l][c]==FIXO_X)) && ((e.grelha[l+2][c+2]==SOL_X) || (e.grelha[l+2][c+2]==FIXO_X))) && (e.grelha[l+1][c+1]==VAZIA)){
      (e.grelha[l+1][c+1]=SOL_O);
      return e;
      }
    l++;
    }
  }
  // Diagonais 2 OO_
  for(l=1;l<e.num_lins;l++){
    for (c=e.num_cols;c>0;c--){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+1][c+1]==SOL_O) || (e.grelha[l+1][c+1]==FIXO_O))) && (e.grelha[l+2][c+2]==VAZIA)){
      (e.grelha[l+2][c+2]=SOL_X);
      return e;
      }
    l++;
    }
  }
  // Diagonais 2 O_O
  for(l=1;l<e.num_lins;l++){
    for (c=e.num_cols;c>0;c--){
      if ((((e.grelha[l][c]==SOL_O) || (e.grelha[l][c]==FIXO_O)) && ((e.grelha[l+2][c+2]==SOL_O) || (e.grelha[l+2][c+2]==FIXO_O))) && (e.grelha[l+1][c+1]==VAZIA)){
      (e.grelha[l+1][c+1]=SOL_X);
      return e;
      }
    l++;
    }
  }
  return e;
}



/**
Função que verifica se a linha do tabuleiro está valida
@param e Estado atual
@returns 1 ou 0 dependente da veracidade do tabuleiro
*/
int validalinha (ESTADO e){
	int linha,coluna;
	char pecant=VAZIA;
	int conta=0;
	for(linha=0;linha<e.num_lins;linha++){
		for(coluna=0;coluna<e.num_cols;coluna++){
            if((((e.grelha[linha][coluna]==SOL_X) || (e.grelha[linha][coluna]==FIXO_X)) && ((pecant==SOL_X || pecant==FIXO_X)) || ((e.grelha[linha][coluna]==SOL_O) || (e.grelha[linha][coluna]==FIXO_O))&&((pecant==SOL_O || pecant==FIXO_O))) && (e.grelha[linha][coluna]!=VAZIA) && (e.grelha[linha][coluna]!=BLOQUEADA)) conta++;
            else {
                conta=0;
           	    pecant=e.grelha[linha][coluna];
		    }
		    if(conta>=2) return 1;
	    }
	    pecant=VAZIA;
    }
    return 0;
}    


/**
Função que verifica se a coluna do tabuleiro está valida
@param e Estado atual
@returns 1 ou 0 dependente da veracidade do tabuleiro
*/
int validacoluna (ESTADO e){
	int l,c;
	int conta=0;
	char pos=VAZIA;
	for(c=0;c<e.num_cols;c++){
		for(l=0;l<e.num_lins;l++){
            if ((((e.grelha[l][c]==SOL_X || (e.grelha[l][c]==FIXO_X) && (pos==SOL_X || pos==FIXO_X)) || ((e.grelha[l][c]==SOL_O || e.grelha[l][c]==FIXO_O) && (pos==SOL_O || pos==FIXO_O))) && e.grelha[l][c]!=VAZIA && e.grelha[l][c]!=BLOQUEADA)) {conta++;}
            else {
                conta=0;
           	    pos=e.grelha[l][c];
		    }
		    if (conta>=2) return 1;
	    }
	    pos=VAZIA;
    }
    return 0;
}

/**
Função auxiliar que verifica se a diagonal do tabuleiro está valida
@param e Estado atual
@param linha atual
@param coluna atual
@returns 1 ou 0 dependente da veracidade do tabuleiro
*/
int vd1Aux (ESTADO e,int linha,int coluna){
    char pos=VAZIA;
    int conta=0;
    while(linha<e.num_lins && coluna<e.num_cols){
        if ((((e.grelha[linha][coluna]==SOL_X || e.grelha[linha][coluna]==FIXO_X) && (pos==SOL_X || pos==FIXO_X)) || ((e.grelha[linha][coluna]==SOL_O || e.grelha[linha][coluna]==FIXO_O) && (pos==SOL_O || pos==FIXO_O))) && e.grelha[linha][coluna]!=VAZIA && e.grelha[linha][coluna]!=BLOQUEADA) conta++;
        else {
        	conta=0;
        	pos=e.grelha[linha][coluna];
        }
        linha++;
        coluna++;
        if (conta>=2) return 0;
    }
    return 1;
}

/**
Função que verifica se a diagonal do tabuleiro está valida
@param e Estado atual
@returns 1 ou 0 dependente da veracidade do tabuleiro
*/
int validadiagonal1(ESTADO e){
	int l=0,c=0;
	int conta=vd1Aux(e,0,0);
	while(l<e.num_lins && c<e.num_cols && conta != 0){
        conta = vd1Aux(e,l++,0)*vd1Aux(e,0,c++)*conta;
	}
	if (conta==0) {return 1;}
	else {return 0;}
}

/**
Função auxiliar que verifica se a diagonal do tabuleiro está valida
@param e Estado atual
@param linha atual
@param coluna atual
@returns 1 ou 0 dependente da veracidade do tabuleiro
*/
int vd2Aux (ESTADO e, int linha, int coluna){
    char pos=VAZIA;
    int conta=0;
    while(linha<e.num_lins && coluna>=0){
        if ((((e.grelha[linha][coluna]==SOL_X || e.grelha[linha][coluna]==FIXO_X) && (pos==SOL_X || pos==FIXO_X)) || ((e.grelha[linha][coluna]==SOL_O || e.grelha[linha][coluna]==FIXO_O) && (pos==SOL_O || pos==FIXO_O))) && e.grelha[linha][coluna]!=VAZIA && e.grelha[linha][coluna]!=BLOQUEADA) conta++;
        else {
        	conta=0;
        	pos=e.grelha[linha][coluna];
        }
        linha++;
        coluna--;
        if (conta>=2) {return 0;}
    }
    return 1;
}

/**
Função que verifica se a diagonal do tabuleiro está valida
@param e Estado atual
@returns 1 ou 0 dependente da veracidade do tabuleiro
*/
int validadiagonal2(ESTADO e){
    int l=0,c=e.num_cols-1;
    int conta;
	conta=vd2Aux(e,0,c);
	while(l<e.num_lins && c>=0 && conta != 0){
		l++;
		c--;
		conta = vd2Aux(e,0,c)*vd2Aux(e,l,e.num_cols-1)*conta;
	}
    if (conta==0) {return 1;}
	else {return 0;}
}



ESTADO undo(ESTADO e){
	ESTADOPOS ep = pop(&e);
	e = processa(e,ep.acao);
	return e;
}

/**
Função que lê o estado do ficheiro Undo.txt
@param c contador de linha do ficheiro
@returns e Estado atual
*/
ESTADO ler_undo (int c){
	char x[MAX_BUFFER];
	fprintf(stderr, "%d\n",c);
	int i;
	char filename[100];
	sprintf(filename,"%sundo.txt",PATH_ESTADOS);
	FILE *fp=fopen(filename,"r");
	for(i=0;i<c;i++) {
    	fscanf(fp,"%s",x);			
    }
    ESTADO e = str2estado(x);
	return e;
}

/**
Função que grava no ficheiro Undo.txt o estado
@param e estado do tabuleiro
@param c contador de linha do ficheiro
@returns c contador incrementado da linha do ficheiro
*/
int gravar_undo_ficheiro(ESTADO e,int c){
	char estado[100];
	fprintf(stderr, "%s\n",estado2str(e));
	sprintf(estado,"%sundo.txt",PATH_ESTADOS);
	FILE *fp = fopen(estado,"a");
	fprintf(fp,"%s\n",estado2str(e));
	fclose(fp);
	c++;
	return c;
}

/**
Função que lê o puzzle atual do ficheiro
@returns o numero do puzzle
*/
int ler_puzzle (){
	int v=0;
	char filename[100];
	sprintf(filename,"%sPuzzle.txt",PATH_ESTADOS);
	FILE *fp=fopen(filename,"r");
	fscanf (fp,"%d",&v);
	return v;
}

/**
Função que grava o puzzle atual do ficheiro
@param v valor do puzzle do ficheiro
*/
void gravar_puzzle_ficheiro(int v){
	char estado[100];
	sprintf(estado,"%sPuzzle.txt",PATH_ESTADOS);
	FILE *fp = fopen(estado,"w");
	fprintf(fp,"%d",v);
	fclose(fp);
}

/**
Função que lê o estado atual do ficheiro
@returns e Estado do tabuleiro
*/
ESTADO ler_estado_ficheiro (){
	char filename[100];
	char *e=NULL;
	sprintf(filename,"%s_estado.txt",PATH_ESTADOS);
	FILE *fp=fopen(filename,"r");
	fscanf (fp,"%s",e);
	return str2estado(e);
}

/**
Função que grava o estado atual do ficheiro
@param e Estado do tabuleiro
*/
void gravar_estado_ficheiro(ESTADO e){
	char *estado;
	char filename[100];
	sprintf(filename,"%s_estado.txt",PATH_ESTADOS);
	FILE *fp = fopen(filename,"w");
	estado = estado2str(e);
	fprintf(fp,"%s",estado);
	fclose(fp);
}



/**
Função para as casas fixas na grelha
@param e estado do tabuleiro
@param l linha onde se vai imprimir a casa
@param c coluna onde se vai imprimir a casa
*/
void imprimeCasa (ESTADO e, int l, int c){
  VALOR v = e.grelha[l][c];
  IMAGEM(c,l,TAM,ficheiro[v]);
}

/**
Função que atualiza a casa com os vários links na grelha
@param e estado do tabuleiro
@param l linha onde se vai imprimir a casa
@param c coluna onde se vai imprimir a casa
*/
void imprimeCasa_link(ESTADO e, int l, int c){
  ESTADO ne = e;
  VALOR v = e.grelha[l][c];
  char link [MAX_BUFFER];
  switch (v) {
          case VAZIA: 
              ne.grelha[l][c]=SOL_X;
              break;
          case SOL_X:
              ne.grelha[l][c]=SOL_O;             
              break;
          case SOL_O:
              ne.grelha[l][c]=VAZIA;
              break;
          default:
              ne.grelha[l][c]=v;
              break;
  }
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(ne));
  ABRIR_LINK(link);
  IMAGEM(c,l,TAM,ficheiro[v]);
  FECHAR_LINK;
}

/**
Função que inicializa o estado
@returns Um novo estado
*/
ESTADO inicializar() {
  ESTADO e;
  e.num_lins = GRELHA;
  e.num_cols = GRELHA;	
  int lin, col;
  for (lin=0; lin<e.num_lins; lin++){
       for (col=0; col<e.num_cols; col++){
          e.grelha [lin][col] = VAZIA; 
        }
  }  
  e.grelha [0][1] = BLOQUEADA;    
  e.grelha [0][3] = FIXO_X;
  e.grelha [1][1] = FIXO_O;
  e.grelha [2][0] = FIXO_X;
  e.grelha [3][3] = FIXO_O;
  return e;
}

/**
Função que lê os diferentes tabuleiros
@param filename nome do ficheiro
@returns e Estado
*/
ESTADO ler_tabuleiro(char *filename){
  ESTADO e;
  int L,C;
  char linha [100];
  FILE *fp= fopen (filename,"r");
  fscanf (fp,"%d %d",&L, &C);
  e.num_lins=L;
  e.num_cols=C;
  for(L=0;L<e.num_lins;L++) {
    fscanf (fp,"%s",linha);
    for(C=0;C<e.num_cols;C++){
      switch (linha[C]){
        case'.': e.grelha[L][C]=VAZIA;
                 break;
        case'#': e.grelha[L][C]=BLOQUEADA;
                 break;
        case'X': e.grelha[L][C]=FIXO_X;
                 break;
        case'O': e.grelha[L][C]=FIXO_O;
                 break;
        default: e.grelha[L][C]=VAZIA;
      }
    }
  }
  fclose(fp);
  return e;
}

/**
Função que imprime a grelha
@param e estado do tabuleiro
*/
void imprimeGrelha(ESTADO e){
  int lin, col;
  VALOR v;
  for (lin=0; lin<e.num_lins; lin++){
       for (col=0; col<e.num_cols; col++){
          v = e.grelha[lin][col];
        switch (v) {
                case VAZIA:   
                case SOL_X:
                case SOL_O:
                    imprimeCasa_link(e,lin,col);
                    break;
                case FIXO_O:
                case FIXO_X:
                case BLOQUEADA:
                    imprimeCasa(e,lin,col);
                    break;
        }
       }
    }
}

/**
Função que remete para os diferentes links
@param e estado do tabuleiro
@param c contador da linha do ficheiro
*/
void imprimeBotoes(ESTADO e){
  char link [MAX_BUFFER];
  char nomef_jogo[100];
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?");
    ABRIR_LINK(link);
  IMAGEM(0, 3, 118, "novo.png");
  FECHAR_LINK;
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str());
    ABRIR_LINK(link);
  IMAGEM(1, 3, 118, "undo.png");
  FECHAR_LINK;
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(help(e)));
    ABRIR_LINK(link);
  IMAGEM(2, 3, 118, "ajuda.png");
  FECHAR_LINK;
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(e));
    ABRIR_LINK(link);
  IMAGEM(3, 3, 118, "ancora.png");
  FECHAR_LINK;
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(e));
    ABRIR_LINK(link);
  IMAGEM(4, 3, 118, "desfazer_ancora.png");
  FECHAR_LINK;
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(e));
    ABRIR_LINK(link);
  IMAGEM(5, 3, 118, "resolve.png");
  FECHAR_LINK;
  sprintf(nomef_jogo,"%sPuzzle1.txt",PATH_TABULEIROS);
  e = ler_tabuleiro(nomef_jogo);
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(e));
    ABRIR_LINK(link);
  IMAGEM(0, 4, 118, "tabuleiro_1.png");
  FECHAR_LINK;
  sprintf(nomef_jogo,"%sPuzzle2.txt",PATH_TABULEIROS);
  e = ler_tabuleiro(nomef_jogo);
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(e));
    ABRIR_LINK(link);
  IMAGEM(1, 4, 118, "tabuleiro_2.png");
  FECHAR_LINK;
  sprintf(nomef_jogo,"%sPuzzle3.txt",PATH_TABULEIROS);
  e = ler_tabuleiro(nomef_jogo);
  sprintf(link,"http://localhost/cgi-bin/GandaGalo65?%s",estado2str(e));
    ABRIR_LINK(link);
  IMAGEM(2, 4, 118, "tabuleiro_3.png");
  FECHAR_LINK;
}


ESTADO processa (ESTADO e, ACAO acao){
	switch (acao.decisao){
		case MARCA_X: e.grelha[acao.dec_lin][acao.dec_col]=SOL_X;
					  break;
		case MARCA_O: e.grelha[acao.dec_lin][acao.dec_col]=SOL_O;
					  break;
		case MARCA_VAZIO: e.grelha[acao.dec_lin][acao.dec_col]=VAZIA;
						  break;
		case VALIDA:
			if (validalinha(e)==0 && validacoluna(e)==0 && validadiagonal1(e)==0 && validadiagonal2(e)==0) IMAGEM(4,1,100, "valido.png");
    		else IMAGEM(4,1,100,"invalido.png");
    		break;
    	case UNDO:
    		e=undo(e);
    		break;
    	case VOLTA_ANCORA:
    		//e = volta_ancora(e);
    		break;
    	default: //FAZ NADA
    		break;
	}
}


/**
Lê o estado a partir da variável de ambiente QUERY_STR. Caso não seja passado um valor, chama a função inicializar
@param args O valor da variável (o que é passado depois de ? no URL)
@returns O estado
*/
//ESTADO ler_estado(char *args) {
  //if (strlen(args) == 0)
    //return inicializar();
  //return str2estado(args);
//}
ESTADO ler_estado (){
	int x,y;
	char user[100];
	int acao;
	ESTADO e={0};
	char nomef_estado[100];
	char nomef_jogo[100];
	char *args = getenv("QUERY_STRING");
	if (args[0]=='\0'){
		e = inicializar();
	}
	else {
		sscanf(args,"%[^#]%d,%d,%d",user,&acao,&x,&y);
		sprintf(nomef_estado,"%s%s_estado.txt",PATH_TABULEIROS,user);
		e = ler_tabuleiro(nomef_jogo);
		strcpy (e.user,user);
		e.acao.decisao = acao;
		e.acao.dec_lin = x;
		e.acao.dec_col = y;
	}
	return e;
}


/**
Função principal do programa
@returns 0 se tudo correr bem
*/
int main() {
  ESTADO e = ler_estado();
  e = processa (e,e.acao);
  COMECAR_HTML;
  ABRIR_SVG(620,900);
    imprimeGrelha (e);
    imprimeBotoes (e);
    if (validalinha(e)==0 && validacoluna(e)==0 && validadiagonal1(e)==0 && validadiagonal2(e)==0) IMAGEM(4,1,100, "valido.png");
    else IMAGEM(4,1,100,"invalido.png");
  FECHAR_SVG;
  FECHAR_HTML;
  return 0;     
}