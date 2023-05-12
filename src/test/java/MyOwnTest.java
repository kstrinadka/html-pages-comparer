import kstrinadka.com.PagesComparer;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MyOwnTest {

    @Test
    public void oneTest() {

        Map<String, String> yesterdayPages = new HashMap<>();
        yesterdayPages.put("http://example.com/page1", "<html>Вчерашняя версия страницы 1</html>");
        yesterdayPages.put("http://example.com/page2", "<html>Вчерашняя версия страницы 2</html>");
        yesterdayPages.put("http://example.com/page3", "<html>Вчерашняя версия страницы 3</html>");

        Map<String, String> todayPages = new HashMap<>();
        todayPages.put("http://example.com/page1", "<html>Сегодняшняя версия страницы 1</html>");
        todayPages.put("http://example.com/page3", "<html>Сегодняшняя версия страницы 3</html>");
        todayPages.put("http://example.com/page4", "<html>Новая страница 4</html>");



        Set<String> disappearedExpected = new HashSet<>();
        disappearedExpected.add("http://example.com/page2");

        Set<String> appearedExpected = new HashSet<>();
        appearedExpected.add("http://example.com/page4");

        Set<String> changedExpected = new HashSet<>();
        changedExpected.add("http://example.com/page1");
        changedExpected.add("http://example.com/page3");

        PagesComparer pagesComparer = new PagesComparer(todayPages, yesterdayPages);
        Set<String> disappearedActual= pagesComparer.getDisappearedPages();
        Set<String> appearedActual= pagesComparer.getAppearedPages();
        Set<String> changedActual= pagesComparer.getChangedPages();

        assertEquals(disappearedActual, disappearedExpected);
        assertEquals(appearedActual, appearedExpected);
        assertEquals(changedActual, changedExpected);
    }

}
