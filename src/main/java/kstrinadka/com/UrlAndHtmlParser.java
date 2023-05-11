package kstrinadka.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class UrlAndHtmlParser {


    public  HashMap<URL, String> someDayState;


    public HashMap<URL, String> getSomeDayState(String day) {

        return null;
    }



    public List<String> getListOfHtmlPageNames(String pathToFileWithNames) {
        try {
            // Считываем содержимое файла attribute.html в переменную html
            BufferedReader reader = new BufferedReader(new FileReader("html_pages/today/pagesurls/attribute.html"));
            StringBuilder htmlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlBuilder.append(line);
            }
            String html = htmlBuilder.toString();
            reader.close();

            // Делаем что-то с прочитанными данными
            System.out.println("HTML страница: " + html);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return null;
    }


    private BufferedReader createReader(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("html_pages/today/attribute.html"));
            StringBuilder htmlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlBuilder.append(line);
            }
            String html = htmlBuilder.toString();
            reader.close();

            // Делаем что-то с прочитанными данными
            System.out.println("HTML страница: " + html);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return null;
    }

}
