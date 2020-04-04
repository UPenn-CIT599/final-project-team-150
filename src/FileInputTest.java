import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileInputTest {

    @Test
    public void readFile() throws IOException {
        City Atlanta = FileInput.readFile("src/DATA/Atlanta.tsp");
        assertEquals(Atlanta.getName(), "Atlanta");
        assertEquals(Atlanta.getNum(), 20);
    }
}