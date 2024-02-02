import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;

public class Task2 {
    private static final class tokens extends LinkedList<String> {
		tokens(String line) { 
			super(new LinkedList<String>()); 
			for (var token : line.split(" ")) add(token);
        } 
		String        nextStr() { return remove(); }
		int           nextInt() { return Integer.parseInt(remove()); } 
		long         nextLong() { return Long.parseLong(remove()); }
		double        nextDbl() { return Double.parseDouble(remove()); } 
		BigInteger nextBigInt() { return new BigInteger(remove()); } 
    }

	private static tokens nextLine(BufferedReader cin) { 
		String line = null;
		try { 
            line = cin.readLine(); 
        }
		catch (IOException e) { 
            e.printStackTrace(); 
        }
		return new tokens(line); 
    }
	private static byte[] to_bytes(String s)           { return s.getBytes(); }
	private static byte[] to_bytes(Integer x)          { return to_bytes(x.toString()); }
	private static byte[] to_bytes(Long x)             { return to_bytes(x.toString()); }
	private static byte[] to_bytes(String f, Double x) { return to_bytes(String.format(f,x)); }
	private static byte[] to_bytes(BigInteger x)       { return to_bytes(x.toString()); }
	private final static byte[] space = to_bytes(" ");
	private final static byte[] endl  = to_bytes("\n");
    public long[] buf;
    public long[] arr;
    
    public static void main(String[] args) throws IOException {
        final var cin  = new BufferedReader(new InputStreamReader(System.in));
        final var cout = new BufferedOutputStream(System.out);
        Task2 tsk = new Task2();

        var input = nextLine(cin);
        int N = input.nextInt();
        tsk.arr = new long[N];
        tsk.buf = new long[N];
        input = nextLine(cin);
        for(int i = 0; i < N; ++i){
            tsk.arr[i] = input.nextLong();
        }
        tsk.mergeSort(0, N);
        for(long el : tsk.arr){
            cout.write(to_bytes(el + " "));
        }
        cout.flush();
    }

    public void mergeSort(int l, int r){ // [l, r)
        if(l >= r){
            return;
        }
        if(r - l <= 5){
            insertion_sort(l, r);
            return;
        }
        int m = (r - l) / 2;
        mergeSort(l, l + m);
        mergeSort(l + m, r);
        merge(l, l + m, r);
    }

    public void merge(int s, int m, int e){
        int s1 = s;
        int e1 = m;
        int s2 = m;
        int e2 = e;
        int buf_e = 0;
        while(s1 < e1 && s2 < e2){
            if(arr[s1] < arr[s2]){
                buf[buf_e] = arr[s1++];
            }else{
                buf[buf_e] = arr[s2++];
            }
            ++buf_e;
        }
        while(s1 < e1){
            buf[buf_e++] = arr[s1++];
        }
        while(s2 < e2){
            buf[buf_e++] = arr[s2++];
        }
        System.arraycopy(buf, 0, arr, s, e - s);
    }

    public void insertion_sort(int l, int r){
        for(int i = l; i < r; ++i){
            for(int j = i; j > l && arr[j] < arr[j - 1]; --j){
                long tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
            }
        }
    }
}
