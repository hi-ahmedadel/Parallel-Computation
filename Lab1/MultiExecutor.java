import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
    
    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

  
    public void executeAll() {
        System.out.println("\n--- LAB EXERCISE: MULTIEXECUTOR ---");
        System.out.println("Starting execution of " + tasks.size() + " tasks concurrently...");

        List<Thread> threads = new ArrayList<>();
        
        for (Runnable task : tasks) {
       
            Thread thread = new Thread(task); 
            threads.add(thread);
          
            thread.start(); 
        }

        for (Thread thread : threads) {
            try {
                thread.join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("All tasks finished execution.");
    }
    
    public static void main(String[] args) {
      
        List<Runnable> tasks = List.of(
            new Task("Initialization Task"),
            new Task("Core Logic Task"),
            new Task("Cleanup Task")
        );

    
        MultiExecutor executor = new MultiExecutor(tasks);
        
       
        executor.executeAll();
    }
}
