package CasaInteligente;

public class Lampada
{
    //0 desligado, 1 ligado, 2 eco
    private int modo;

    private final double consumoNormal = 0.1;
    private final double consumoEco = 0.05;
    private long total;
    private long stamp;


    public Lampada() {
        this.modo = 0;
        this.total = 0;
        this.stamp = System.currentTimeMillis();
    }

    public Lampada (int modo , long total , long stamp)
    { this.modo = modo;
        this.total = total;
        this.stamp = stamp;
    }

    public Lampada(Lampada l)
    { this.modo = l.getModo();
        this.total = l.getTotal();
        this.stamp = l.getStamp();
    }

    public int getModo()
    { return this.modo; }

    public long getTotal()
    { return this.total; }

    public void setTotal(long total)
    { this.total = total; }


    public long getStamp()
    { return this.stamp; }


    public void lampON() {
        this.actualizaConsumo();
        this.modo = 1;
    }

    public void lampOFF() {
        this.actualizaConsumo();
        this.modo = 0;
    }

    public void lampECO() {
        this.modo = 2;
    }

    public double periodoConsumo() {
        return total;
    }


    private void actualizaConsumo() {
        long periodo = System.currentTimeMillis() - stamp;
        if(this.modo == 1) {
            this.total += consumoNormal*periodo;
        } else if(this.modo == 2) {
            this.total += consumoEco*periodo;
        }
        this.stamp = System.currentTimeMillis();
    }

    public Lampada clone()
    { return new Lampada(this); }
}
