// Q. Write a program to display all running threads in Java.



package Thursday_LAB_11_01_24;

public class DisplayRunningThreads 
{
	public static void main(String[] args) {
        // Get all running threads
        Thread[] threads = getAllThreads();

        // Display information about each thread
        for (Thread thread : threads) {
            System.out.println("Thread Name: " + thread.getName() +
                    "\n\tID: " + thread.getId() +
                    "\n\tState: " + thread.getState() +
                    "\n\tPriority: " + thread.getPriority() +
                    "\n\tDaemon: " + thread.isDaemon() +
                    "\n------------------------");
        }
    }

    private static Thread[] getAllThreads() {
        // Get all threads and their stack traces
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }

        int threadCount = threadGroup.activeCount();
        Thread[] threads = new Thread[threadCount];
        threadGroup.enumerate(threads);

        return threads;
    }
}
