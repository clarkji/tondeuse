import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MowerTest {

    @Test
    public void testProcessMowers() {
        List<String> input = List.of(
                "5 5",
                "1 2 N",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );

        List<Mower.Position> result = Mower.processMowers(input);

        assertEquals("1 3 N", result.get(0).toString());
        assertEquals("5 1 E", result.get(1).toString());
    }
}
