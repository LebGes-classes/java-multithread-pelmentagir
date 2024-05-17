import io.XLSXActions;

import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Worker extends TaskProcessor implements Runnable{
    private String name;

    int day = 0;
    int dailyHour = 8;
    int workLoad = 0;
    int restTime = 0;
    double efficiency = 0;

    @Override
    public void run() {
            while (dailyHour > 0) {
                    if (tasks.isEmpty()) {
                        threadSleep(2);
                        saveDate();
                    }
                    int index = new Random().nextInt(0, tasks.size()-1);
                    String taskName = tasks.get(index)[0];
                    int taskTime = Integer.parseInt(tasks.get(index)[1]);
                    tasks.remove(index);
                    if (dailyHour >= taskTime) {
                        System.out.println(getWorkerName() + " взял задачу: " + taskName);
                        threadSleep(taskTime);
                        System.out.println(getWorkerName() + " выполнил задачу: " + taskName);
                        dailyHour -= taskTime;
                        workLoad+=taskTime;
                        if (dailyHour > 0) {
                            switch (new Random().nextInt(10)) {
                                case 0 -> {
                                    dailyHour -= 1;
                                    restTime +=1;
                                    System.out.println(getWorkerName() +
                                            " устал от работы, выпью кофе");
                                    threadSleep(1);
                                }
                                case 1 -> {
                                    if (dailyHour > 1) {
                                        dailyHour -= 2;
                                        restTime += 2;
                                        System.out.println(getWorkerName() + " устал от работы, пойду пообедаю");
                                        threadSleep(2);
                                    } else {
                                        dailyHour = 0;
                                        restTime += 1;
                                        day++;
                                        System.out.println(getWorkerName() + " пойду домой, остался всего час");
                                    }
                                }
                            }
                        }
                    } else {
                        int partOfTaskTime = taskTime - dailyHour;
                        workLoad += dailyHour;
                        threadSleep(2);
                        saveDate();
                        System.out.println(getWorkerName() + " взял часть от задачи: " + taskName);
                        threadSleep(dailyHour);
                        System.out.println(getWorkerName() + " выполнил часть задачи: " + taskName);
                        tasks.add(new String[]{taskName, String.valueOf(partOfTaskTime)});
                        dailyHour = 0;
                        day++;
                    }
            }
            threadSleep(2);
            saveDate();
    }
    private static void threadSleep (
            int n
    ) {
        try {
            Thread.sleep(n * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWorkerName() {
        return name;
    }

    @Override
    public String toString() {
        return "Worker{ " + getWorkerName() + " }";
    }

    public Worker(String name) {
        this.name = name;
    }
    public void saveDate(){
        String[] info = new String[5];
        info[0] = name; // имя сотрудника
        info[1] = "8"; // рабочее время
        info[2] = String.valueOf(workLoad); // сколько отработал
        info[3] = String.valueOf(restTime); // сколько отдохнул
        efficiency = (double) workLoad /8;
        info[4] = String.valueOf(efficiency);

        XLSXActions.writeLineFile(info,"salfetka.xlsx", header,"Day " + day);
    }
}
