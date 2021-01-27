import java.util.*;

public class Grafo {
    // variáveis de instância
    private Map < Integer , Set < Integer > > adj ;
                            // " lista " de adjacência
    //i.
    public Grafo(){
        adj = new HashMap<>();
    }
    public Grafo(Map<Integer,Set<Integer>> adjacentes){
        setAdj(adjacentes);
    }

    public void setAdj(Map<Integer,Set<Integer>> adjacentes){
        adj = new HashMap<>();
        adjacentes.forEach((key, value) -> adj.put(key, value));
    }

    //ii.
    public void addArco(Integer vOrig, Integer vDest){
        this.adj.putIfAbsent(vOrig,new HashSet<>());
        this.adj.putIfAbsent(vDest,new HashSet<>());
        adj.get(vOrig).add(vDest);
    }

    //iii.
    public boolean isSink(Integer v){
        return adj.get(v).isEmpty();
    }

    //iv.
    public boolean isSource(Integer v){
        boolean bool = true;
        if(adj.values().stream().anyMatch(e->e.contains(v))) bool = false;
        return bool;
    }

    //v.
    public int size(){
        return adj.size()+adj.values().stream().mapToInt(Set::size).sum();
    }

    //vi.

 }