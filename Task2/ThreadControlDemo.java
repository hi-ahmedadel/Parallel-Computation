import java.lang.Thread.State;

public class ThreadControlDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("🌟 Thread Control Demo (Naming, Priority, States) 🌟");
        System.out.println("=================================================");
        
        // 1. Thread Naming: Naming during creation (High Priority) [cite: 10, 15]
        //    Thread States: Demonstrating NEW state [cite: 68]
        Thread t1 = new Thread(() -> {
            try {
                // RUNNABLE State inside run() [cite: 69]
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

        // 2. Thread Naming: Naming using setName() (Low Priority) [cite: 21, 26]
        Thread t2 = new Thread(); 
        t2.setName("Helper-Low-Priority"); //
        
        t2.setPriority(Thread.MIN_PRIORITY); // Priority 1 [cite: 46]
        t1.setPriority(Thread.MAX_PRIORITY); // Priority 10 [cite: 46]
        
        // Get initial state (NEW) [cite: 85, 86]
        System.out.println("Status T1 (Before start): " + t1.getState()); 
        System.out.println("Status T2 (Before start): " + t2.getState());
        
        // Start threads (RUNNABLE state) [cite: 69, 88]
        t1.start(); 
        t2.start();
        
        // Print state immediately after start (often RUNNABLE) [cite: 89]
        Thread.sleep(50); 
        System.out.println("Status T1 (After start): " + t1.getState());
        System.out.println("Status T2 (After start): " + t2.getState());
        
        // Wait for T1 to finish (to demonstrate TERMINATED) [cite: 73, 91]
        t1.join(); 
        t2.join();
        
        // Final state (TERMINATED) [cite: 92, 93]
        System.out.println("\nStatus T1 (After completion): " + t1.getState());
        System.out.println("Status T2 (After completion): " + t2.getState());
        
        System.out.println("\n--- ThreadControlDemo finished ---");
    }
}
