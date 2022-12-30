package Lab78;

import java.io.*;
import java.net.*;
import java.util.regex.*;

// Класс потока сканера
public class CrawlerTask implements Runnable {

    // Регекс шаблон
    public static final String REGEX_GROUPNAME = "url";
    public static final String REGEX_TEMPLATE = String.format("href\\s*=\\s*\"(?<%s>https:\\/\\/\\S*)\"", REGEX_GROUPNAME);
    public static final Pattern REGEX = Pattern.compile(REGEX_TEMPLATE, Pattern.CASE_INSENSITIVE);
    Socket socket;


    public static int waitTime;
    private URLPool pool;

    public CrawlerTask(URLPool p){
        pool = p;
    }

    // Посылает get request запросы, возвращает сокет
    public Socket Request(URLDepthPair pair)
            throws UnknownHostException, SocketException, IOException {

        socket = new Socket(pair.getCurrentUrl().getHost(), 80);
        socket.setSoTimeout(waitTime * 1000);

        OutputStream os = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(os, true);

        writer.println("GET " + pair.getCurrentUrl().getPath() + " HTTP/1.1");
        writer.println("Host: " + pair.getCurrentUrl().getHost());
        writer.println("Connection: close");
        writer.println();

        return socket;
    }

    // Обрабатывает ссылку и добавляет результат в пулл
    public void processURL(URLDepthPair pair) throws IOException {
        try {
            socket = Request(pair);
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
            return;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        String str;
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while ((str = reader.readLine()) != null) {
            Matcher LinkFinder = REGEX.matcher(str);
            URL newSiteURL;
            while (LinkFinder.find()) {
                String newURL = LinkFinder.group(1);
                try {
                    if (URLDepthPair.isAbsolute(newURL)) {
                        newSiteURL = new URL(newURL);
                    } else {
                        newSiteURL = new URL(pair.getCurrentUrl(), newURL);
                    }
                    pool.addURL(new URLDepthPair(newSiteURL, pair.getCurrentDepth() + 1));
                }
                catch (MalformedURLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        reader.close();

        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Запуск
    public void run() {
        URLDepthPair nextPair;
        while (true) {
            nextPair = pool.getURL();
            try {
                processURL(nextPair);
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}