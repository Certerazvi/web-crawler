import java.util.*;

public class Spider {
    private Set<String> urls = new HashSet<>();
    private Map<String, Set<String>> assets = new HashMap<>();
    private Set<String> remainingLinks = new HashSet<>();
    private Set<String> visitedUrls = new HashSet<>();
    private Set<String> urlsToGO = new HashSet<>();
    private Set<String> links = new HashSet<>();


    public void setAssets(Set<String> assets) {
        remainingLinks = (assets);
    }

    /**
     * Main launching point of the program. It builds the legs of the spider
     * which parse each URL starting from the domain.
     *
     * @param url    - The starting point of the spider(domain)
     * @param isTest - in order to make this method sutable for testing
     */
    private Map<String, Set<String>> search(String url, boolean isTest) {
        System.out.println("[");
        SpiderLeg leg = new SpiderLeg(url);
        do {
            links = leg.crawl(url, this);

            urls.addAll(links);
            links.addAll(remainingLinks);
            assets.put(url, urls);

            visitedUrls.add(url);
            urlsToGO.addAll(urls);
            urlsToGO.removeAll(visitedUrls);

            if(urlsToGO.isEmpty()){
                break;
            } else {
                url = nextUrl();
            }
        } while(isTest);
        System.out.println("]");
        return assets;
    }

    public Map<String, Set<String>> search(String url){
        return search(url, true);
    }

    /**
     * Returns the assets for searching just the domain.
     */
    public Map<String, Set<String>> searchOneLevel(String url){
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
