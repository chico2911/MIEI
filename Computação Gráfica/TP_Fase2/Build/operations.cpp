
#include "operations.h"

/*
 * Inicialização da estrutura Group com todas as posições
 * a apontar para NULL.
 */
void initGroup(Group g)
{
    for (int i = 0; i < 10; i++) g[i] = NULL;
}


/*
 * Adiciona um novo nodo a uma lista ligada de uma posição do array Group.
 */
void addOpGroup(Group group, int i, char operation[], double x, double y, double z, double angle)
{
    OperAux* main = group[i];

    OperAux* novo = (OperAux*)malloc(sizeof(struct operationAux));
    novo->operation = (char*)malloc(sizeof(char) * 10);
    strcpy(novo->operation, operation);
    novo->x = x;
    novo->y = y;    
    novo->z = z;
    novo->angle = angle;
    novo->next = NULL;

    if (group[i] == NULL) { group[i] = novo; }
    else {
        while (main->next != NULL) {
            main = main->next;
        }
        main->next = novo;
    }
}


/*
 * Adiciona um OperFile ao vector files que guarda todas as operações
 * a realizar em relação a um ficheiro.
 */
void addOperFile(char* fileName, Group group, vector<OperFile*>& files)
{
    OperFile* novo = new OperFile();
    novo->fileName = new char[15];
    vector<Oper*> operations;

    for (int i = 0; group[i]; i++) {
        OperAux* tmp;
        for (tmp = group[i]; tmp; tmp = tmp->next) {
            Oper* newOp = new Oper();

            newOp->x = tmp->x;
            newOp->y = tmp->y;
            newOp->z = tmp->z;
            newOp->angle = tmp->angle;
            
            newOp->operation = new char[15];
            strcpy(newOp->operation, tmp->operation);

            operations.push_back(newOp);
        }
    }

    novo->operations = operations;
    strcpy(novo->fileName, fileName);
    files.push_back(novo);
}