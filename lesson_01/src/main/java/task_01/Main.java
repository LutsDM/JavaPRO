package task_01;

public class Main {
    public static void main(String[] args) {
        /*
        Два базовых способа создания отдельного потока:
        1. Наследование от класса Thread
        2. Реализация интерфейса Runnable
         */
        MyThread1 myThread12 = new MyThread1();
        myThread12.start();

        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Sheep mySheep = new Sheep();
        Thread newThread = new Thread(mySheep);
        newThread.setName("SheepThread");
        newThread.setDaemon(true);
        newThread.start();



        for (int i = 0; i < 5; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

