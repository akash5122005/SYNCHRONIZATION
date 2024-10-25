import java.util.concurrent.locks.ReentrantLock;

class Printer{
    private final ReentrantLock lock = new ReentrantLock();

    public void print(){
        lock.lock();
        try{
            for(int i = 1; i <= 5;i++){
                System.out.println(Thread.currentThread().getName()+ ": "+i);
                Thread.sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
class PrintThread extends Thread{
    private final Printer printer;

    public PrintThread(Printer printer){
        this.printer = printer;
    }
    public void run(){
        printer.print();
    }
}

public class Main{
    public static void main(String[] args){
        Printer printer = new Printer();

        PrintThread thread2 = new PrintThread(printer);
        PrintThread thread1 = new PrintThread(printer);

        thread2.setName("Thread 2");
        thread1.setName("Thread 1");

        thread2.start();
        try{
            Thread.sleep(50);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        thread1.start();

        try{
            thread2.join();
            thread1.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
