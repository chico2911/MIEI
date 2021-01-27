package Ex_FBFeed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FBFeedMap {
    Map<String, List<FBPost>> usersTimeline;

    public FBFeedMap(){usersTimeline=new HashMap<>();
    }
    public FBFeedMap(Map<String, List<FBPost>> timeLineToCpy){
        setUsersTimeline(timeLineToCpy);
    }
    public FBFeedMap(FBFeedMap fbFeed){setUsersTimeline(fbFeed.getUsersTimeline());}

    public void setUsersTimeline(Map<String, List<FBPost>> timeLineToCpy){
        timeLineToCpy.forEach((key,values)->usersTimeline.put(key, setList(values)));
    }
    public List<FBPost> setList(List<FBPost> values){
        List<FBPost> listFBpost = new ArrayList<>();
        values.forEach(e->listFBpost.add(e.clone()));
        return listFBpost;
    }
    public Map<String, List<FBPost>> getUsersTimeline(){
        Map<String, List<FBPost>> newTL = new HashMap<>();
        usersTimeline.forEach((key,value)->newTL.put(key,setList(value)));
        return newTL;
    }

    //i.
    public void addPost(String user, FBPost post){
        usersTimeline.get(user).add(post);
    }
    //ii.
    public void removePosts(String user, LocalDateTime di, LocalDateTime df){
        usersTimeline.get(user)
                .removeIf(post -> post.getData().isAfter(di) && post.getData().isBefore(df));
    }
    //iii.
    public int postsNumPeriodo(LocalDateTime di, LocalDateTime df){
       int conta=0;
       for(List<FBPost> post : usersTimeline.values()){
          conta+= post.stream().filter(e->e.getData().isAfter(di) && e.getData().isBefore(df)).count();
       }
       return conta;
    }
    //iv. Algo como isto deve funcionar.. com operador externo tinha 100% certeza xD
    public String utilizadorMaisActivo(LocalDateTime di, LocalDateTime df){
        int nrPost=0; String nome="";
        for(String usr : usersTimeline.keySet()){
            if(usersTimeline.get(usr).stream().filter(e->e.getData().isAfter(di) && e.getData().isBefore(df)).count()>0){
                nome=usr; nrPost=(int) usersTimeline.get(usr).stream().filter(e->e.getData().isAfter(di) && e.getData().isBefore(df)).count();
            }
        }
        return nome;
    }
    //v.
    public List<FBPost> timelineGlobal(){
        List<FBPost> postOrdenados = new ArrayList<>();
        usersTimeline.forEach((key, value) -> value.forEach(i -> postOrdenados.add(i.clone())));
        postOrdenados.sort(new Comparator<FBPost>() {
           @Override
           public int compare(FBPost o1, FBPost o2) {
               if(o1.getData().isBefore(o2.getData())) return -1;
               if(o1.getData().isAfter(o2.getData())) return 1;
               return 0;
           }
       });
        return postOrdenados;
    }

}
