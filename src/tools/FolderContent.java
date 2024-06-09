package tools;

import java.io.File;

public class FolderContent {

    public static String defaultDirectory = System.getProperty("user.dir") + "/resources/maps"; // Vordefinierter Ordnerpfad

    public static int countFolderContents() {
        File folder = new File(defaultDirectory);

        if (folder.exists() && folder.isDirectory()) {
            File[] contents = folder.listFiles();
            if (contents != null) {
                int fileCount = 0;
                int directoryCount = 0;

                for (File content : contents) {
                    if (content.isFile()) {
                        fileCount++;
                    } else if (content.isDirectory()) {
                        directoryCount++;
                    }
                }
                return fileCount + directoryCount;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
}
