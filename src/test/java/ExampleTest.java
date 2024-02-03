import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {

    @Test
    public void testSimple(){
        int a = 1 + 1;
        int resultadoEsperado = 2;
        assertEquals(resultadoEsperado, a);
    }
}
