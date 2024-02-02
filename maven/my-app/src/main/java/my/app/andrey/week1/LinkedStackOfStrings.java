package my.app.andrey.week1;

public class LinkedStackOfStrings implements MyStack{
    private Node top = null;
    private class Node{
        String item;
        Node next = null;
        public Node(String item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty(){
        return top == null;
    }

    public void push(String item){
        Node newTop = new Node(item, top);
        top = newTop;
    }

    public String pop(){
        if(top == null) return null;
        String item = top.item;
        top = top.next;
        return item;
    }
}