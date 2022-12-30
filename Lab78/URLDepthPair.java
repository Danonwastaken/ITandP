package Lab78;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.*;

import static Lab78.Crawler.threads;

// Класс для хранения URL пары и глубины
public class URLDepthPair {


    // Регекс шаблон
    public static final String REGEX_URL = "(https?:\\/\\/)((\\w+\\.)+\\.(\\w)+[~:\\S\\/]*)";
    public static final Pattern REGEX_PATTERN = Pattern.compile(REGEX_URL, Pattern.CASE_INSENSITIVE);

    private URL currentUrl; // Ссылка
    private int currentDepth; // Глубина


    public URLDepthPair(URL url, int d) throws MalformedURLException {
        currentUrl = new URL(url.toString());

        currentDepth = d;
    }

    public URL getCurrentUrl() {
        return currentUrl;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    // #endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLDepthPair that = (URLDepthPair) o;
        return currentDepth == that.currentDepth && Objects.equals(currentUrl, that.currentUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentUrl, currentDepth);
    }


    @Override
    public String toString() {
        return "URL: " + currentUrl.toString() + ", Depth: " + currentDepth + ", Threads: " + threads;
    }

    // Проверка на абсолютную ссылку
    public static boolean isAbsolute(String url) {
        Matcher URLChecker = REGEX_PATTERN.matcher(url);
        if (!URLChecker.find()) {
            return false;
        }
        return true;
    }
}