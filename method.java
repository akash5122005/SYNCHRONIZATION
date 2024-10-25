class Counter{
    private int count = 0;

    public synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName() + ": Incremented value: "+ count);
    }
}
class ThreadDemo extends Thread {
    private Counter counter;
    public ThreadDemo(Counter counter){
        this.counter = counter;
    }
    public void run(){
        for (int i=0; i<5; i++){
            counter.increment();
        }
    }
}
public class Main{
    public static void main(String[] args){
        Counter counter = new Counter();
        ThreadDemo thread2 = new ThreadDemo(counter);
        ThreadDemo thread1 = new ThreadDemo(counter);

        thread2.setName("Thread Thread 2");
        thread1.setName("Thread Thread 1");

        thread2.start();
        thread1.start();

        try{
            thread2.join();
            thread1.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
}
