import java.util.*;

public class Spider {
    private Set<String> urls = new HashSet<String>();
    private Map<String, Set<String>> assets = new HashMap<String,
            Set<String>>();
    private Set<String> remainingLinks = new HashSet<String>();

    public void setAssets(Set<String> assets) {
        remainingLinks = assets;
    }

    /**
     * Our main launching point for the Spider's functionality. Internally it
     * creates spider legs that make an HTTP request and parse the response
     * (the web page).
     *
     * @param url- The starting point of the spider
     */
    public void search(String url) {
        while(true) {
            SpiderLeg leg = new SpiderLeg(url);
            if(assets.get(url) == null) {
                Set<String> links = leg.crawl(url, this);
                urls.addAll(links);
                links.addAll(remainingLinks);
                assets.put(url, links);
                urls.remove(url);
            }
            System.out.println("\n MATAAAAA");
            if(urls.isEmpty()){
                break;
            } else {
                url = nextUrl();
            }
            System.out.println("\n MATAAAAA");
        }
        //System.out.println(gson.toJson(this));
        System.out.println("\n===== Done =====");
    }

    /**
     * Returns the next URL to visit (in the order that they were found).
     * We also do a check to make sure this method doesn't return a URL that has already been visited.
     *
     * @return
     */
    private String nextUrl() {
        String nextUrl;
        Iterator i = urls.iterator();
        nextUrl    = i.next().toString();
        return nextUrl;
    }
}
