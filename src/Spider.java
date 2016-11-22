import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Spider {
    private Set<String> urls        = new HashSet<>();
    private Set<String> visitedUrls = new HashSet<>();
    private Set<String> urlsToGO    = new HashSet<>();


    /**
     * Main launching point of the program. It builds the legs of the spider
     * which parse each URL starting from the domain.
     *
     * @param url    - The starting point of the spider(domain)
     * @param isTest - in order to make this method suitable for testing
     */
    private Set<String> search(String url, boolean isTest) {
        Set<String> links;
        System.out.println("[");
        SpiderLeg leg = new SpiderLeg(url);
        do {
            links = leg.crawl(url);
            urls.addAll(links);

            //create the set of urls to be visited in the future
            visitedUrls.add(url);
            urlsToGO.addAll(urls);
            urlsToGO.removeAll(visitedUrls);

            if (urlsToGO.isEmpty()){
                break;
            } else {
                url = nextUrl();
            }
        } while (isTest);
        System.out.println("]");
        return urls;
    }

    /**
     * Creates the json. This method is called in Main.
     */
    public Set<String> search(String url){
        return search(url, true);
    }

    /**
     * Returns the assets for searching just the domain (for testing).
     */
    public Set<String> searchOneLevel(String url){
        return search(url, false);
    }

    /**
     * Return the next url in the list of urls that should be visited.
     */
    private String nextUrl() {
        String nextUrl;
        Iterator i = urlsToGO.iterator();
        nextUrl    = i.next().toString();
        return nextUrl;
    }
}
