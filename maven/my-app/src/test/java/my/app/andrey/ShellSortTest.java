package my.app.andrey;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class ShellSortTest {
     
    @Test
    public void test1(){
        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7 };
		List<Integer> intList = Arrays.asList(intArray);
		Collections.shuffle(intList);
		intList.toArray(intArray);
        System.out.println(Arrays.toString(intArray));
    }
}
