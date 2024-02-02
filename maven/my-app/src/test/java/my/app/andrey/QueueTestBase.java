package my.app.andrey;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import my.app.andrey.week1.MyQueue;

import org.junit.Before;

import java.util.stream.IntStream;

public abstract class QueueTestBase<T extends MyQueue> {
    private T instance;

    protected abstract T createInstance();

    @Before 
    public void setUp() {
        instance = createInstance();
    }

    private void cleanUp(){
        if(instance == null) return;
        while(!instance.isEmpty()){
            instance.dequeue();
        }
    }

    @Test
    public void testPushAll(){
        cleanUp();
        String[] seq = {"A", "B", "C", "D", "E", "F", "G", "H"};
        assertThat(instance.isEmpty()).isTrue();
        for(int i = 0; i < seq.length; ++i){
            instance.enqueue(seq[i]);
        }
        assertThat(instance.isEmpty()).isFalse();
        for(int i = 0; i < seq.length; ++i){
            assertThat(instance.dequeue()).isEqualTo(seq[i]);
        }
        assertThat(instance.isEmpty()).isTrue();
    }

    @Test
    public void testPushPop(){
        cleanUp();
        String[] seq = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for(int i = 0; i < seq.length; ++i){
            instance.enqueue(seq[i]);
            assertThat(instance.dequeue()).isEqualTo(seq[i]);
        }
    }

    @Test
    public void testDifferentTimePops(){

    }

}