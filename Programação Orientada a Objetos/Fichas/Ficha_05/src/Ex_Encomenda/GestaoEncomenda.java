package Ex_Encomenda;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestaoEncomenda {
    Map<Integer, EncEficiente> encomenda;

    public GestaoEncomenda(){
        encomenda = new HashMap<>();
    }
    public GestaoEncomenda(Map<Integer, EncEficiente> encomenda){
        setEncomenda(encomenda);
    }

    public GestaoEncomenda(GestaoEncomenda ge){setEncomenda(ge.getEncomenda());}

    public void setEncomenda(Map<Integer, EncEficiente> encefic){
        encomenda=new HashMap<>();
        encefic.entrySet().forEach(e->encomenda.put(e.getKey(),e.getValue().clone()));
    }
    public Map<Integer, EncEficiente> getEncomenda(){
        Map<Integer, EncEficiente> encefi = new HashMap<>();
        this.encomenda.forEach((key,value)->encefi.put(key,value.clone()));
        return encefi;
    }

    //i.
    public Set<Integer> todosCodigosEnc(){
        return new HashSet<>(this.encomenda.keySet());
    }
    //ii.
    public void addEncomenda(EncEficiente enc){
        this.encomenda.put(enc.getNoEnc(),enc.clone());
    }
    //iii.
    public EncEficiente getEncomenda(Integer codEnc){
        return encomenda.get(codEnc);
    }
    //iv.
    public void removeEncomenda(Integer codEnc){
        encomenda.remove(codEnc);
    }
    //v.
    public Integer encomendaComMaisProdutos(){
        int maior = 0; int max=0;
        for(EncEficiente enc : this.encomenda.values()){
            if(enc.getLinhas().size()>max){ maior=enc.getNoEnc(); max=enc.getLinhas().size();}
        }
        return maior;
    }
    //vi.
    public Set<Integer> encomendasComProduto(String codirod){
        return encomenda.values().stream()
                                 .filter(e->e.existeProdutoEncomenda(codirod))
                                 .map(EncEficiente::getNoEnc).collect(Collectors.toSet());
    }
    //vii.
    public Set<Integer> encomendasAposData(LocalDate d){
        return encomenda.values().stream()
                .filter(e->e.getData().isAfter(d))
                .map(EncEficiente::getNoEnc).collect(Collectors.toSet());
    }
    //viii.
    public Set<EncEficiente> encomendasValorDecrescente(){
        Set<EncEficiente> ordena = new TreeSet<>((encomenda1, encomenda2)->{
            if(encomenda1.calculaValorTotal()<encomenda2.calculaValorTotal()) return -1;
            if(encomenda1.calculaValorTotal()>encomenda2.calculaValorTotal()) return  1;
            return 0;
        });
        encomenda.values().forEach(e->ordena.add(e.clone()));
        return ordena;
    }
    //ix.
    public Map<String,List<Integer>> encomendasDeProduto(){
        Map<String,List<Integer>> encomendaProd = new HashMap<>();
        for(EncEficiente enc : encomenda.values()){
            List<String> lprods = enc.getLinhas().stream().map(LinhaEncomenda::getDescricao).collect(Collectors.toList());
            for(String linha : lprods){
                if(!encomendaProd.containsKey(linha)) encomendaProd.put(linha,new ArrayList<Integer>());
                encomendaProd.get(linha).add(enc.getNoEnc());
            }
        }
        return encomendaProd;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (EncEficiente e: this.encomenda.values())
            sb.append(e.toString() + "\n");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        GestaoEncomenda ge = (GestaoEncomenda) o;
        return this.encomenda.equals(ge.getEncomenda());
    }

    public GestaoEncomenda clone() {
        return new GestaoEncomenda(this);
    }



}
