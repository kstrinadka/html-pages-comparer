package kstrinadka.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlAndHtmlParser {
    private final static String YESTERDAY = "yesterday";
    private final static String TODAY = "today";

    /**
     * @return - Хешмапа с сегодняшними url и html страницами
     */
    public static Map<String, String> getUrlsAndHtmlPagesTodayMap() {
        List<String> todayNames = getListOfHtmlPagesNames(NamesAndPaths.TODAY_NAMES_FILE_PATH);
        return parseUrlsAndHtmlPagesFromNames(todayNames, TODAY);
    }

    /**
     * @return - Хешмапа со вчерашними url и html страницами
     */
    public static Map<String, String> getUrlsAndHtmlPagesYesterdayMap() {
        List<String> todayNames = getListOfHtmlPagesNames(NamesAndPaths.YESTERDAY_NAMES_FILE_PATH);
        return parseUrlsAndHtmlPagesFromNames(todayNames, YESTERDAY);
    }

    /**
     * @param pathToFileWithNames - путь к файлу, который содержит список имен для html страниц и их URL
     * @return - список имен для файлов, из которых будут читаться html страницы и URL.
     */
    public static List<String> getListOfHtmlPagesNames(String pathToFileWithNames) {
        List<String> namesList = new ArrayList<>();
        try {
            // Считываем содержимое файла names.txt
            BufferedReader reader = new BufferedReader(new FileReader(pathToFileWithNames));
            String line;
            while ((line = reader.readLine()) != null) {
                namesList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return namesList;
    }


    /**
     * @param namesList - список имен файлов, с которых будут считываться URL и HTML страницы
     * @param day - yesterday или today
     */
    public static Map<String, String> parseUrlsAndHtmlPagesFromNames(List<String> namesList, String day) {
        Map<String, String> urlsAndHtmlPages = new HashMap<>();
        for (String name: namesList) {
            String url = getUrlByFileName(name, day);
            String htmlPage = getHtmlPageByFileName(name, day);
            urlsAndHtmlPages.put(url, htmlPage);
        }
        return urlsAndHtmlPages;
    }


    /**
     * Считывается html страница по имени и дню
     */
    private static String getHtmlPageByFileName(String name, String day) {
        String htmlPage = null;
        String htmlPageFileName = createHtmlFileName(name, day);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(htmlPageFileName));
            StringBuilder htmlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlBuilder.append(line);
            }
            htmlPage = htmlBuilder.toString();
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return htmlPage;
    }

    /**
     * Считывается URL страницы по имени и дню
     */
    private static String getUrlByFileName(String name, String day) {
        String url = null;
        String urlFileName = createUrlFileName(name, day);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(urlFileName));
            url = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return url;
    }

    /**
     * @return - имя файла из которого считывается URL
     */
    private static String createUrlFileName(String name, String day) {
        if (day.equals(TODAY)) {
            return NamesAndPaths.TODAY_FILES_PATH + name + NamesAndPaths.URL_FILE_POSTFIX;
        }
        if (day.equals(YESTERDAY)) {
            return NamesAndPaths.YESTERDAY_FILES_PATH + name + NamesAndPaths.URL_FILE_POSTFIX;
        }
        throw new RuntimeException("ошибка при генерации имени файла, содержащего URL страницы");
    }

    /**
     * @return - имя файла из которого считывается HTML страница
     */
    private static String createHtmlFileName(String name, String day) {
        if (day.equals(TODAY)) {
            return NamesAndPaths.TODAY_FILES_PATH + name + NamesAndPaths.HTML_FILE_POSTFIX;
        }
        if (day.equals(YESTERDAY)) {
            return NamesAndPaths.YESTERDAY_FILES_PATH + name + NamesAndPaths.HTML_FILE_POSTFIX;
        }
        throw new RuntimeException("ошибка при генерации имени файла, содержащего URL страницы");
    }

}
