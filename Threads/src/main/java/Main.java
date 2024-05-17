
import io.XLSXActions;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String[] header = new String[]{"Workers","Business Day","Work Load","Rest Time","Efficiency"};
        String[][] df = XLSXActions.parser("gfd",0);
        for (int i = 0; i < df.length; i++) {
            for (int j = 0; j < df[0].length; j++) {
                System.out.print(df[i][j]+"\t");
            }
            System.out.println();
        }


        //System.out.println(TasksReader.tasksOpener("WeeklyPlan.csv"));
    }
}
