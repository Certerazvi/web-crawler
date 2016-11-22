import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        String url       = scanner.next();
        //double startTime = System.currentTimeMillis();
        Spider spider    = new Spider();
        spider.search(url);
        //double finishTime = System.currentTimeMillis();
        //System.out.println("Time: " + (finishTime - startTime) / (1000 * 60));
    }
}
