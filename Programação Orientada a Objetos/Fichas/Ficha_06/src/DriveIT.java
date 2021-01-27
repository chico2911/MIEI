import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DriveIT implements Serializable {
    private Map<String,Veiculo> veiculos;
    private Map<String,Comparator<Veiculo>> comparatorMap;

    public DriveIT(){
        this.veiculos=new HashMap<>(); comparatorMap = new TreeMap<>();
    }
    public DriveIT(Map<String,Veiculo> velos,Map<String,Comparator<Veiculo>> comparatorMap){
        setVeiculos(velos);
        setComparatorMap(comparatorMap);
    }
    public DriveIT(DriveIT drive){
        setVeiculos(drive.getVeiculosMap());
        setComparatorMap(drive.getComparatorMap());
    }

    public Map<String, Veiculo> getVeiculosMap() {
        Map<String,Veiculo> newVeiculos = new HashMap<>();
        veiculos.forEach((key, value) -> newVeiculos.put(key, value.clone()));
        return newVeiculos;
    }
    public void setVeiculos(Map<String, Veiculo> newVeiculos) {
        veiculos = new HashMap<>();
        newVeiculos.forEach((key, value) -> veiculos.put(key, value.clone()));
    }

    public Map<String,Comparator<Veiculo>> getComparatorMap(){
        Map<String,Comparator<Veiculo>> newComparator = new TreeMap<>();
        comparatorMap.forEach(newComparator::put);
        return newComparator;
    }
    public void setComparatorMap(Map<String, Comparator<Veiculo>> OLDcomparatorMap) {
        comparatorMap=new TreeMap<>();
        OLDcomparatorMap.forEach(comparatorMap::put);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Matricula;Marca;Modelo;Ano;Velocidade Media;Preco Base;Classificacao;NoKms;Promocao;Ocupacao;Pontos;Taxa\n");
        for(Veiculo v : veiculos.values()){
            str.append(v).append("\n");
        }
        return str.toString();
    }

    //Fase I
    public boolean existeVeiculo(String cod) throws VeiculoNãoExiste {
        if (veiculos.containsKey(cod)) return true;
        else throw new VeiculoNãoExiste("Veiculo nao existe");
    }

    public int quantos(){return veiculos.size();}

    public int quantos(String marca){
        return (int) veiculos.values().stream().filter(e->marca.equals(e.getMarca())).count();
    }

    public int quantosT(String tipo){
        int value=0;
        switch(tipo){
            case "Ocasiao": value =(int) veiculos.values().stream().filter(e-> e instanceof VeiculoOcasiao).count();
                            break;
            case "Premium": value = (int) veiculos.values().stream().filter(e-> e instanceof VeiculoPremium).count();
                            break;
            case "Normal": value = quantos() - quantosT("Ocasiao") - quantosT("Premium");
                            break;
        }
        return value;
    }

    public Veiculo getVeiculo(String cod){
        Veiculo V = veiculos.get(cod);
        return V.clone();
    }

    public void adiciona(Veiculo v) throws VeiculoExistente {
        if(veiculos.values().stream().noneMatch(e->e.equals(v))) veiculos.put(v.getMatricula(),v.clone());
        else throw new VeiculoExistente("Veiculo " + v.getMatricula() +" ja existe");
    }

    public List<Veiculo> getVeiculos(){
        List<Veiculo> veiculoList = new ArrayList<>();
        veiculos.forEach((key,values)->veiculoList.add(values.clone()));
        return veiculoList;
    }

    public void adiciona(Set<Veiculo> vs){
        vs.forEach(e-> {
            try {
                adiciona(e);
            } catch (VeiculoExistente veiculoExistente) {
                veiculoExistente.printStackTrace();
            }
        });
    }

    public void registarAluguer(String codVeiculo, int numKms) throws ValoresNegativos, VeiculoNãoExiste {
        if(numKms>0){
        Veiculo carro = veiculos.get(codVeiculo).clone();
        if(carro!=null){
        int i = carro.getNumKms() + numKms;
        carro.setNumKms(i);
        veiculos.replace(codVeiculo,carro);}
        else throw new VeiculoNãoExiste();}
        else throw new ValoresNegativos(numKms);
    }
    public void classificarVeiculo(String cod, int classificacao) throws VeiculoNãoExiste, ValoresNegativos {
        if(classificacao>0){
        Veiculo carro = veiculos.get(cod).clone();
        if(carro!=null){
        double i = (carro.getClassificacao()+(double) classificacao)/2;
        carro.setClassificacao(i);
        veiculos.replace(cod,carro);}
        else throw new VeiculoNãoExiste();}
        else throw new ValoresNegativos(classificacao);
    }

    public double custoRealKm(String cod) throws VeiculoNãoExiste {
        double custo=0;
        Veiculo carro = veiculos.get(cod).clone();
        if(carro!=null){custo=1.10*carro.custoRealKm();}
        else throw new VeiculoNãoExiste();
        return  custo;
    }

    public List<Veiculo> veiculosOrdenadosCusto(){
        List<Veiculo> newList;
        newList = veiculos.values().stream().sorted(new Comparator<Veiculo>() {
            @Override
            public int compare(Veiculo o1, Veiculo o2) {
                if(o1.custoRealKm()>o2.custoRealKm()) return -1;
                if(o1.custoRealKm()<o2.custoRealKm()) return 1;
                return 0;
            }
        }).map(Veiculo::clone).collect(Collectors.toList());
        return newList;
    }

    public Veiculo veiculoMaisBarato(){
        return this.veiculos.values().stream()
                .reduce((v1,v2) -> v1.custoRealKm() < v2.custoRealKm() ? v1 : v2)
                .get().clone();
    }

    public Veiculo veiculoMenosUtilizado(){
        return veiculos.values().stream().min((o1, o2) -> o1.getNumKms()-o2.getNumKms()).get().clone();

    }

    public void alteraPromocao(boolean b){
        for(Veiculo v : this.veiculos.values()){
            if(v instanceof VeiculoOcasiao){
                VeiculoOcasiao vo = (VeiculoOcasiao) v;
                vo.setPromocao(b);
            }
        }
    }

    public double avgCostKmBus(){
        double avg=0; int size=0;
        for(Veiculo v : veiculos.values()){
            if(v instanceof AutocarroInteligente){
                AutocarroInteligente bus = (AutocarroInteligente) v.clone();
                bus.setOcupacao(0.85); size++;
                avg+=bus.custoRealKm();
            }
        }
        return avg/size;

    }
//Fase 2
    public Set<Veiculo> ordenarVeiculos(){
        return veiculos.values().stream().sorted().map(Veiculo::clone).collect(Collectors.toSet());
    }

    public List<Veiculo> ordenarVeiculosLista(){
        return veiculos.values().stream().sorted().map(Veiculo::clone).collect(Collectors.toList());
    }

    public Set<Veiculo> ordenarVeiculos(Comparator<Veiculo> c){
        return veiculos.values().stream().sorted(c).map(Veiculo::clone).collect(Collectors.toSet());
    }

    public void addComparator(String key,Comparator<Veiculo> comparator){comparatorMap.put(key, comparator);}

    public Iterator<Veiculo> ordenarVeiculo(String criterio){
        Set<Veiculo> aux = new TreeSet<>(comparatorMap.get(criterio));
        veiculos.forEach((k,v)->aux.add(v.clone()));
        return aux.iterator();
    }

//Fase 3
        public List<BonificaKms> daoPontos(){
            List<BonificaKms> bonificaKmsList = new ArrayList<>();
            for(Veiculo v : this.veiculos.values()){
                if(v instanceof VeiculoPremium || v instanceof AutocarroInteligente){
                    BonificaKms bk = (BonificaKms) v.clone();
                    bonificaKmsList.add(bk);
                }
            }
            return bonificaKmsList;
    }

    //Fase 4
    public void gravaCSV(String filename)throws IOException {
        PrintWriter pw = new PrintWriter(filename);
        pw.print(this);
        pw.flush();
        pw.close();
    }

    //2.
    public void gravaObj(String filename) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(this);
        out.flush();
        out.close();
    }

    public DriveIT lerObj(String filename) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        DriveIT it = (DriveIT) in.readObject();
    //    setComparatorMap(it.getComparatorMap());
       // setVeiculos(it.getVeiculosMap());
        in.close();
        return it;
    }




}
