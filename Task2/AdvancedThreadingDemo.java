public class AdvancedThreadingDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==============================================");
        System.out.println("⚙️ Advanced Threading Demo (Groups & Daemon) ⚙️");
        System.out.println("==============================================");
        
        // 1. Thread Group Demo (الشرائح 10-12)
        System.out.println("\n--- 1. Thread Group Example ---");
        // إنشاء ThreadGroup لتجميع الخيوط [cite: 99, 100]
        ThreadGroup group = new ThreadGroup("Monitor-Group"); 
        
        // إنشاء خيط (Runnable) بسيط ليتم تمريره للمجموعة
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " running in " +
                               Thread.currentThread().getThreadGroup().getName());
            try {
                Thread.sleep(100); // للحفاظ على الخيط نشطاً
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // إنشاء خيطين وتعيينهما للمجموعة
        Thread tA = new Thread(group, task, "Group-Worker-A");
        Thread tB = new Thread(group, task, "Group-Worker-B");
        
        tA.start();
        tB.start();
        
        Thread.sleep(50); // الانتظار لبدء تشغيل الخيوط
        
        // استخدام activeCount() و list() للإدارة والمراقبة [cite: 135, 136]
        System.out.println("Active Threads in Group: " + group.activeCount());
        group.list(); 

        // الانتظار لانتهاء خيوط المجموعة
        tA.join(); 
        tB.join();

        // 2. Daemon vs User Thread Demo (الشرائح 13-16)
        System.out.println("\n--- 2. Daemon vs User Thread Example ---");
        
        // A. Daemon Thread (خلفية)
        Thread daemonThread = new Thread(() -> {
            try {
                // حلقة لا نهائية لخيط الخلفية [cite: 185]
                while (true) {
                    System.out.println("[Daemon] Performing background service...");
                    Thread.sleep(500); 
                }
            } catch (InterruptedException e) {
                System.out.println("Daemon interrupted");
            }
        }, "GC-Sim-Daemon");
        
        // يتم وضع علامة على هذا الخيط بأنه Daemon [cite: 193]
        daemonThread.setDaemon(true); 
        daemonThread.start();
        
        // B. User Thread (المهمة الأساسية)
        Thread userThread = new Thread(() -> {
            System.out.println("[User] Core application task started: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // يستغرق وقتاً أطول 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[User] Core application task finished.");
        }, "Main-User-Task"); // خيط مستخدم افتراضي (Non-Daemon) [cite: 141]

        userThread.start();
        
        // انتظار انتهاء خيط المستخدم 
        userThread.join(); 
        
        System.out.println("Main thread ends: " + Thread.currentThread().getName()); 
        // الـ JVM ستنتظر حتى ينتهي "Main-User-Task" (خيط مستخدم)[cite: 152].
        // بعد ذلك، ستغلق وتتجاهل "GC-Sim-Daemon"[cite: 153].
        
        System.out.println("\n--- AdvancedThreadingDemo finished ---");
    }
}
