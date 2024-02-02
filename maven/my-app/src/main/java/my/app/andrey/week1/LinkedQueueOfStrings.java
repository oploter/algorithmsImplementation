package my.app.andrey.week1;

public class LinkedQueueOfStrings implements MyQueue{
    private Node head = null;
    private Node tail = null;
    private class Node{
        String item;
        Node next = null;
        public Node(String item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void enqueue(String item){
        Node newTail = new Node(item, null);
        if(tail != null){
            tail.next = newTail;
        }else{
            head = newTail;
        }
        tail = newTail;
    }

    public String dequeue(){
        if(head == null) return null;
        String item = head.item;
        head = head.next;
        if(head == null) tail = null;
        return item;
    }


}
