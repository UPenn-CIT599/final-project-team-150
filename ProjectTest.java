import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProjectTest {

    @Test
    public void readFile1() throws IOException {
        City Atlanta = FileInput.readFile("DATA/Atlanta.tsp");
        assertEquals(Atlanta.getName(), "Atlanta");
        assertEquals(Atlanta.getNum(), 20);
    }

    @Test
    public void readFile2() throws IOException {
        City Atlanta = FileInput.readFile("DATA/Berlin.tsp");
        assertEquals(Atlanta.getName(), "Berlin");
        assertEquals(Atlanta.getNum(), 52);
    }

    @Test
    public void readFile3() throws IOException {
        City Atlanta = FileInput.readFile("DATA/Boston.tsp");
        assertEquals(Atlanta.getName(), "Boston");
        assertEquals(Atlanta.getNum(), 40);
    }

    @Test
    public void readFile4() throws IOException {
        City Atlanta = FileInput.readFile("DATA/NYC.tsp");
        assertEquals(Atlanta.getName(), "NYC");
        assertEquals(Atlanta.getNum(), 68);
    }

    @Test
    public void readFile5() throws IOException {
        City Atlanta = FileInput.readFile("DATA/Toronto.tsp");
        assertEquals(Atlanta.getName(), "Toronto");
        assertEquals(Atlanta.getNum(), 109);
    }

    @Test
    public void createCity1() {
        City city = new City("Ann Arbor", 10);
        assertEquals(city.getName(), "Ann Arbor");
        assertEquals(city.getNum(), 10);
    }

    @Test
    public void createCity2() {
        City city = new City("Flint", 15);
        assertEquals(city.getName(), "Flint");
        assertEquals(city.getNum(), 15);
    }

    @Test
    public void createCity3() {
        City city = new City("Grand Rapids", 20);
        assertEquals(city.getName(), "Grand Rapids");
        assertEquals(city.getNum(), 20);
    }

    @Test
    public void createCity4() {
        City city = new City("Detroit", 7);
        assertEquals(city.getName(), "Detroit");
        assertEquals(city.getNum(), 7);
    }

    @Test
    public void createCity5() {
        City city = new City("Plymouth", 9);
        assertEquals(city.getName(), "Plymouth");
        assertEquals(city.getNum(), 9);
    }

    @Test
    public void createLocation1() {
        Location location = new Location(1, 1.0, 2.0);
        assertEquals((int) location.getX(), 1);
        assertEquals((int) location.getY(), 2);
    }

    @Test
    public void createLocation2() {
        Location location = new Location(2, 2.0, 3.0);
        assertEquals((int) location.getX(), 2);
        assertEquals((int) location.getY(), 3);
    }

    @Test
    public void createLocation3() {
        Location location = new Location(2, 3.0, 4.0);
        assertEquals((int) location.getX(), 3);
        assertEquals((int) location.getY(), 4);
    }

    @Test
    public void createLocation4() {
        Location location = new Location(2, 4.0, 5.0);
        assertEquals((int) location.getX(), 4);
        assertEquals((int) location.getY(), 5);
    }

    @Test
    public void createLocation5() {
        Location location = new Location(2, 5.0, 6.0);
        assertEquals((int) location.getX(), 5);
        assertEquals((int) location.getY(), 6);
    }
}