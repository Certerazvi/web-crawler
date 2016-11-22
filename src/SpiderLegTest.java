import static junit.framework.TestCase.assertEquals;

public class SpiderLegTest {
    @org.junit.Test
    public void crawl() throws Exception {
        String url    = "https://gocardless.com/";
        SpiderLeg leg = new SpiderLeg(url);

        leg.crawl(url);
        // assert that there are 18 static assets on the url page
        assertEquals(18, leg.getAssets().size());
    }
}