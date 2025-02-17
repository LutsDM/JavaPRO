package task_01;

public class MyThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 200; i < 203 ; i++) {
            String threadName = getName();
            System.out.println(threadName + " " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
