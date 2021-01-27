package Ex_VideoYT;

import Ex_Encomenda.EncEficiente;
import Ex_Turma.Aluno;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SistemaVideos {
    Map<String,VideoYoutube> videos;

    public SistemaVideos(){videos=new HashMap<>();}
    public SistemaVideos(Map<String,VideoYoutube> youtube){setVideos(youtube);}
    public SistemaVideos(SistemaVideos youtube){setVideos(youtube.getVideos());}

    public void setVideos(Map<String, VideoYoutube> youtube) {
        youtube.forEach((key,value)->videos.put(key,value.clone()));
    }
    public Map<String, VideoYoutube> getVideos(){
        Map<String, VideoYoutube> youtube = new HashMap<>();
        this.videos.forEach((key, value) -> youtube.put(key, value.clone()));
        return youtube;
    }

    public void addVideo(VideoYoutube v){
        videos.put(v.getCod(),v.clone());
    }
    public VideoYoutube getVideo(String codVideo){return videos.get(codVideo);}
    public void removeVideo(String codVideo){videos.remove(codVideo);}
    public void addLike(String codVideo){
        int nrLikes = videos.get(codVideo).getLike();
        videos.get(codVideo).setLike(nrLikes+1);}
    public String topLikes(){
        String cod = ""; int max=0;
        for(VideoYoutube video : this.videos.values()){
            if(video.getLike()>max){ cod=video.getCod(); max=video.getLike();}
        }
        return cod;
    }

    public boolean antesEdepois(VideoYoutube video,LocalDate dinicial, LocalDate dfinal){
        return video.getData().isAfter(dinicial) && video.getData().isBefore(dfinal);
    }
    public String topLikes(LocalDate dinicial, LocalDate dfinal){
        String cod = ""; int max=0;
        for(VideoYoutube video : this.videos.values()){
            if(video.getLike()>max && antesEdepois(video,dinicial,dfinal)){ cod=video.getCod(); max=video.getLike();}
        }
        return cod;
    }
     /*public Video videoMaisLongo() --> não defini a duração na classe VideoYoutube. Desta forma não
     é possivel fazer a alinea, mas seria algo como a topLikes
      */
}
