//Q. Write a program to set the priorities of the thread.


package Thursday_LAB_11_01_24;

import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityExample 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Get user input for lowercase range
        System.out.print("Enter start character for lowercase letters: ");
        char lowercaseStart = scanner.next().charAt(0);
        
        System.out.print("Enter end character for lowercase letters: ");
        char lowercaseEnd = scanner.next().charAt(0);

        // Get user input for uppercase range
        System.out.print("Enter start character for uppercase letters: ");
        char uppercaseStart = scanner.next().charAt(0);
        
        System.out.print("Enter end character for uppercase letters: ");
        char uppercaseEnd = scanner.next().charAt(0);

        // Get user input for number range (set the range between 1 and 20)
        int numberStart, numberEnd;
        do {
            System.out.print("Enter start number (between 1 and 20): ");
            numberStart = scanner.nextInt();
            System.out.print("Enter end number (between 1 and 20): ");
            numberEnd = scanner.nextInt();
        } 
        while (numberStart < 1 || numberStart > 20 || numberEnd < 1 || numberEnd > 20);

        // Create a priority queue to manage task priorities
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(3, (t1, t2) ->
                Integer.compare(t2.getPriority(), t1.getPriority())); // Higher priority first

        // Create tasks for different operations based on user input
        Task lowercaseTask = new Task("Lowercase Task", lowercaseStart, lowercaseEnd, 1);
        Task uppercaseTask = new Task("Uppercase Task", uppercaseStart, uppercaseEnd, 2);
        Task numberTask = new Task("Number Task", numberStart, numberEnd, 3);

        // Add tasks to the priority queue
        taskQueue.add(lowercaseTask);
        taskQueue.add(uppercaseTask);
        taskQueue.add(numberTask);

        // Start the tasks based on priority and join them
        while (!taskQueue.isEmpty()) 
        {
            Task nextTask = taskQueue.poll();
            Thread thread = new Thread(nextTask, nextTask.getTaskName());
            thread.start();

            try 
            {
                thread.join(); // Wait for the thread to complete before moving to the next one
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }

        // Close the scanner
        scanner.close();
    }

    static class Task implements Runnable 
    {
        private final String taskName;
        private final int start;
        private final int end;
        private final int priority;

        public Task(String taskName, char start, char end, int priority) 
        {
            this.taskName = taskName;
            this.start = start;
            this.end = end;
            this.priority = priority;
        }

        public Task(String taskName, int start, int end, int priority) 
        {
            this.taskName = taskName;
            this.start = start;
            this.end = end;
            this.priority = priority;
        }

        public int getPriority() 
        {
            return priority;
        }

        public String getTaskName()
        {
            return taskName;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) 
            {
                if (Character.isLetter(start)) 
                {
                    System.out.println(Thread.currentThread().getName() + " - " + taskName + ": " + (char) i);
                } else 
                {
                    System.out.println(Thread.currentThread().getName() + " - " + taskName + ": " + i);
                }
                try 
                {
                    Thread.sleep(500); // Sleep for 0.5 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
