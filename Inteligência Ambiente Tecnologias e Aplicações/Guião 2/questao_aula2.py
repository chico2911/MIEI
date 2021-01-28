import csv
import networkx as net
import operator
# importar a biblioteca matplotlib.pyplot
import matplotlib.pyplot as plt

# open the file
in_file = csv.reader(open('lista_arestas.txt', 'r'))

g = net.Graph()
for line in in_file:
    g.add_edge(line[0], line[1], weight=line[2], conf=line[3])

f = open("QA2.txt", "w+", -1, "utf-8")

v = list(net.degree_centrality(g).values())
k = list(net.degree_centrality(g).keys())
nodo = k[v.index(max(v))]
nodoMenosConexoes = k[v.index(min(v))]

f.write('O Nodo com mais conexões é: \n')
f.write('---> ' + nodo + '\n\n')

f.write('O Nodo com menos conexões é: \n')
f.write('---> ' + nodoMenosConexoes + "\n\n")

# medidas de centralidade

# centralidade do grau (o número de conecções de um grau para todos os outros)
f.write('Os 10 nodos mais centrais são: \n')
degree_centrality = sorted(net.degree_centrality(g).items(), key=lambda kv: (kv[1], kv[0]))
degree_centrality.reverse()

for i in degree_centrality[:10]:
    f.write('---> ' + str(i) + '\n')

# eigenvector centrality (o quão importante é um nodo em função de quão bem conectado está)
f.write('\nOs 10 nodos mais importante em função de quão bem conectado está: \n')
eigenvector_centrality = sorted(net.eigenvector_centrality(g).items(), key=lambda kv: (kv[1], kv[0]))
eigenvector_centrality.reverse()

for i in eigenvector_centrality[:10]:
    f.write('---> ' + str(i) + '\n')

# closeness centralidade (importância de um nodo em função da sua proximidade com os outros da rede)
f.write('\nOs 10 nodos mais importantes em função da sua proximidade com os outros da rede: \n')
closeness_centrality = sorted(net.closeness_centrality(g).items(), key=lambda kv: (kv[1], kv[0]))
closeness_centrality.reverse()

for i in closeness_centrality[:10]:
    f.write('---> ' + str(i) + '\n')

# betweeness centralidade (quantifica quantas vezes um nodo aparece nos caminhos mais curtos entre dois nodos)
f.write('\nOs 10 nodos que mais aparecem nos caminhos mais curtos entre dois nodos: \n')
betweenness_centrality = sorted(net.betweenness_centrality(g).items(), key=lambda kv: (kv[1], kv[0]))
betweenness_centrality.reverse()

for i in betweenness_centrality[:10]:
    f.write('---> ' + str(i) + '\n')

# medidas de distâncias

# grau -> marta trabalhou com 3 colegas
f.write('\nQuantas conexões o nodo mais central tem: \n')
f.write('---> ' + str(net.degree(g, nodo)) + '\n\n')

# distância (menor distância e o seu comprimento)
f.write('Qual a distância do nodo mais central ao menos central : \n')
f.write('---> ' + str(net.shortest_path_length(g, nodo, nodoMenosConexoes)) + '\n\n')

f.write('Por quais nodos temos que passar do mais central até ao menos central : \n')
f.write('---> ' + str(net.shortest_path(g, nodo, nodoMenosConexoes)) + '\n\n')

f.close()

# bfs (a distância de um nodo para todos os outros)
S = net.bfs_tree(g, nodo)
net.draw_networkx(S)
plt.axis('on')
plt.show()
