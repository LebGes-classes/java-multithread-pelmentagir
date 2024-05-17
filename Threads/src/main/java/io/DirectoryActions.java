/*
 * File: DirectoryActions.java
 * Authors:
 *   - Fairushin Tagir
 * Copyright: (c) 2023 Fairushin Tagir
 * License: СПИСОК НА ОТЧИСЛЕНИЯ
 */
package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class DirectoryActions {


    /**
     * This method copies all files in a folder
     * @param source  The folder we will copy
     * @param target  Where will we store the copied folder
     */
    public static void copyFilesDirectory(String source, String target) throws IOException {
        File folder = new File(source);
        File[] listOfFiles = folder.listFiles();
        Path destDir = Paths.get(target);

        if(listOfFiles != null){
            for(File file: listOfFiles){
                Files.copy(file.toPath(),destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
    /**
     * @param source Folder to be cleared
     * Delete all files in a folder
     */
    public static void deleteFilesDirectory(String source){
        File folder = new File(source);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null){
            for (File file: listOfFiles){
                file.delete();
            }
        }
    }

    /**
     * saving folder
     * @param name folder name
     * @param savesFolderPath path where you want to save the files
     * @param tempFolderPath the path you want to save
     * @throws IOException
     */
    public static void saveFolder(String name, String savesFolderPath, String tempFolderPath) throws IOException {
        String folderPath = Paths.get(savesFolderPath,name).toString();
        try{
            Files.createDirectory(Paths.get(folderPath));
        }catch (Exception e){
            System.out.println("Error saving folder" + e.getMessage() + "\n");
        }
        DirectoryActions.copyFilesDirectory(tempFolderPath,folderPath);
    }

}
