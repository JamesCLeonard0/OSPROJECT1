import java.util.Random;

class Process 
{ 
    int pid;  
    long bt;  
    long art;  
      
    public Process(int id, long l, long m) 
    { 
        this.pid = id; 
        this.bt = l; 
        this.art = m; 
    } 
} 
  
public class SJF  
{ 
    static void getWaitingTime(Process proc[], int n, 
                                     long wt[]) 
    { 
        long rt[] = new long[n]; 
       
 
        for (int i = 0; i < n; i++) 
            rt[i] = proc[i].bt; 
       
        int complete = 0, t = 0;
		long minm = Long.MAX_VALUE; 
        int shortest = 0, finish_time; 
        boolean check = false; 
        while (complete != n) { 
       
      
            for (int j = 0; j < n; j++)  
            { 
                if ((proc[j].art <= t) && 
                  (rt[j] < minm) && rt[j] > 0) { 
                    minm = rt[j]; 
                    shortest = j; 
                    check = true; 
                } 
            } 
       
            if (check == false) { 
                t++; 
                continue; 
            } 
       
          
            rt[shortest]--; 
       
         
            minm = rt[shortest]; 
            if (minm == 0) 
                minm = Long.MAX_VALUE; 
       
       
            if (rt[shortest] == 0) { 
       
               
                complete++; 
                check = false; 
       
              
                finish_time = t + 1; 
       
              
                wt[shortest] = finish_time - 
                             proc[shortest].bt - 
                             proc[shortest].art; 
       
                if (wt[shortest] < 0) 
                    wt[shortest] = 0; 
            } 
      
            t++; 
        } 
    } 
       

    static void getTurnAroundTime(Process proc[], int n, 
                            long wt[], long tat[]) 
    { 
      
        for (int i = 0; i < n; i++) 
            tat[i] = proc[i].bt + wt[i]; 
    } 
       

    static void getAvgTime(Process proc[], int n) 
    { 
        long[] wt = new long[n];
		long[] tat = new long[n]; 
        long  total_wt = 0, total_tat = 0; 
       
    
        getWaitingTime(proc, n, wt); 
       
       
        getTurnAroundTime(proc, n, wt, tat); 
       
    
        System.out.println("Processes " + 
                           " Burst time " + 
                           " Waiting time " + 
                           " Turn around time"); 
       
      
        for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + tat[i]; 
            System.out.println(" " + proc[i].pid + " "
                             + proc[i].bt + " " + wt[i] 
                             + " " + tat[i]); 
        } 
       
        System.out.println("Average waiting time = " + 
                          (long)total_wt / (long)n); 
        System.out.println("Average turnaround time = " + 
                           (long)total_tat / (long)n); 
    } 
      

    public static void main(String[] args) 
    { 
    	
         Random rand = new Random();
         long leftLimit = (long) Math.pow(10, 7);
         long rightLimit = (long) Math.pow(10, 13);
         //System.out.println(leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
         Process proc[] = new Process[250];
         for(int i=0; i<proc.length;i++) {
        	proc[i] = new Process(i+1,leftLimit + (long) (Math.random() * (rightLimit - leftLimit)),0);
        	
         }
     
         getAvgTime(proc, proc.length); 
    } 
} 