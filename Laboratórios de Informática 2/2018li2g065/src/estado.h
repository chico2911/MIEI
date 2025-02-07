#ifndef ___ESTADO_H___
#define ___ESTADO_H___

/**
@file estado.h
Definição do estado e das funções que convertem estados em strings e vice-versa
*/

/** \brief O tamanho máximo da grelha */
#define MAX_GRID		20
#define MAX_STACK		100
#define MAX_STRING_USER 20


typedef enum {BLOQUEADA, FIXO_X, FIXO_O, VAZIA, SOL_X, SOL_O} VALOR;
typedef enum {NADA, MARCA_X, MARCA_O, MARCA_VAZIO, UNDO, MARCA_ANCORA, VOLTA_ANCORA,VALIDA} JOGADAS;

/**
\brief Estrutura que armazena as diferentes jogadas
*/
typedef struct acao {
	char decisao;
	char dec_lin;
	char dec_col;
} ACAO;

/**
\brief Estrutura que armazena a estrutura ação e a variável "âncora"
*/
typedef struct estadopos {
	ACAO acao;
	char ancora;
} ESTADOPOS;

/**
\brief Estrutura que armazena a stack e o apontador para o seu topo
*/
typedef struct stackaltpos {
	ESTADOPOS sap[MAX_STACK];
	int top;
} STACKALTPOS;

/**
\brief Estrutura que armazena o estado do jogo
*/
typedef struct estado {
	char num_lins;
	char num_cols;
	char grelha[MAX_GRID][MAX_GRID];
	STACKALTPOS stack;
	ACAO acao;
	char user [MAX_STRING_USER];
} ESTADO;

/**
\brief Função que converte um estado numa string
@param e O estado
@returns A string correspondente ao estado e
*/
char *estado2str(ESTADO e);

/**
\brief Função que converte uma string num estado 
@param argumentos Uma string contendo os argumentos passados à CGI
@returns O estado correspondente à string dos argumentos
*/
ESTADO str2estado(char *argumentos);

#endif