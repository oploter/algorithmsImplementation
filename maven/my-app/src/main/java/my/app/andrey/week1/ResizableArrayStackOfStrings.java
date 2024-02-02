package my.app.andrey.week1;

public class ResizableArrayStackOfStrings implements MyStack{
    private String[] s = new String[1];
    private int n = 0;

    public ResizableArrayStackOfStrings(){
        s = new String[1];
        n = 0;
    }

    private void resize(int newCapacity){
        String[] newS = new String[newCapacity];
        for(int i = 0; i < n; ++i){
            newS[i] = s[i];
        }
        s = newS;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public String pop(){
        if(n == 0) return null;
        if(n <= s.length/4) resize(s.length / 2);
        return s[--n];
    }

    public void push(String item){
        if(s.length == n) resize(2 * s.length);
        s[n++] = item;
    }   
}
