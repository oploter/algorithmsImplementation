package my.app.andrey.week1;
import java.util.concurrent.ThreadLocalRandom;

public class ShellSort{
    public static void swap(Comparable[] arr, int i, int j){
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void sort(Comparable[] arr){
        int h = 1;
        while(h < arr.length/3){
            h = 3 * h + 1;
        }
        while(h >= 1){
            for(int i = h; i < arr.length; ++i){
                int j = i;
                while(j >= h && arr[j].compareTo(arr[j - h]) < 0){
                    swap(arr, j, j - h);
                    j -= h;
                }
            }
            h /= 3;
        }
    }

    public static boolean isSorted(Comparable[] arr){
        int j = 0;
        for(; j < arr.length - 1; ++j){
            if(arr[j].compareTo(arr[j + 1]) > 0){
                return false;
            }
        }
        return true;
    }

    public static void shuffle(Object[] arr){
        
    }

    public static void main(String[] args){

    }
}