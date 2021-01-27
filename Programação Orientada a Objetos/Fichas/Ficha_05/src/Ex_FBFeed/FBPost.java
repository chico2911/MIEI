package Ex_FBFeed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FBPost {
    private int id;
    private String user;
    private LocalDateTime data;
    private String post;
    private int likes;
    private List<String> coments;

    //Construtores
    public FBPost() {
        this.id = 0;
        this.user = "n/a";
        this.data = LocalDateTime.now();
        this.post = "n/a";
        this.likes = 0;
        this.coments = new ArrayList<>();
    }
    public FBPost(FBPost fbPost) {
        this.id = fbPost.getId();
        this.user = fbPost.getUser();
        this.data = fbPost.getData();
        this.post = fbPost.getPost();
        this.likes = fbPost.getLikes();
        setComents(fbPost.getComents());
    }
    public FBPost(int id, String user, LocalDateTime data, String post, int likes, List<String> coments) {
        this.id = id;
        this.user = user;
        this.data = data;
        this.post = post;
        this.likes = likes;
        setComents(coments);
    }

    //Metodos Usuais Getters
    public int getId() {
        return id;
    }
    public String getUser() {
        return user;
    }
    public LocalDateTime getData() {
        return data;
    }
    public String getPost() {
        return post;
    }
    public int getLikes() {
        return likes;
    }
    public List<String> getComents(){
        List<String> aux = new ArrayList<>();
        for(String coment : this.coments){
            aux.add(coment);
        }
        return aux;
    }

    //Metodos Usuais Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setComents(List<String> coments) {
        this.coments = new ArrayList<>();
        for(String coment : coments){
            this.coments.add(coment);
        }
    }

    //toString
    public String toString() {
        return "FBFeed.FBPost{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", data=" + data +
                ", post='" + post + '\'' +
                ", likes=" + likes +
                ", coments=" + coments.toString() +
                '}';
    }

    public FBPost clone()
    { return new FBPost(this); }
}
