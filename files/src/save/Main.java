package save;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(97, 3, 15,55.4);
        GameProgress save2 = new GameProgress(45, 4, 55,567.8);
        GameProgress save3 = new GameProgress(52, 7, 89,2646.4);

        saveGame("/Users/yakimov/Games/savegames/save1.dat", save1);
        saveGame("/Users/yakimov/Games/savegames/save2.dat", save2);
        saveGame("/Users/yakimov/Games/savegames/save3.dat", save3);

        ArrayList<String> saveList = new ArrayList<>();
        saveList.add("/Users/yakimov/Games/savegames/save1.dat");
        saveList.add("/Users/yakimov/Games/savegames/save2.dat");
        saveList.add("/Users/yakimov/Games/savegames/save3.dat");

        zipFiles("/Users/yakimov/Games/savegames/save.zip", saveList);
        deleteSave(saveList);
    }

    public static void saveGame(String savePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(savePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void zipFiles(String zipFilePath, ArrayList<String> saveFileList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String saveFiles : saveFileList) {
                FileInputStream fis = new FileInputStream(saveFiles);
                ZipEntry entry = new ZipEntry(saveFiles);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void deleteSave (ArrayList<String> saveFileList) {
        for (String list : saveFileList) {
            File file = new File(list);
            if (file.delete()) {
                System.out.println("deleted " + list);
            }
        }
    }
}
