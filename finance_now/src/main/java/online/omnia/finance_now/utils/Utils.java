package online.omnia.finance_now.utils;

import java.io.File;
import java.util.*;

/**
 * Created by lollipop on 04.07.2017.
 */
public class Utils {
    public static List<Class> getFilesInPackage(Class clazz) {
        List<String> paths = getFiles(clazz);
        List<Class> classes = new ArrayList<>();
        Class currentClass;
        Class superClass;
        for (String currentPath : paths) {
            try {
                currentClass = Class.forName(currentPath);
                superClass = currentClass.getSuperclass();
                if (clazz == superClass) classes.add(currentClass);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return classes;
    }

    private static List<String> getFiles(Class clazz) {
        Queue<File> filePaths = new ArrayDeque<>();
        List<String> paths = new ArrayList<>();

        String path = clazz.getResource("").getPath();
        path = path.substring(1, path.lastIndexOf("/"));
        filePaths.add(new File(path));
        File currentFile;
        String filePath;
        int beginIndex;
        while (!filePaths.isEmpty()) {
            currentFile = filePaths.poll();
            if (currentFile.isDirectory()) {
                Collections.addAll(filePaths, currentFile.listFiles());
            } else if (currentFile.getAbsolutePath().endsWith(".class")) {
                filePath = currentFile.getAbsolutePath();
                beginIndex = filePath.indexOf("online\\omnia\\finance_now");
                filePath = filePath.substring(beginIndex,
                        filePath.length() - 6).replaceAll("\\\\", ".");
                paths.add(filePath);
            }
        }
        return paths;
    }

}
