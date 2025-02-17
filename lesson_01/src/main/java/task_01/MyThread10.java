package task_01;

public class MyThread10 implements Runnable {

    private int count2;

    @Override
    public void run() {
        for (int i = 1000000; i < 2000000; i++) {
            String iText = String.valueOf(i);
            if (i % 21 == 0 && iText.contains("3")) {
                incrementCount();  // Синхронизированный метод для изменения count2
            }
        }
    }

    // Синхронизированный метод для безопасного изменения count2
    private synchronized void incrementCount() {
        count2++;
    }

    public int getCount2() {
        return count2;
    }
}
