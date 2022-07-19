package installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static String logFile = "/Users/yakimov/Games/temp/temp.txt";
    static StringBuilder logText = new StringBuilder();

    public static void main(String[] args) {
        createNewFolder("/Users/yakimov/Games/src");
        createNewFolder("/Users/yakimov/Games/src/main");
        createNewFolder("/Users/yakimov/Games/src/test");
        createNewFolder("/Users/yakimov/Games/res");
        createNewFolder("/Users/yakimov/Games/res/drawables");
        createNewFolder("/Users/yakimov/Games/res/vectors");
        createNewFolder("/Users/yakimov/Games/res/icons");
        createNewFolder("/Users/yakimov/Games/savegames");
        createNewFolder("/Users/yakimov/Games/temp");
        createNewFile("/Users/yakimov/Games/src/main/Main.java");
        createNewFile("/Users/yakimov/Games/src/main/Utils.java");
        createNewFile(logFile);
        logSaveWrite();
    }

    public static void createNewFolder(String folderName) {
        File dir = new File(folderName);
        logSave(folderName, dir.mkdir());
    }

    public static void createNewFile(String fileName) {
        File file = new File(fileName);
        try {
            logSave(fileName, file.createNewFile());
        } catch (IOException e) {
            logSave(fileName, false);
        }
    }

    public static void logSave(String fileOrFolderName, boolean createFileOrFolder) {
        if (createFileOrFolder) {
            logText.append(fileOrFolderName + " is created\n");
        } else {
            logText.append(fileOrFolderName + " ************** isn't created\n");
        }
    }

    public static void logSaveWrite() {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logText.toString());
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
