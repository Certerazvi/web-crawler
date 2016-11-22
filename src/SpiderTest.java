import static junit.framework.TestCase.assertEquals;

public class SpiderTest {
    @org.junit.Test
    public void search() throws Exception {
        String url    = "https://gocardless.com/";
        Spider spider = new Spider();

        // asserts that there are 42 new links to visit from url page
        // they are later tested to check of they were already visited
        assertEquals(42, spider.searchOneLevel(url).get(url).size());
    }
}