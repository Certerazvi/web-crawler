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
    // Use a fake USER_AGENT so the web server thinks this is a normal web browser.
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; " +
            "WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 " +
            "Safari/535.1";
    private transient String domain;
    private String url;
    private Set<String> assets = new HashSet<String>();

    public SpiderLeg(String domain){
        this.domain = domain;
    }

    /**
     * This performs all the work. It makes an HTTP request, checks the
     * response, and then gather all the links on the page. Perform a
     * searchForWord after the successful crawl
     *
     * @param url - The URL to visit
     * @return whether or not the crawl was successful
     */
    public Set<String> crawl(String url, Spider spider) {
        this.url = url;
        Set<String> urls = new HashSet<String>();

        if (url.startsWith(domain)) {
            try {
                Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
                Document htmlDocument = connection.get();
                if (connection.response().statusCode() != 200) {
                    // 200 is the HTTP OK status code indicating if it is ok.
                    System.out.println("\nError visiting " + url);
                }

                Elements linksOnPage = htmlDocument.select("[href]");
                Elements images = htmlDocument.select("[src]");
                System.out.println("Found (" + linksOnPage.size() + ") links");
                for (Element link : linksOnPage) {
                    if (connection.response().contentType().contains
                            ("text/html") && url.startsWith(domain) && url
                            .contains("#")) {
                        urls.add(link.absUrl("href"));
                    } else {
                        assets.add(link.absUrl("href"));
                    }
                }

                for (Element image : images) {
                    assets.add(image.absUrl("src"));
                }
                spider.setAssets(assets);
                Gson gson = new Gson();
                System.out.println(gson.toJson(this));
                return urls;
            } catch (IOException ioe) {
                // We were not successful in our HTTP request
                System.out.println("\nSomething went wrong here: " + url);
            }
        }
        return urls;
    }
}