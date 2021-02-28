#ifndef CG_TP_OPERATIONS_H
#define CG_TP_OPERATIONS_H

#include <iostream>
#include <vector>
#include <string.h>

using namespace std;


/*
 Estrutura com informa��o realtiva a uma opera��o.
 Nome da opera��o e suas vari�veis.
*/
typedef struct oper {
    char* operation;
    double x, y, z, angle;
} Oper;


/*
 Estrutura que atribui a cada ficheiro .3d as respetivas
opera��es a realizar.
 */
typedef struct operFile {
    char* fileName;
    vector<Oper*> operations;
} OperFile;


/*
 Lista ligada auxiliar com informa��o realtiva a uma opera��o.
 Apoio � leitura de grupos secund�rios.
 */
typedef struct operationAux {
    char* operation;
    double x, y, z, angle;
    struct operationAux* next;
} OperAux;


/*
 Array de hierarquias, que cont�m OperAux's, ou seja,
listas ligadas de opera��es e suas vari�veis.
 */
typedef OperAux* Group[10];



void initGroup(Group g);

void addOpGroup(Group g, int i, char opName[], double x, double y, double z, double angle);

void addOperFile(char* fileName, Group g, vector<OperFile*>& files);

#endif //CG_TP_OPERATIONS_H