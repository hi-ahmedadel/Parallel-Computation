public class RaceConditionDemo {
  
    static int counter = 0; 

    static class MyTask implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++) {
               
                int current = counter; 
                
                try {
                   
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
            
                counter++; 
                int updated = counter; 
                
                System.out.println(Thread.currentThread().getName()
                        + " -> Current (Read): " + current + ", Updated (Final Counter): " + updated);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Race Condition Demo ---");
        System.out.println("Expected Final Counter: 10 (5 increments from Thread-A + 5 increments from Thread-B)");
        
      
        Thread tA = new Thread(new MyTask(), "Thread-A");
        Thread tB = new Thread(new MyTask(), "Thread-B");
        
        tA.start();
        tB.start();
        
      
        tA.join();
        tB.join();
        
        System.out.println("\nFinal Counter Value (Actual Result): " + counter);
        System.out.println("Note: The final value is often less than 10 due to Race Condition.");
    }
}
