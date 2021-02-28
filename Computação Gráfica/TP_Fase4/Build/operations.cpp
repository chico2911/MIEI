#include "operations.h"

/*
 * Inicializa��o da estrutura Group com todas as posi��es
 * a apontar para NULL.
 */
void initGroup(Group g)
{
    for (int i = 0; i < 40; i++) g[i] = NULL;
}


/**
 * Adiciona um ponto ao vetor de pontos.
 */
void addPoint(double x, double y, double z, vector<Ponto>& points)
{
    Ponto point;
    point.x = x;
    point.y = y;
    point.z = z;

    points.push_back(point);
}


/*
 * Adiciona um novo nodo a uma lista ligada de uma posi��o do array Group.
 */
void addOpGroup(Group group, int i, char operation[], double x, double y, double z, double angle, Transform* transform)
{
    OperAux* main = group[i];

    OperAux* novo = (OperAux*)malloc(sizeof(struct operationAux));
    novo->operation = (char*)malloc(sizeof(char) * 10);
    strcpy(novo->operation, operation);
    novo->x = x;
    novo->y = y;    
    novo->z = z;
    novo->angle = angle;
    novo->transform = transform;
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
 * Adiciona um OperFile ao vector files que guarda todas as opera��es
 * a realizar em rela��o a um ficheiro.
 */
void addOperFile(char* fileName, Group group, vector<OperFile*>& files, char* texture, Color* color)
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
            newOp->transform = tmp->transform;
            
            newOp->operation = new char[15];
            strcpy(newOp->operation, tmp->operation);

            operations.push_back(newOp);
        }
    }

    novo->operations = operations;
    strcpy(novo->fileName, fileName);

    if (texture != NULL) {
        novo->texture = new char[30];
        strcpy(novo->texture, texture);
    }
    else novo->texture = NULL;

    if (color != NULL) {
        novo->color = new Color();
        (novo->color)->component = new char[12];
        strcpy((novo->color)->component, color->component);

        (novo->color)->r = color->r;
        (novo->color)->g = color->g;
        (novo->color)->b = color->b;
    }
    else novo->color = NULL;

    files.push_back(novo);
}
