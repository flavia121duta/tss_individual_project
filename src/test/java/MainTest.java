import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMainMethod() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
