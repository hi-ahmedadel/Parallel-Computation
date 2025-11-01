public class Task implements Runnable {
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running task: " + taskName);
        try {
            // محاكاة لعمل يستغرق وقتاً قصيراً
            Thread.sleep(150); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " finished task: " + taskName);
    }
}
