# Web Crawler

Simple (single-threaded) web crawler.

## Usage
```bash
$ javac -d . -cp lib\gson-2.8.0.jar;lib\jsoup-1.6.0.jar src\Main.java src\Spider.java src\SpiderLeg.java
$ java -cp lib\jsoup-1.6.0.jar;lib\gson-2.8.0.jar; Main
```
As argument you should provide the url as the starting point.
If we take as the starting url: https://gocardless.com/ the program finishes the json in 7.28 minutes.

## Specs

Given a starting URL, it should visit every reachable page under that domain.
For each page, it should determine the URLs of every static asset (images, javascript, stylesheets) on that page.
The crawler should output to STDOUT in JSON format listing the URLs of every static asset, grouped by page.

## Testing
Just a couple of basic tests on the number of static assets a single page has.
For an industrial application further tests should be written. 
