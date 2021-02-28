
#include "parser.h"

/*
 * Parser XML auxiliar do Parser principal para percorrer sec��es de grupos.
 */
void parseGroup(const XMLElement* child, Group group, int i, vector<OperFile*>& files)
{
    for (const XMLElement* child2 = child->FirstChildElement(); child2; child2 = child2->NextSiblingElement())
    {
        if (strcmp((char*)child2->Value(), "group") == 0) {

            int j = i + 1;
            parseGroup(child2, group, j, files);

            for (j; j < 10; j++) group[j] = NULL;
        }

        if (strcmp((char*)child2->Value(), "models") == 0) {
            for (const XMLElement* child3 = child2->FirstChildElement(); child3; child3 = child3->NextSiblingElement()) {

                char aux[15];
                char* fileName = (char*)child3->Attribute("file");
                strcpy(aux, fileName);
                char file[40];
                file[0] = '\0';
                strcat(file, aux);

                addOperFile(file, group, files);
            }
        }

        if (strcmp((char*)child2->Value(), "translate") == 0) {

            char operation[15];
            strcpy(operation, (char*)child2->Value());

            double x = 0;
            double y = 0;
            double z = 0;

            char* X = (char*)child2->Attribute("X");
            if (X != NULL) x = atof(X);
            char* Y = (char*)child2->Attribute("Y");
            if (Y != NULL) y = atof(Y);
            char* Z = (char*)child2->Attribute("Z");
            if (Z != NULL) z = atof(Z);

            addOpGroup(group, i, operation, x, y, z, -1);
        }

        if (strcmp((char*)child2->Value(), "rotate") == 0) {

            char operation[15];
            strcpy(operation, (char*)child2->Value());

            double angleValue = 0;
            double x = 0;
            double y = 0;
            double z = 0;

            char* angle = (char*)child2->Attribute("angle");
            if (angle != NULL) angleValue = atof(angle);
            char* axisX = (char*)child2->Attribute("axisX");
            if (axisX != NULL) x = atof(axisX);
            char* axisY = (char*)child2->Attribute("axisY");
            if (axisY != NULL) y = atof(axisY);
            char* axisZ = (char*)child2->Attribute("axisZ");
            if (axisZ != NULL) z = atof(axisZ);

            addOpGroup(group, i, operation, x, y, z, angleValue);
        }

        if (strcmp((char*)child2->Value(), "scale") == 0) {

            char operation[15];
            strcpy(operation, (char*)child2->Value());

            double x = 0;
            double y = 0;
            double z = 0;

            char* X = (char*)child2->Attribute("X");
            if (X != NULL) x = atof(X);
            char* Y = (char*)child2->Attribute("Y");
            if (Y != NULL) y = atof(Y);
            char* Z = (char*)child2->Attribute("Z");
            if (Z != NULL) z = atof(Z);

            addOpGroup(group, i, operation, x, y, z, -1);
        }
    }
}

/*
 * Parser XML para percorrer e interpretar um config file,
 * usando tinyxml2.
 */
void xmlParser(const char* configFile, vector<OperFile*>& files)
{
    int i = 0;
    Group group;
    initGroup(group);

    XMLDocument doc;
    XMLError error = doc.LoadFile(configFile);

    if (!error) {
        XMLElement* elem = doc.FirstChildElement();
        for (const XMLElement* child = elem->FirstChildElement(); child; child = child->NextSiblingElement()) {
            if (strcmp(child->Value(), "group") == 0) {

                for (int j = i; j < 10; j++) group[i] = NULL;

                parseGroup(child, group, i, files);
            }
        }
    }
}