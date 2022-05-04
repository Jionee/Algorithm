import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int arr[] = {7,6,5,8,3,5,9,1,6};
        heapSort(arr,arr.length);
    }

    static void heapSort(int[] arr,int size) {
        if(size<2){ //원소가 0,1개인 경우는 정렬할 필요 없음
            return;
        }
        //heap index가 0부터 시작
        int parentIdx = getParent(size-1);

        //max heap구성
        for(int i=parentIdx;i>=0;i--){
            heapify(arr,i,size-1);
        }

        //root를 가장 마지막으로 보내고 heapify 수행
        for(int i=size-1;i>=1;i--){
            swap(arr,0,i);
            heapify(arr,0,i-1);
        }

        System.out.println(Arrays.toString(arr));
    }

    static void heapify(int[] arr, int parentIdx, int lastIdx){
        int leftChildIdx = parentIdx * 2 + 1;
        int rightChildIdx = parentIdx * 2 + 2;
        int largestIdx = parentIdx;

        if(leftChildIdx <= lastIdx && arr[leftChildIdx]>arr[largestIdx]){
            largestIdx = leftChildIdx;
        }
        if(rightChildIdx <= lastIdx && arr[rightChildIdx]>arr[largestIdx]){
            largestIdx = rightChildIdx;
        }

        if(parentIdx!=largestIdx){
            swap(arr,parentIdx,largestIdx);
            heapify(arr,largestIdx,lastIdx); //재귀적으로 다시 힙 구성
        }
    }

    static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static int getParent(int child){
        return (child-1)/2;
    }
}
