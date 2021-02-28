#ifndef CG_TP_OPERATIONS_H
#define CG_TP_OPERATIONS_H

#include <iostream>
#include <vector>
#include <string.h>

using namespace std;


/*
 Estrutura com informação realtiva a uma operação.
 Nome da operação e suas variáveis.
*/
typedef struct oper {
    char* operation;
    double x, y, z, angle;
} Oper;


/*
 Estrutura que atribui a cada ficheiro .3d as respetivas
operações a realizar.
 */
typedef struct operFile {
    char* fileName;
    vector<Oper*> operations;
} OperFile;


/*
 Lista ligada auxiliar com informação realtiva a uma operação.
 Apoio à leitura de grupos secundários.
 */
typedef struct operationAux {
    char* operation;
    double x, y, z, angle;
    struct operationAux* next;
} OperAux;


/*
 Array de hierarquias, que contém OperAux's, ou seja,
listas ligadas de operações e suas variáveis.
 */
typedef OperAux* Group[10];



void initGroup(Group g);

void addOpGroup(Group g, int i, char opName[], double x, double y, double z, double angle);

void addOperFile(char* fileName, Group g, vector<OperFile*>& files);

#endif //CG_TP_OPERATIONS_H