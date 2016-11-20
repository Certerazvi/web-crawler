import java.util.*;

public class Spider {
    private Set<String> urls = new HashSet<>();
    private Map<String, Set<String>> assets = new HashMap<>();
    private Set<String> remainingLinks = new HashSet<>();
    private Set<String> visitedUrls = new HashSet<>();
    private Set<String> urlsToGO = new HashSet<>();


    public void setAssets(Set<String> assets) {
        remainingLinks = (assets);
    }

    /**
     * Our main launching point for the Spider's functionality. Internally it
     * creates spider legs that make an HTTP request and parse the response
     * (the web page).
     *
     * @param url- The starting point of the spider
     */
    public void search(String url) {
        SpiderLeg leg = new SpiderLeg(url);
        while(true) {
            //if(assets.get(url) == null) {
                Set<String> links = leg.crawl(url, this);
                //System.out.println("hwrw\n");
                //links.forEach(System.out::println);
                urls.addAll(links);
                //links.addAll(remainingLinks);
                //assets.put(url, links);
                visitedUrls.add(url);
                urlsToGO.addAll(urls);
                urlsToGO.removeAll(visitedUrls);
                //System.out.println("hwrw\n");
           // }
            //System.out.println("\n MATAAAAA");
            if(urlsToGO.isEmpty()){
                //System.out.println("\nUrls is empty");
                break;
            } else {
                url = nextUrl();
                //System.out.println("\nNext url");
            }
            System.out.println(urlsToGO.size());
            //System.out.println("\n MATAAAAA");
        }
        //System.out.println(gson.toJson(this));
    }

    /**
     * Returns the next URL to visit (in the order that they were found).
     * We also do a check to make sure this method doesn't return a URL that has already been visited.
     *
     * @return
     */
    private String nextUrl() {
        String nextUrl;
        Iterator i = urlsToGO.iterator();
        nextUrl    = i.next().toString();
        return nextUrl;
    }
}
