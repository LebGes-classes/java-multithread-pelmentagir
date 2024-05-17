package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProgrammingLanguages {
    /**
     * Running a Python file
     * @param pathVenvScriptFile a file that runs through a virtual shell
     * @param pathFile path of the executable file
     */
    public static void runningPython(String pathVenvScriptFile, String pathFile) {
        try {
            // Создание процесса для запуска Python-скрипта
            Process process = new ProcessBuilder(pathVenvScriptFile, pathFile).start();
            //Чтение вывода
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            // Ожидание завершения процесса
            int exitCode2 = process.waitFor();
            // Вывод результата
            if (exitCode2 == 0) {
                System.out.println("Python script executed successfully:");
            } else {
                System.out.println("Python script execution failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
