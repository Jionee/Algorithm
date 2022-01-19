import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Boj1202 {
    static int N,K;
    static int[]bag;
    static ArrayList<Bosuk> bosuk = new ArrayList<>();
    static long result;
    static PriorityQueue<Bosuk> bosuks = new PriorityQueue<>(new Comparator<Bosuk>() {
        @Override
        public int compare(Bosuk o1, Bosuk o2) {
            return Integer.compare(o2.price,o1.price);
        }
    });
    static PriorityQueue<Bosuk> bosuks2 = new PriorityQueue<>(Comparator.comparingInt(Bosuk::getPrice)); //다른 방법

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/main/java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K];

        for(int n = 0;n<N;n++){
            st = new StringTokenizer(br.readLine());
            bosuk.add(new Bosuk(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        Collections.sort(bosuk); //오름차순 정렬

        for(int k = 0;k<K;k++){
            bag[k] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag); //오름차순 정렬

        //가방만큼 돌건데 작은 가방부터 가능한 보석이 있는지 보기
        int index = 0;
        for(int k=0;k<K;k++){
            //보석 무게가 되는지 살펴보기
            while(index<bosuk.size()){
                if(bag[k] >= bosuk.get(index).weight){
                    bosuks.add(bosuk.get(index));
                    index++;
                }
                else{
                    break;
                }
            }

            if(bosuks.size()>0){
                Bosuk removeBosuk = bosuks.poll();
                result += removeBosuk.price;
            }
        }
        System.out.println(result);
    }
}

class Bosuk implements Comparable<Bosuk>{
    int weight;
    int price;

    public Bosuk(int weight,int price){
        this.weight = weight;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Bosuk o) {
        int comp1 = Integer.compare(weight,o.weight); //오름차순
        return comp1;
    }

    @Override
    public String toString() {
        return "Bosuk{" +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }
}
