import java.text.ParseException;
import java.util.Random;

class FIFO {
    static void getWaitingTime(int processes[], int n,
            long bt[], long wt[]) {
        wt[0] = 0;
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    static void getTurnAroundTime(int processes[], int n,
            long bt[], long wt[], long tat[]) {

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }
    static void getAvgTime(int processes[], int n, long bt[]) {
        long wt[] = new long[n];
        long tat[] = new long[n];
        long total_wt = 0, total_tat = 0;
        getWaitingTime(processes, n, bt, wt);
        getTurnAroundTime(processes, n, bt, wt, tat);
        System.out.printf("Processes \t Burst time \t\t Waiting"
                    +" time \t\t Turn around time\n");
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.printf("%d ", (i + 1));
            System.out.printf("\t\t %d ", bt[i]);
            System.out.printf("\t     %d", wt[i]);
            System.out.printf("\t     %d\n", tat[i]);
        }
        long s = (long)total_wt /(long) n;
        long t = total_tat / n;
        System.out.printf("Average waiting time = " +s + " cycles");
        System.out.printf("\n");
        System.out.printf("Average turnaround time = " + t + " cycles");
    }
    public static void main(String[] args) throws ParseException {
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

        getAvgTime(processes, n, burst_time);

    }
}
