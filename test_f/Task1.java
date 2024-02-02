import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {
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
    public static void main(String[] args) throws IOException {
        final var cin  = new BufferedReader(new InputStreamReader(System.in));
        final var cout = new BufferedOutputStream(System.out);
        
        var input = nextLine(cin);
        int N = input.nextInt();
        input = nextLine(cin);        
        long[] arr = new long[N];
        for(int i = 0; i < N; ++i){
            arr[i] = input.nextLong();
        }
        sort(arr, 0, N);
        StringBuilder sb = new StringBuilder();
        for(long el : arr){
            sb.append(el);
            sb.append(" ");
        }
        cout.write(to_bytes(sb.toString()));
        cout.flush();
    }

    public static void slowSort(long[] arr, int l, int r){
        for(int i = l; i < r; ++i){
            int m_ind = i;
            for(int j = i + 1; j < r; ++j){
                if(arr[m_ind] > arr[j]){
                    m_ind = j;
                }
            }
            long tmp = arr[i];
            arr[i] = arr[m_ind];
            arr[m_ind] = tmp;
        }
    }

    public static void sort(long[] arr, int l, int r){ // [l, r)
        if(l >= r) return;
        if(r - l <= 2){
            slowSort(arr, l, r);
            return;
        }
        
        int m = ThreadLocalRandom.current().nextInt(l, r);
        int p = partition(arr, arr[m], l, r - 1);
        sort(arr, l, p);
        sort(arr, p, r);
    }

    public static int partition(long[] arr, long x, int st, int nd){ // [st, nd]
        int l = st;
        int r = nd;
        while(l <= r){
            while(l <= r && arr[l] < x){
                ++l;
            }
            while(r >= l && arr[r]  >= x){
                --r;
            }
            if(l <= r){
                long t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                ++l;
                --r;
            }
        }
        return l;
    }
}
