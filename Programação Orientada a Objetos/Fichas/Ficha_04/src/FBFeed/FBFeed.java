package FBFeed;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FBFeed {
    List<FBPost> fbPostList;

    //construtores
    public FBFeed() {
        this.fbPostList = new ArrayList<>();
    }
    public FBFeed(FBFeed fbFeed){
        setFbPostList(fbFeed.getFbPostList());
    }
    public FBFeed(List<FBPost> fbPosts){
        setFbPostList(fbPosts);
    }

    //Getter e Setter
    public List<FBPost> getFbPostList() {
        List<FBPost> aux = new ArrayList<>();
        for(FBPost fbPost : this.fbPostList){
            aux.add(fbPost.clone());
        }
        return aux;
    }
    public void setFbPostList(List<FBPost> fbPosts){
        this.fbPostList = new ArrayList<>();
        for(FBPost fbPost : fbPosts) {
            this.fbPostList.add(fbPost.clone());
        }
    }

    //I
    public int nrPosts(String user){
        return (int) this.fbPostList.stream().filter(fbPost -> fbPost.getUser().equals(user)).count();
    }

    //II
    public List<FBPost> postsOf(String user){
        return this.fbPostList.stream().filter(fbPost -> fbPost.getUser().equals(user)).collect(Collectors.toList());
    }

    //III
    public List<FBPost> postsOf(String user, LocalDateTime inicio, LocalDateTime fim){
        return this.fbPostList.stream().filter(fbPost -> fbPost.getUser().equals(user)
                                        && fbPost.getData().compareTo(inicio)>0 && fbPost.getData().compareTo(fim)<0 )
                                        .collect(Collectors.toList());
    }

    //IV
    public FBPost getPost(int id){
        FBPost post = new FBPost();
        for(FBPost f: this.fbPostList){
            if(f.getId() == id){
                post = f;
            }
        }
        return post;
    }

    //V
    public void comment(FBPost post, String comentario){
        List<String> coments = post.getComents();
        coments.add(comentario);
        getPost(post.getId()).setComents(coments);

    }

    //VI
    public void comment(int postid, String comentario){
        List<String> coments = getPost(postid).getComents();
        coments.add(comentario);
        getPost(postid).setComents(coments);
    }

    //VII
    public void like(FBPost post){
        getPost(post.getId()).setLikes(post.getLikes() + 1);
    }

    //VIII
    public void like(int postid){
        getPost(postid).setLikes(getPost(postid).getLikes()+1);
    }

    //IX
    public List<Integer> top5CommentsI(){
        return this.fbPostList.stream().limit(5).map(FBPost::getId).sorted(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                int size1 = getPost(o1).getComents().size(); int size2 = getPost(o2).getComents().size();
                if (size1==size2)return 0;
                if(size1<size2) return 1;
                return -1;
            }
        }).collect(Collectors.toList());

    }

    }
