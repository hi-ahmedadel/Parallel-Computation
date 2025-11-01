public class AdvancedThreadingDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==============================================");
        System.out.println("⚙️ Advanced Threading Demo (Groups & Daemon) ⚙️");
        System.out.println("==============================================");
        
       
        System.out.println("\n--- 1. Thread Group Example ---");

        ThreadGroup group = new ThreadGroup("Monitor-Group"); 
        
      
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " running in " +
                               Thread.currentThread().getThreadGroup().getName());
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

     
        Thread tA = new Thread(group, task, "Group-Worker-A");
        Thread tB = new Thread(group, task, "Group-Worker-B");
        
        tA.start();
        tB.start();
        
        Thread.sleep(50); 
        
       
        System.out.println("Active Threads in Group: " + group.activeCount());
        group.list(); 

       
        tA.join(); 
        tB.join();

       
        System.out.println("\n--- 2. Daemon vs User Thread Example ---");
        
        
        Thread daemonThread = new Thread(() -> {
            try {
               
                while (true) {
                    System.out.println("[Daemon] Performing background service...");
                    Thread.sleep(500); 
                }
            } catch (InterruptedException e) {
                System.out.println("Daemon interrupted");
            }
        }, "GC-Sim-Daemon");
        
        
        daemonThread.setDaemon(true); 
        daemonThread.start();
        
       
        Thread userThread = new Thread(() -> {
            System.out.println("[User] Core application task started: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);  
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[User] Core application task finished.");
        }, "Main-User-Task"); 

        userThread.start();
        
     
        userThread.join(); 
        
        System.out.println("Main thread ends: " + Thread.currentThread().getName()); 
      
        
        System.out.println("\n--- AdvancedThreadingDemo finished ---");
    }
}
