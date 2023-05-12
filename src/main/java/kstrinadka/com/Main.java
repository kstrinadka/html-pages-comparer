package kstrinadka.com;

public class Main {
    public static void main(String[] args) {

        PagesComparer pagesComparer = new PagesComparer(UrlAndHtmlParser.getUrlsAndHtmlPagesTodayMap(),
                UrlAndHtmlParser.getUrlsAndHtmlPagesYesterdayMap());

        System.out.println(pagesComparer.createMessage());
    }
}
