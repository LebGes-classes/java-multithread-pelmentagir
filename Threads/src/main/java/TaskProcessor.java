import io.CSVActions;
import io.XLSXActions;
import java.util.*;

public class TaskProcessor {
    public static String[] header = {
            "Worker",
            "Business Day",
            "Work Load",
            "Rest Time",
            "Efficiency"
    };
    private static final String FILE_PATH_CSV = "WeeklyPlan.csv";
    private static final String FILE_PATH_XLSX = "рэп_про_любовь.xlsx";
    public static ArrayList<String[]> tasks = CSVActions.parser(FILE_PATH_CSV);
    public static HashSet<Worker> workersOnPlace = new HashSet<>();
    public static int countWorkers = 0;

    public static void main(String[] args) {
        String[][] table = XLSXActions.parser("salfetka.xlsx",1);
        for (int i = 1; i < table.length; i++) {
            workersOnPlace.add(new Worker(table[i][0]));
        }
        List<Thread> threadList = new ArrayList<>();


        for(Worker workable : workersOnPlace){
            Thread thread = new Thread(workable);
            thread.start();
            threadList.add(thread);
        }
//       threadList.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                 throw new RuntimeException(e);
//            }
//        });
    }
}



