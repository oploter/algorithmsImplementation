package my.app.andrey.week1;

public interface MyQueue {
    void enqueue(String item);
    String dequeue();
    boolean isEmpty();
}
