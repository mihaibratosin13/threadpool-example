import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolApp
{   
    public static void main(String args[]){
        // if there are not enough arguments provided return an error message
        if(args.length < 2){
            ThreadPoolApp.error();
        }
        
        try{
            // try to read number of jobs provided as the first argument
            // and the number of threads as a second argument
            int numberOfJobs = Integer.parseInt(args[0]);
            int numberOfThreads = Integer.parseInt(args[1]);
            
            // if the numbers given are smaller than one return an error message
            if((numberOfJobs<1) || (numberOfThreads <1))
                ThreadPoolApp.error();
            
            // initialise the thread pool with the provided number of threads
            ExecutorService pool = Executors.newFixedThreadPool (numberOfThreads);
            
            // create an array of jobs with the provided number of jobs as its length
            Job[] jobs = new Job[numberOfJobs];
            for(int i = 0; i<numberOfJobs; i++){
                // initialise every job with the jobNumber provided
                jobs[i] = new Job(i, i, i+1);
                pool.execute(jobs[i]);//executes the command at some future time
            }
            
            // Shutdown : previously submitted tasks are executed, but no new
            // tasks will be accepted.
            pool.shutdown();
            System.out.println("Last line " + Thread.currentThread().getName());
        }
        catch(NumberFormatException e){
            ThreadPoolApp.error();
        }
    }
    
    /**
     * Defines the error message printed when one of the conditions is broken
     */
    private static void error(){
        System.out.println("ThreadPoolApp must be run with two positive valued integer arguments." +
            " The first detailing the number of jobs and the second the number of processing threads"+
            " in the pool.");
        System.exit(0);
    }
    
}
