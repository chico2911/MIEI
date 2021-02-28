#ifndef CG_TP_OPERATIONS_H
#define CG_TP_OPERATIONS_H

#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

/*
 Coordenadas de todos os tri�ngulos.
*/
struct Ponto {
    double x;
    double y;
    double z;
};

/*
 Estrutura com informa��o relativa a uma transforma��o dependente de um tempo.
 Tempo e vetor de pontos associados � transforma��o.
*/
typedef struct transform {
    double time;
    vector<Ponto> points;
} Transform;

/*
 Estrutura com informa��o realtiva a uma opera��o.
 Nome da opera��o e suas vari�veis.
*/
typedef struct oper {
    char* operation;
    double x, y, z, angle;
    Transform* transform;
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
    Transform* transform;
    struct operationAux* next;
} OperAux;


/*
 Array de hierarquias, que cont�m OperAux's, ou seja,
listas ligadas de opera��es e suas vari�veis.
 */
typedef OperAux* Group[40];



void initGroup(Group g);

void addPoint(double x, double y, double z, vector<Ponto>& points);

void addOpGroup(Group g, int i, char opName[], double x, double y, double z, double angle, Transform* transform);

void addOperFile(char* fileName, Group g, vector<OperFile*>& files);

#endif //CG_TP_OPERATIONS_H