package my.app.andrey;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import my.app.andrey.week1.MyStack;

import org.junit.Before;

import java.util.stream.IntStream;

public abstract class TestBase<T extends MyStack> {
    private T instance;

    protected abstract T createInstance();

    @Before 
    public void setUp() {
        instance = createInstance();
    }

    private void cleanUp(){
        if(instance == null) return;
        while(!instance.isEmpty()){
            instance.pop();
        }
    }

    @Test
    public void testPushPop(){
        cleanUp();
        String[] strs = {"A", "B", "C", "D", "E", "F"};
        for(int i = 0; i < strs.length; ++i){
            instance.push(strs[i]);
            assertThat(instance.pop()).isEqualTo(strs[i]);
        }
    }

    @Test
    public void testPopAtTheEnd(){
        cleanUp();
        String[] strs = {"A", "B", "C", "D", "E", "F", "G"};
        for(int i = 0; i < strs.length; ++i){
            instance.push(strs[i]);
        }
        for(int i = strs.length - 1; i >= 0; --i){
            assertThat(instance.pop()).isEqualTo(strs[i]);
        }
    }

    @Test
    public void testRandomPop(){
        cleanUp();
        String[] strs = IntStream.range(0, 21).mapToObj(a -> ("Str" + a)).toArray(String[]::new);
        int[] seq = {0, 2, 1, 5, 4, 3, 9, 8, 7, 6, 14, 13, 12, 11, 10, 20, 19, 18, 17, 16, 15};
        int[] idToDelete = {0, 2, 5, 9, 14, 20};
        int id = 0;
        int cnt = 0;
        for(int i = 0; i < strs.length; ++i){
            instance.push(strs[i]);
            if(i == idToDelete[id]){
                while(!instance.isEmpty()){
                    assertThat(instance.pop()).isEqualTo("Str" + seq[cnt]);
                    ++cnt;
                }
                ++id;
            }
        }
    }

}