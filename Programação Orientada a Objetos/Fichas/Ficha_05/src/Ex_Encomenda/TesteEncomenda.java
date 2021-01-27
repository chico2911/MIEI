package Ex_Encomenda;

import java.time.LocalDate;
import java.util.ArrayList;

public class TesteEncomenda {

    public static void main(String[] opts){
        LinhaEncomenda le = new LinhaEncomenda("6593431", "Telemóvel",
                13, 70, 0.23, 0.20);
        LinhaEncomenda le1 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 60, 0.23, 0.20);
        LinhaEncomenda le2 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 30, 0.23, 0.20);
        LinhaEncomenda le4 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 50, 0.23, 0.20);
        LinhaEncomenda le5 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 70, 0.23, 0.20);
        LinhaEncomenda le6 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 1000, 0.23, 0.20);
        LinhaEncomenda le7 = new LinhaEncomenda("6593431", "Telemóvel",
                13, 350, 0.23, 0.20);

        ArrayList<LinhaEncomenda> linha  = new ArrayList<>();
        ArrayList<LinhaEncomenda> linha2 = new ArrayList<>();
        ArrayList<LinhaEncomenda> linha3 = new ArrayList<>();
        linha.add(le); linha.add(le2);linha.add(le1);
        linha2.add(le); linha2.add(le2);linha2.add(le1);linha2.add(le6);linha2.add(le7);linha2.add(le5);
        linha3.add(le7); linha3.add(le4);linha3.add(le);
        EncEficiente enc1 = new EncEficiente("Ze",2655685,"Rua da Uni",1, LocalDate.now(),linha);
        EncEficiente enc2 = new EncEficiente("Luis",2655685,"Rua da Uni",2, LocalDate.now(),linha2);
        EncEficiente enc3 = new EncEficiente("Leonor",2655685,"Rua da Uni",3, LocalDate.now(),linha3);

        GestaoEncomenda t = new GestaoEncomenda();
        t.addEncomenda(enc1);
        t.addEncomenda(enc2);
        t.addEncomenda(enc3);

        System.out.println("Encomendas: "+t.toString());

        System.out.println("Ordem coleçao Valor Decrescente:");

        System.out.println(t.encomendasValorDecrescente());

        System.out.println("Encomenda com Maior Produtos:");

        System.out.println(t.encomendaComMaisProdutos());

        System.out.println("Encomenda após 29-11-1999: "+t.encomendasAposData(LocalDate.ofYearDay(1999,29-11)));

        System.out.println("método que calcula um map em que associa cada código de\n" +
                "produto à lista das encomendas em que foi referida: "+t.encomendasDeProduto());



    }
}
