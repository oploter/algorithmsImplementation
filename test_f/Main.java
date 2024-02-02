import java.util.Scanner;

class Main {
    public static final long MOD = 1000000007;
    public static void main(String[] args) {
        Scanner myInput = new Scanner( System.in );
        int n = myInput.nextInt();
        long pr = 3;
        long ppr = 1;
        long cur = 0;
        for(int i = 3; i <= n; ++i){
            cur = (3 * pr - ppr) % MOD;
            ppr = pr;
            pr = cur;
        }
        System.out.println(pr);
    }
    
}