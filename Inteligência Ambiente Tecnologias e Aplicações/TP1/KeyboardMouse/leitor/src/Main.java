
import java.io.*;
import java.util.*;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Main {

    Map<String, List<String>> keypressed = new HashMap<>();
    Map<String, List<String>> keyreleased = new HashMap<>();
    Map<String, List<String>> mouseclick = new HashMap<>();
    Map<String, List<String>> mousemove = new HashMap<>();
    Map<String, List<String>> mousescrol = new HashMap<>();


    public static void main(String args[]) throws IOException {

        Map<String, List<String>> keypressed = new HashMap<>();
        Map<String, List<String>> keyreleased = new HashMap<>();
        List<String> mouseclick = new ArrayList<>();
        List<String> mousemove = new ArrayList<>();
        List<String> mousescrol = new ArrayList<>();


        BufferedReader in = null;


        try {
            in = new BufferedReader(new FileReader("C:\\Users\\rvcar\\OneDrive\\Documentos\\GitHub\\IATA\\KeyboardMouse\\leitor\\src\\logger.txt"));


            String leitura = in.readLine();

            while (leitura != null) {
                String[] tokens = leitura.split("\\*\\#");
                if (tokens[0].equals("t")) {
                    if (tokens[1].equals("p")) {
                        if (keypressed.containsKey(tokens[2])) {
                            List<String> temp = keypressed.get(tokens[2]);
                            temp.add(tokens[3]);
                            keypressed.put(tokens[2], temp);
                        } else {
                            List<String> temp = new ArrayList<String>();
                            temp.add(tokens[3]);
                            keypressed.put(tokens[2], temp);
                        }
                    } else {
                        if (keyreleased.containsKey(tokens[2])) {
                            List<String> temp = keyreleased.get(tokens[2]);
                            temp.add(tokens[3]);
                            keyreleased.put(tokens[2], temp);
                        } else {
                            List<String> temp = new ArrayList<String>();
                            keyreleased.put(tokens[2], temp);
                        }
                    }
                } else {
                    if (tokens[1].equals("c")) {
                        String clic = tokens[2] + "#" + tokens[3] + "#" + tokens[4] + "#" + tokens[5];
                        mouseclick.add(clic);
                    } else {
                        if (tokens[1].equals("s")) {
                            String scroll = tokens[2] + "#" + tokens[3] + "#" + tokens[4];
                            mousescrol.add(scroll);
                        } else {
                            String move = tokens[2] + "#" + tokens[3] + "#" + tokens[4];
                            mousemove.add(move);
                        }
                    }
                }


                leitura = in.readLine();
            }
            System.out.println("Logger loaded");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        in.close();
        System.out.println(keypressed.size());
        Scanner keyboard = new Scanner(System.in);
        /*

Q1  --done

Q2  -- done

Q3 top 10 mais pressionadas (x) done

Q4 Top 10 left - PETER

Q5 top 10 right - PETER

Q6 letra mais tempo presionada - done

Q7 nr scrols

Q8 distancia perc

Q9 number of right and left cli


 */
        System.out.println("Escolha uma querie. pressiona 99 para sair");
        System.out.println("Pressione 1 para: Tecla mais vezes presionada ");
        System.out.println("Pressione 2 para: Tecla menos pressionada (das pressionadas) ");
        System.out.println("Pressione 3 para: Top 10 teclas mais pressionadas ");
        System.out.println("Pressione 4 para: Tecla mais tempo presionada ");
        System.out.println("Pressione 5 para: Número de Cliques ");
        System.out.println("Pressione 6 para: Número de Scrolls ");
        System.out.println("Pressione 7 para: Letras e/ou números pressionadas no grupo da mão esquerda");
        System.out.println("Pressione 8 para: Letras e/ou números pressionadas no grupo da mão direita ");
        System.out.println("Pressione 9 para: Distância percorrida pelo mouse ");
        System.out.println("Pressione 0 para: Reload do ficheiro logger ");

        int myint = keyboard.nextInt();
        while (myint != 99) {
            if (myint == 1)
                moreTimesPressed(keypressed);
            if (myint == 2)
                lessTimesPressed(keypressed);
            if (myint == 3)
                top10pressed(keypressed);
            if (myint == 4)
                moreTimePressed(keypressed, keyreleased);
            if (myint == 5)
                mousenumbers(mouseclick, "Cliques");
            if (myint == 6)
                mousenumbers(mousescrol, "Scrolls");
            if (myint == 7)
                numberPressedLeftHand(keypressed);
            if (myint == 8)
                numberPressedRightHand(keypressed);
            if (myint == 9)
                mousedistance(mousemove);
            if (myint == 0)
                main(null);
            System.out.println("Escolha uma querie. pressiona 99 para sair");
            System.out.println("Pressione 1 para: Tecla mais vezes presionada ");
            System.out.println("Pressione 2 para: Tecla menos pressionada (das pressionadas) ");
            System.out.println("Pressione 3 para: Top 10 teclas mais pressionadas ");
            System.out.println("Pressione 4 para: Tecla mais tempo presionada ");
            System.out.println("Pressione 5 para: Número de Cliques ");
            System.out.println("Pressione 6 para: Número de Scrolls ");
            System.out.println("Pressione 7 para: Letras e/ou números pressionadas no grupo da mão esquerda");
            System.out.println("Pressione 8 para: Letras e/ou números pressionadas no grupo da mão direita ");
            System.out.println("Pressione 9 para: Distância percorrida pelo mouse ");
            System.out.println("Pressione 0 para: Reload do ficheiro logger ");
            myint = keyboard.nextInt();
        }


    }

    public static void moreTimesPressed(Map<String, List<String>> keypressed) {
        Set<String> keys = keypressed.keySet();
        String morepressed = "Nenhuma Tecla pressionada";
        int vezes = 0;
        for (String key : keys) {
            if (keypressed.get(key).size() > vezes) {
                morepressed = key;
                vezes = keypressed.get(key).size();
            }
        }
        if (vezes > 0)
            System.out.println("Tecla mais pressionada:" + morepressed + "\n Pressionada " + vezes + " vezes");
        else
            System.out.println(morepressed);
    }

    public static void lessTimesPressed(Map<String, List<String>> keypressed) {
        Set<String> keys = keypressed.keySet();
        String lesspressed = "Nenhuma Tecla pressionada";
        boolean inicialized = false;
        int vezes = 0;
        for (String key : keys) {
            if (inicialized && keypressed.get(key).size() < vezes) {
                lesspressed = key;
                vezes = keypressed.get(key).size();
            } else {
                inicialized = true;
                lesspressed = key;
                vezes = keypressed.get(key).size();
            }
        }
        if (vezes > 0)
            System.out.println("Tecla menos pressionada:" + lesspressed + "\n Pressionada " + vezes + " vezes");
        else
            System.out.println(lesspressed);
    }


    public static void top10pressed(Map<String, List<String>> keypressed) {
        Set<String> keys = keypressed.keySet();
        List<String> teclas = new ArrayList<>();
        List<Integer> vezes = new ArrayList<>();
        for (String key : keys) {
            if (teclas.size() < 10) {
                teclas.add(key);
                vezes.add(keypressed.get(key).size());
            }
            for (int i = 0; i < teclas.size(); i++) {
                if (keypressed.get(key).size() > keypressed.get(teclas.get(i)).size()) {
                    String tempstring = teclas.get(i);
                    int tempnumb = vezes.get(i);
                    teclas.set(i, key);
                    vezes.set(i, keypressed.get(key).size());
                    key = tempstring;
                }
            }

        }
        System.out.println(vezes.size());
        for (int i = vezes.size() - 1; i >= 0; i--) {
            System.out.println("Posição " + (i + 1) + ": Tecla " + teclas.get(i) + "\n Pressionada " + vezes.get(i) + " vezes.");
        }
    }


    public static void moreTimePressed(Map<String, List<String>> keypressed, Map<String, List<String>> keyreleased) {
        Set<String> keys = keypressed.keySet();
        int toptime = -1;
        String topkey = "";
        boolean inicialized = false;
        int vezes = 0;
        for (String key : keys) {
            int timetemp = 0;
            List<String> pressed = keypressed.get(key);
            List<String> released = keypressed.get(key);
            for (String pressedstr : pressed) {
                int ptime = Integer.parseInt(pressedstr);
                for (String releasedstr : released) {
                    int rtime = Integer.parseInt(releasedstr);
                    if (rtime > ptime) {
                        timetemp += rtime - ptime;
                    }
                }
            }
            if (timetemp > toptime) {
                topkey = key;
                toptime = timetemp;
            }
        }
        if (toptime >= 0)
            System.out.println("Tecla mais tempo pressionada:" + topkey + "\n Pressionada " + toptime + " segundos");
        else
            System.out.println("Sem teclas pressionadas");
    }

    public static void mousenumbers(List<String> mousemap, String type) {
        System.out.println("Numero de " + type + " = " + mousemap.size());
    }

    public static void mousedistance(List<String> mousemap) {
        double distance = 0;
        int lastx = -99999;
        int lasty = -99999;
        for (String str : mousemap) {
            String[] tokens = str.split("\\#");
            if (lastx == -99999 && lasty == -99999) {
                lastx = Integer.parseInt(tokens[0]);
                lasty = Integer.parseInt(tokens[0]);
            } else {
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[0]);
                double dist = sqrt(pow(x - lastx, 2) + pow(y - lasty, 2));
                distance += dist;
                lastx = x;
                lasty = y;
            }

        }
        System.out.println("Distancia percorrida: " + distance);
    }

    public static void numberPressedLeftHand(Map<String, List<String>> keypressed) {
        String[] LH = {"q", "w", "e", "r", "t", "a", "s", "d", "f", "g", "z", "x", "c", "v", "b", "1", "2", "3", "4", "5"};
        Set<String> keys = keypressed.keySet();
        int vezes = 0;
        for(String key :keys) {
            for(int i = 0; i< LH.length; i++){
                if(key.equals(LH[i])){
                    vezes++;
                }
            }
       }
        System.out.println("Letras e/ou números pressionadas mão esquerda são:" + vezes + "vezes!");
    }

    public static void numberPressedRightHand(Map<String, List<String>> keypressed) {
        String[] RH = {"y", "u", "i", "o", "p", "h", "j", "k", "l", "ç", "n", "m", "6", "7", "8", "9", "0"};
        Set<String> keys = keypressed.keySet();
        int vezes = 0;
        for(String key :keys) {
            for(int i = 0; i< RH.length; i++){
                if(key.equals(RH[i])){
                    vezes++;
                }
            }
        }
        System.out.println("Letras e/ou números pressionadas mão direita são:" + vezes + "vezes!");
    }
}


/*

Q1 tecla mais vezes presionada --done

Q2 menos pressionada (das pressionadas) -- done

Q3 top 10 mais pressionadas (x) done

Q4 Numero de letras e/ou numeros pressionados no grupo Mao esquerda - PETER

Q5 Numero de letras e/ou numeros pressionados no grupo Mao direito - PETER

Q6 letra mais tempo presionada - done

Q7 nr scrols

Q8 distancia perc

Q9 number of right and left cli


 */