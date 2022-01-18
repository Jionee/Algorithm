import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {
    public static void main(String[] args) {
        Item item1 = new Item(1, 3, 1);
        Item item2 = new Item(2, 2, 3);
        Item item3 = new Item(3, 1, 2);

        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new Comparator<Item>(){ //클래스의 Comparable은 무시됨
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.c,o2.c);
            }
        });

        Collections.sort(list,Comparator.comparingInt(Item::getB));
    }
}

class Item implements Comparable<Item>{
    int a;
    int b;
    int c;

    public Item(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "{a = " + a +", b = " +b+",c = "+c + "}";
    }

    //음수 : -
    //0 : -
    //양수 : SWAP
    @Override
    public int compareTo(Item o) {
        int comp1 = Integer.compare(o.a,a);
        if(comp1 == 0){
            return Integer.compare(o.b,b);
        }
        else
            return comp1;

        //return Integer.compare(a,o.a); //오름차순(자기,파라미터)
        //return Integer.compare(o.a,a); //내림차순(파라미터,자기)

//        if(a < o.a)
//            return -1;
//        else if(a == o.a)
//            return 0;
//        else
//            return 1;
    }
    public int getA(){
        return a;
    }public int getB(){
        return b;
    }public int getC(){
        return c;
    }
}

