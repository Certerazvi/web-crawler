import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SpiderLeg {
    // Use a fake USER_AGENT so the server thinks this is a normal web browser.
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; " +
            "WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 " +
            "Safari/535.1";
    private transient String domain; //transient avoids including domain in json

    // the fields used by jsoup to create json
    private String url;
    private Set<String> assets = new HashSet<>();


    public SpiderLeg(String domain){
        this.domain = domain;
    }

    public Set<String> getAssets() {
        return assets;
    }

    /**
     * It is the main worker. It performs the web requests and gathers all
     * the static assets on the page.
     *
     * @param url    - The URL to visit
     * @param spider - instance os Spider
     * @return assets on the page
     */
    public Set<String> crawl(String url, Spider spider) {
        this.url = url;
        Set<String> urls = new HashSet<>();

        if (url.startsWith(domain)) {
            try {
                Connection connection = Jsoup.connect(url)
                        .timeout(5*1000)
                        .userAgent(USER_AGENT);
                Document htmlDocument = connection.get();

                Elements links = htmlDocument.select("a[href]");
                Elements media = htmlDocument.select("[src]");

                // add the new links to visit
                for (Element link : links) {
                    if(link.attr("abs:href").startsWith(domain)){
                        // check remove link which are anchors
                        if (!link.attr("abs:href").contains("#")) {
                            urls.add(link.attr("abs:href"));
                        }
                    }
                }

                // add the assets from the url
                for (Element src : media) {
                    assets.add(src.attr("abs:src"));
                }

                spider.setAssets(assets);
                Gson gson = new Gson();

                //create the json
                if(url.equals(domain)){
                    System.out.println(gson.toJson(this));
                } else {
                    System.out.println("," + gson.toJson(this));
                }
            } catch (IOException ioe) {
                // We were not successful in our HTTP request
                // Not outputting not to alter the json
            }
        }
        return urls;
    }
}