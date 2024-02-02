import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class p{
    public static void main(String[] args){
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        while(!st.isEmpty()){
            StdOut.println("Int: " + st.pop()) ;
        }
    }
}