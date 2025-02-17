package task_01;

public class Homework01 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int count = 0;
        for (int i = 0; i < 2000000; i++) {
            String iText = String.valueOf(i);
            if (i % 21 == 0 && iText.contains("3")) {
                count++;
            }
        }
        long end = System.nanoTime();
        long timeResult1 = end - start;
        System.out.println("Result of a single-threaded application:" + count);
        System.out.println("Execution time of a single-threaded application: " + timeResult1 + " ns");

        multiThread(timeResult1);

    }


    public static void multiThread(long timeResult1) {
        long start = System.nanoTime();
        int count1 = 0;
        MyThread10 thread1 = new MyThread10();
        Thread newThread = new Thread(thread1);
        newThread.start();

        for (int i = 0; i < 1000000; i++) {
            String iText = String.valueOf(i);
            if (i % 21 == 0 && iText.contains("3")) {
                count1++;
            }
        }
        int result = count1 + thread1.getCount2();
        long end = System.nanoTime();
        long timeResult2 = end - start;
        System.out.println("Result of a multi-threaded application:" + result);
        System.out.println("Execution time of a multi-threaded application: " + timeResult2 + " ns");
        double percentage = ((timeResult1 - timeResult2) / (double) timeResult1 * 100);
        percentage = Math.round(percentage * 100.0) / 100.0;
        System.out.println("Time saving percentage in multithreading: " + percentage + "%");
    }


}//end of main

