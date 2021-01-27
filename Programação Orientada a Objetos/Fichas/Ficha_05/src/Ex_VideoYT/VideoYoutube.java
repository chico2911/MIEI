package Ex_VideoYT;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
/*Construtor,getters, setters e overrides feitos pelo Intellij para
acelerar o processo de construção da classe, logo do exercicio.
WARNING: Pode ter erros.
 */
public class VideoYoutube {
    private String nome;           private LocalDate data;
    private Byte[] conteudo;       private String resolucao;
    private List<String> coments;  private String cod;
    private int like;              private int dislike;

    public VideoYoutube(){
        coments  = new ArrayList<>();
        nome     = new String();
        conteudo = null;
        data     =LocalDate.now();
        resolucao=new String();
        like=0; dislike = 0; cod = new String();
    }
    public VideoYoutube(String nome, LocalDate data, Byte[] conteudo, String resolucao, List<String> coments, int like, int dislike,String cod) {
        this.nome = nome;
        this.data = data;
        this.conteudo = conteudo;
        this.resolucao = resolucao;
        this.coments = coments;
        this.like = like;
        this.dislike = dislike;
        this.cod=cod;
    }
    public VideoYoutube(VideoYoutube video){
        nome=video.getNome(); data=video.getData();
        conteudo=video.getConteudo(); resolucao=video.getResolucao();
        coments=video.getComents(); like=video.getLike(); dislike=video.getDislike();
        cod=video.getCod();
    }
    public String getNome() {
        return nome;
    }
    public String getCod(){return cod;}
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Byte[] getConteudo() {
        return conteudo;
    }
    public void setConteudo(Byte[] conteudo) {
        this.conteudo = conteudo;
    }
    public String getResolucao() {
        return resolucao;
    }
    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
    public List<String> getComents() {
        return coments;
    }
    public void setComents(List<String> coments) {
        this.coments = coments;
    }
    public int getLike() {
        return like;
    }
    public void setLike(int like) {
        this.like = like;
    }
    public int getDislike() {
        return dislike;
    }
    public void setDislike(int dislike) {
        this.dislike = dislike;
    }


    //Alineas:
    public void insereComentario(String comentario){coments.add(comentario);}
    public long qtsDiasDepois(){return ChronoUnit.DAYS.between(data, LocalDate.now());}
    public void thumbsUp(){setLike(getLike()+1);}
    public String processa(){
        return conteudo.toString();
    }

    public VideoYoutube clone(){return new VideoYoutube(this);
    }
}
