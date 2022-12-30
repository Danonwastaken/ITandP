package Lab78;
import java.net.*;

public class Crawler {
    private static URLPool pool;

    public static int threads = 50;
    public int threadTimeSleep = 500;

    public Crawler(String hostUrl, int depth) throws MalformedURLException {
        pool = new URLPool(depth);
        URL rootURL = new URL(hostUrl);
        pool.addURL(new URLDepthPair(rootURL, 0));
    }

    // Запуск сканера
    public void crawl(){
        // Потоки
        for (int i = 0; i < threads; i++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            Thread thread = new Thread(crawler);
            thread.start();
        }
        // Проверка потоков
        while (pool.getPoolCounter() != threads) {
            try {
                Thread.sleep(threadTimeSleep);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
        pool.printURLs();
    }

    public static void main(String[] args) {
        args = new String[] {"https://usainbolt.com/athlete/", "2"};
        if (args.length != 2) {
            System.err.println("Аргументы: <URL> <Depth>");
            System.exit(1);
        }
        try {
            Crawler crawler = new Crawler(args[0], Integer.parseInt(args[1]));
            crawler.crawl();
        }
        catch (MalformedURLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }
}