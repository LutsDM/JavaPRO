package task_01;

public class MyThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 20; i < 23; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " -- " + i);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
