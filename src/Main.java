public class Main {

    public static void main(String[] args) {
        Spider spider = new Spider();
        System.out.println("===== This is a web crawler =====");
        spider.search("http://ycombinator.com");
        System.out.println();
        spider.search("http://www.stilmasculin.ro/category/stil/");
        System.out.println("\n===== Done =====");
    }
}
