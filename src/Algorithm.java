import java.io.IOException;

public abstract class Algorithm {
    public Algorithm() {}

    public abstract void programStarts(String fileName, int cutOffTime) throws IOException;

    public void programEnds() {
        System.out.println("<-----------Travelling Salesman Problem Solved------------>");
    };
}
