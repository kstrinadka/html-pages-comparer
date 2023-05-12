package kstrinadka.com;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PagesComparer {

    // ключ - URL; значение - HTML страница
    private final Map<String, String> urlsAndHtmlPagesToday;
    private final Map<String, String> urlsAndHtmlPagesYesterday;

    private Set<String> disappeared;
    private Set<String> appeared;
    private Set<String> changed;


    public PagesComparer(Map<String, String> urlsAndHtmlPagesToday,
                         Map<String, String> urlsAndHtmlPagesYesterday) {
        this.urlsAndHtmlPagesToday = urlsAndHtmlPagesToday;
        this.urlsAndHtmlPagesYesterday = urlsAndHtmlPagesYesterday;
        this.detectingChanges();
    }

    /**
     * Определяем изменения за день
     */
    private void detectingChanges() {
        disappeared = new HashSet<>(urlsAndHtmlPagesYesterday.keySet());
        disappeared.removeAll(urlsAndHtmlPagesToday.keySet());

        appeared = new HashSet<>(urlsAndHtmlPagesToday.keySet());
        appeared.removeAll(urlsAndHtmlPagesYesterday.keySet());

        changed = new HashSet<>();
        for (String url : urlsAndHtmlPagesYesterday.keySet()) {
            if (urlsAndHtmlPagesToday.containsKey(url) && !urlsAndHtmlPagesToday.get(url).equals(urlsAndHtmlPagesYesterday.get(url))) {
                changed.add(url);
            }
        }
    }

    public Set<String> getDisappearedPages(){
        return this.disappeared;
    }

    public Set<String> getAppearedPages(){
        return this.appeared;
    }

    public Set<String> getChangedPages(){
        return this.changed;
    }

    /**
     * @return - текст письма для секретаря
     */
    public String createMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Здравствуйте, дорогая и.о. секретаря\n\n");
        sb.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");
        if (!disappeared.isEmpty()) {
            sb.append("Исчезли следующие страницы:\n");
            for (String url : disappeared) {
                sb.append(url).append("\n");
            }
            sb.append("\n");
        }
        if (!appeared.isEmpty()) {
            sb.append("Появились следующие новые страницы:\n");
            for (String url : appeared) {
                sb.append(url).append("\n");
            }
            sb.append("\n");
        }
        if (!changed.isEmpty()) {
            sb.append("Изменились следующие страницы:\n");
            for (String url : changed) {
                sb.append(url).append("\n");
            }
            sb.append("\n");
        }
        sb.append("С уважением,\nавтоматизированная система мониторинга.\n");

        return sb.toString();
    }
}
