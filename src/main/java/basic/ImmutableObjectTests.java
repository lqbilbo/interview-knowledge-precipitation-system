package basic;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ImmutableObjectTests {

    @Test
    public void testDropWhile() throws Exception {
        final long count = Stream.of(1,2,3,4,5)
                .dropWhile(i  -> i % 2 != 0)
                .count();
        assertEquals(4, count);
    }

//    @Test
//    public void testFlatMapping() throws Exception {
//        final Set<Integer> result = Stream.of("a", "ab", "abc")
//                .collect(Collectors.flatMapping(v -> v.chars().boxed()), Collectors.toSet());
//        assertEquals(3, result.size());
//    }

    @Test
    public void testStream() throws Exception {
        final long count = Stream.of(
                Optional.of(1),
                Optional.empty(),
                Optional.of(2)
        ).flatMap(Optional::stream)
                .count();
        assertEquals(2, count);
    }
}
