package Lab78;

import java.util.*;

// Класс пула ссылок
public class URLPool {
    private int poolCounter = 0; // Хранит количество потоков в ожидании

    private int maxDepth;

    private LinkedList<URLDepthPair> unscannedURLs;

    private LinkedList<URLDepthPair> scannedURLs;

    private HashSet<String> unrepeatedURLs; // Хешсет для хранения неповторяющихся ссылок

    public URLPool(int _maxDepth) {
        maxDepth = _maxDepth;
        unscannedURLs = new LinkedList<URLDepthPair>();
        scannedURLs = new LinkedList<URLDepthPair>();
        unrepeatedURLs = new HashSet<String>();

    }

    public synchronized int getPoolCounter() {
            return poolCounter;

    }
    public int addPoolCounter(){
        return poolCounter++;
    }

    public int removePoolCounter(){
        return poolCounter--;
    }
    // Фриз потока
    public synchronized URLDepthPair getURL() {
            while (unscannedURLs.size() == 0) {
                addPoolCounter();
                try {
                    wait();
                } catch (InterruptedException e) {
                }
                removePoolCounter();
            }

            return unscannedURLs.removeFirst();
        }

    // Обрезка ссылки и добавление в пулл обработанных
    public synchronized void addURL(URLDepthPair nextPair) {
            String url = nextPair.getCurrentUrl().toString();
            String cut = (url.endsWith("/")) ? url.substring(0, url.length() - 1) : url;
            if (unrepeatedURLs.contains(cut)) {
                return;
            }
            unrepeatedURLs.add(cut);

            if (nextPair.getCurrentDepth() < maxDepth) {
                unscannedURLs.add(nextPair);
                notify();
            }
            scannedURLs.add(nextPair);
    }

    // Вывод ссылок
    public synchronized void printURLs() {
            while (!scannedURLs.isEmpty()) {
                System.out.println(scannedURLs.removeFirst());
            }
    }
}