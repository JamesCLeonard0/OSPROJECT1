import java.util.Random;

public class RR
{
    static void getWaitingTime(int processes[], int n,
                long[] burst_time, long[] wt, long quantum)
    {
        long rem_bt[] = new long[n];
        for (int i = 0 ; i < n ; i++)
            rem_bt[i] = burst_time[i];
    
        long t = 0; 
        while(true)
        {
            boolean finish = true;
    
            for (int i = 0 ; i < n; i++)
            {
                if (rem_bt[i] > 0)
                {
                    finish = false; 
    
                    if (rem_bt[i] > quantum)
                    {
                        t += quantum;
    

                        rem_bt[i] -= quantum;
                    }

                    else
                    {
                        t = t + rem_bt[i];
                        wt[i] = t - burst_time[i];
                        rem_bt[i] = 0;
                    }
                }
            }
            if (finish == true)
            break;
        }
    }
    static void getTurnAroundTime(int processes[], int n,
                            long[] burst_time, long wt[], long tat[])
    {
        for (int i = 0; i < n ; i++)
            tat[i] = burst_time[i] + wt[i];
    }    
    static void getavgTime(int processes[], int n, long[] burst_time,
                                        long q)
    {
        long wt[] = new long[n]; 
        long tat[] = new long[n];
        long total_wt = 0, total_tat = 0;
        getWaitingTime(processes, n, burst_time, wt, q);
        getTurnAroundTime(processes, n, burst_time, wt, tat);
        System.out.println("Processes " + " Burst time " +
                    " Waiting time " + " Turnaround time");
    
        for (int i=0; i<n; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i+1) + "\t\t" + burst_time[i] +"\t " +
                            wt[i] +"\t\t " + tat[i]);
        }
    
        System.out.println("Average waiting time = " +
                        (long)total_wt / (long)n);
        System.out.println("Average turnaround time = " +
                        (long)total_tat / (long)n);
    }
    
    public static void main(String[] args)
    {
    	int processes[] = new int[250];
        int n = processes.length;
        long burst_time[] = new long[250];
        Random rand = new Random();
        long leftLimit = (long) Math.pow(10, 7);
        long rightLimit = (long) Math.pow(10, 13);
        //long k = (long) Math.pow(10, 13);
        //System.out.print(k);
        for(int i = 0; i<burst_time.length;i++) {
         
            burst_time[i] = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }
        
        long q = 648720322225585L;
        getavgTime(processes, n, burst_time, q);
    }
}
