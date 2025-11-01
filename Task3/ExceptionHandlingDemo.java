import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandlingDemo {


    static class WorkerWithTryCatch extends Thread {
        public void run() {
            try {
                System.out.println(getName() + " (Try-Catch): Started.");
           
                int result = 100 / 0; 
            } catch (ArithmeticException e) {
                System.out.println(getName() + " (Try-Catch): Caught Exception: " + e.getMessage());
            }
            System.out.println(getName() + " (Try-Catch): Finished.");
        }
    }

    static class WorkerNoCatch extends Thread {
        public void run() {
            System.out.println(getName() + " (UncaughtHandler): Started.");
         
            throw new RuntimeException("Uncaught Exception Test from " + getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("--- 1. Demo: Handling with try-catch ---");
        WorkerWithTryCatch t1 = new WorkerWithTryCatch();
        t1.setName("Thread-TC-1");
        t1.start();
        t1.join(); 

        System.out.println("\n--- 2. Demo: Global DefaultUncaughtExceptionHandler ---");
       
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.err.println("GLOBAL Handler caught exception in: " + thread.getName());
            System.err.println("Error (Global): " + exception.getMessage());
        });

        WorkerNoCatch t2 = new WorkerNoCatch();
        t2.setName("Thread-Global-2");
        t2.start();
        t2.join(); 

        System.out.println("\n--- 3. Demo: Thread-Specific UncaughtExceptionHandler (Higher Priority) ---");
      
        WorkerNoCatch t3 = new WorkerNoCatch();
        t3.setName("Thread-Specific-3");
        
        t3.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("SPECIFIC Handler caught exception in: " + thread.getName());
            System.out.println("Error (Specific): " + exception.getMessage());
        });
        
        t3.start();
        t3.join(); 
    }
}
