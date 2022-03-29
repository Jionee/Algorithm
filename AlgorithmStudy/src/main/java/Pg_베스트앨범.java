import java.util.*;

public class Pg_베스트앨범 {
    static String[] genres = {"classic", "pop", "classic", "classic", "pop"};
    static int[] plays = {500, 600, 150, 800, 2500};
    static HashMap<String,ArrayList<Music>> Map = new HashMap<>();
    static ArrayList<Rank> Arr = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        int N = genres.length;
        ArrayList<Integer> answer = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(Map.containsKey(genres[i])){
                Map.get(genres[i]).add(new Music(i,plays[i]));
            }
           else{
               Map.put(genres[i],new ArrayList<>());
               Map.get(genres[i]).add(new Music(i,plays[i]));
            }
        }
        System.out.println(Map);

        for(String music : Map.keySet()){
            int total = 0;
            for(Music m: Map.get(music)){
                total += m.playNum;
            }
            Arr.add(new Rank(music, total));
        }
        Collections.sort(Arr);
        System.out.println(Arr);

        for(Rank rank:Arr){
            String genre = rank.genre;
            ArrayList<Music> musics = Map.get(genre);
            Collections.sort(musics);
            System.out.println(musics);
            answer.add(musics.get(0).id);
            if(musics.size()>1){
                answer.add(musics.get(1).id);
            }
        }
        int[] Answer = new int[answer.size()];
        for(int i=0;i<answer.size();i++){
            Answer[i] = answer.get(i);
        }
        //System.out.println(answer);
    }

    static class Music implements Comparable<Music>{
        int id;
        int playNum;

        public Music(int id, int playNum) {
            this.id = id;
            this.playNum = playNum;
        }

        @Override
        public String toString() {
            return "Music{" +
                    "id=" + id +
                    ", playNum=" + playNum +
                    '}';
        }

        @Override
        public int compareTo(Music o) {
            if(o.playNum>this.playNum){
                return 1;
            }
            else if(o.playNum<this.playNum){
                return -1;
            }
            else{
                return Integer.compare(this.id,o.id);
            }
        }
    }

    static class Rank implements Comparable<Rank>{
        String genre;
        int totalPlay;

        @Override
        public String toString() {
            return "Rank{" +
                    "genre='" + genre + '\'' +
                    ", totalPlay=" + totalPlay +
                    '}';
        }

        public Rank(String genre, int totalPlay) {
            this.genre = genre;
            this.totalPlay = totalPlay;
        }

        @Override
        public int compareTo(Rank o) {
            return Integer.compare(o.totalPlay,this.totalPlay);
        }
    }
}
