import java.lang.Thread.State;

public class ThreadControlDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println(" Thread Control Demo (Naming, Priority, States) ");
        System.out.println("=================================================");
      
        Thread t1 = new Thread(() -> {
            try {
              
                System.out.println("\n[T1] Inside run() - State: " + Thread.currentThread().getState());
                System.out.println("[T1] Executing with Priority: " + Thread.currentThread().getPriority());
                for (int i = 0; i < 3; i++) {
                    System.out.println("[T1] Task running: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("[T1] Interrupted.");
            }
        }, "Worker-High-Priority");

   
        Thread t2 = new Thread(); 
        t2.setName("Helper-Low-Priority"); 
        
        t2.setPriority(Thread.MIN_PRIORITY); 
        t1.setPriority(Thread.MAX_PRIORITY); 
        
     
        System.out.println("Status T1 (Before start): " + t1.getState()); 
        System.out.println("Status T2 (Before start): " + t2.getState());
        

        t1.start(); 
        t2.start();
        
        Thread.sleep(50); 
        System.out.println("Status T1 (After start): " + t1.getState());
        System.out.println("Status T2 (After start): " + t2.getState());
        
   
        t1.join(); 
        t2.join();

        System.out.println("\nStatus T1 (After completion): " + t1.getState());
        System.out.println("Status T2 (After completion): " + t2.getState());
        
        System.out.println("\n--- ThreadControlDemo finished ---");
    }
}
