package my.app.andrey;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import my.app.andrey.week1.Deque;

import org.junit.Before;

import java.util.Iterator;
import java.util.stream.IntStream;

public class DequeTest {
/*    
    @Test
    public void test1(){
        Deque<Integer> d = new Deque<>();
        for(int i = 1; i < 50; ++i){
            d.addFirst(i);
            assertThat(d.size()).isEqualTo(i);
        }
        //System.out.println("Added everything");
        for(int i = 1;  i < 40; ++i){
            assertThat(d.removeLast()).isEqualTo(i);
        }
        //System.out.println("+++++++++++++++++++++");
        assertThat(d.size()).isEqualTo(10);
        for(int i = 49; i >= 40; --i){
            assertThat(d.removeFirst()).isEqualTo(i);
        }
        assertThat(d.isEmpty()).isTrue();

    }
 
    @Test
    public void testAddFirst(){
        Deque<Integer> d = new Deque<Integer>();
        for(int t = 0; t < 10; ++t){
            for(int i = 0; i < 1000; ++i){
                d.addFirst(i);
            }
            if(t % 2 == 0){
                int cnt = 999;
                int sz = 1000;
                while(!d.isEmpty()){
                    assertThat(d.size()).isEqualTo(sz--);
                    assertThat(d.removeFirst()).isEqualTo(cnt--);
                }
            }else{
                int cnt = 0;
                int sz = 1000;
                while(!d.isEmpty()){
                    assertThat(d.size()).isEqualTo(sz--);
                    assertThat(d.removeLast()).isEqualTo(cnt++);
                }
            }
        }
    }

    @Test
    public void testAddLast(){
        Deque<Integer> d = new Deque<Integer>();
        for(int t = 0; t < 10; ++t){
            for(int i = 0; i < 1000; ++i){
                d.addLast(i);
            }
            if(t % 2 == 0){
                int sz = 1000;
                int cnt = 0;
                while(!d.isEmpty()){
                    assertThat(d.size()).isEqualTo(sz--);
                    assertThat(d.removeFirst()).isEqualTo(cnt++);
                }
            }else{
                int cnt = 999;
                int sz = 1000;
                while(!d.isEmpty()){
                    assertThat(d.size()).isEqualTo(sz--);
                    assertThat(d.removeLast()).isEqualTo(cnt--);
                }
            }
        }
    }

    @Test
    public void test2(){
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(8);
        assertThat(d.size()).isEqualTo(1);
        d.addLast(6);
        assertThat(d.size()).isEqualTo(2);
        d.addLast(5);
        assertThat(d.size()).isEqualTo(3);
        d.addFirst(-1);
        assertThat(d.size()).isEqualTo(4);
        d.addFirst(2);
        assertThat(d.size()).isEqualTo(5);
        assertThat(d.removeLast()).isEqualTo(5);
        assertThat(d.size()).isEqualTo(4);
        assertThat(d.removeFirst()).isEqualTo(2);
        assertThat(d.size()).isEqualTo(3);
        assertThat(d.removeFirst()).isEqualTo(-1);
        assertThat(d.size()).isEqualTo(2);
        d.addFirst(9);
        d.addFirst(8);
        d.addFirst(7);
        d.addFirst(6);
        d.addFirst(5);
        d.addFirst(4);
        d.addLast(2);
        assertThat(d.removeLast()).isEqualTo(2);
        d.addLast(8);
        d.addLast(9);
        d.addLast(10);
        d.addLast(11);
        d.addLast(12);
        assertThat(d.size()).isEqualTo(13);
    }

    @Test
    public void testPushPop(){
        Deque<Integer> d = new Deque<Integer>();
        for(int i = 0; i < 1000; ++i){
            switch (i % 4){
                case 0:
                    d.addFirst(i);
                    assertThat(d.removeFirst()).isEqualTo(i);
                    break;
                case 1:
                    d.addFirst(i);
                    assertThat(d.removeLast()).isEqualTo(i);
                    break;
                case 2:
                    d.addLast(i);
                    assertThat(d.removeFirst()).isEqualTo(i);
                    break;
                case 3:
                    d.addLast(i);
                    assertThat(d.removeLast()).isEqualTo(i);
                    break;
                default:
                    break;
            }
            
        }
    }
*/
    @Test
    public void testIterator1(){
        Deque<Integer> d = new Deque<>();
        Iterator<Integer> it = d.iterator();
        assertThat(it.hasNext()).isFalse();
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.addFirst(4);
        d.addLast(5);
        d.addFirst(6);
        it = d.iterator();
        int i = 0;
        int[] seq = {6, 4, 3, 2, 1, 5};
        while(it.hasNext()){
            assertThat(it.next()).isEqualTo(seq[i++]);
        }
        
    }
}