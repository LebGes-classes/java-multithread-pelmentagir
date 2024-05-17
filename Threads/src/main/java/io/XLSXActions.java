package io;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XLSXActions {
    /**
     * Writes data to XLSX
     * @param filePath file name
     * @param header initial title
     * @param nameSheet sheet name
     */
    public static <E> void writeFile(E[][] tableData, String filePath, String[] header, String nameSheet) {
        FileOutputStream fileOut = null;
        Workbook workbook = null;
        try {
            String pathFile = Paths.get("./" + filePath).toString();
            if(!Files.exists(Paths.get(pathFile))){// Создаем новую книгу Excel
                workbook = new XSSFWorkbook();
            }else{//Переходим в существующую книгу Excel
                FileInputStream fileInputStream = new FileInputStream(filePath);
                workbook = new XSSFWorkbook(fileInputStream);
            }
            //Получаю лист
            Sheet sheet = workbook.getSheet(nameSheet);
            if(sheet == null){ // если листа нету то getSheet() вернет null
                sheet = workbook.createSheet(nameSheet);
            }
            Row row = sheet.createRow(0);
            for (int j = 0; j < header.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(header[j]);
            }
            for (int i = 0; i < tableData.length; i++) {
                Row row1 = sheet.createRow(i+1);
                for (int j = 0; j < tableData[0].length; j++) {
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(tableData[i][j].toString());
                }
            }

            // Сохраняем книгу Excel в файл
            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static <E> void writeLineFile(E[] tableData, String filePath, String[] header, String nameSheet){
        FileOutputStream fileOut = null;
        Workbook workbook = null;
        try {
            //Переходим в существующую книгу Excel
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);

            //Получаю лист
            Sheet sheet = workbook.getSheet(nameSheet);
            if(sheet == null){ // если листа нету то getSheet() вернет null
                sheet = workbook.createSheet(nameSheet);
                Row row = sheet.createRow(0);
                for (int j = 0; j < header.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(header[j]);
                }
            }

            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
            for (int i = 0; i < tableData.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(tableData[i].toString());
            }
            // Сохраняем книгу Excel в файл
            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Parsing xlsx file
     * @param filePath The path to the XLSX file
     * @param sheetNumber sheetPosition The position of the sheet to read
     * @return A 2D String array representing the contents of the XLSX file
     */
    public static String[][] parser(String filePath, int sheetNumber) {
        String[][] data = null;
        try {
            // Открываем файл
            FileInputStream file = new FileInputStream(filePath);

            // Создаем объект для работы с XLSX файлом
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Получаем произвольный лист
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber-1);

            // Получаем кол-во строк и столбов
            int numRows = sheet.getPhysicalNumberOfRows();
            int numCols = sheet.getRow(0).getPhysicalNumberOfCells();
            for (Row row : sheet) {
                numCols = Math.max(numCols, row.getPhysicalNumberOfCells());
            }

            // Создаем таблицу для возвращения
            data = new String[numRows][numCols];

            // Перебираем строки в листе
            for (int i = 0; i < numRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    if(cell != null){
                        data[i][j] = cell.toString();
                    }else{
                        data[i][j] = "";
                    }
                }
            }
            // Закрываем файл
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static int getSheetNum(String filePath){
        int numSheet = 0;
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            numSheet = workbook.getNumberOfSheets();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numSheet;
    }
}