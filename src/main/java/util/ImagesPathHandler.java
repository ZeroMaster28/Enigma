package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ImagesPathHandler {
    private static final String IMG_DIR_PATH = findImagesPath();
    private static final String RESOURCES_IMG_DIR = "images";

    /**
     * Finding <code>images</code> directory absolute path by the directory name
     * @return images directory path
     */
    private static String findImagesPath() {
        try {
            Optional<Path> imgDirPath = Files.walk(new File(".").toPath())
                    .filter(file -> file.getFileName().endsWith(RESOURCES_IMG_DIR))
                    .findAny();
            if(imgDirPath.isPresent()) {
                return imgDirPath.get().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getImagePath(String fileName) {
        return IMG_DIR_PATH + File.separator + fileName;
    }
}
