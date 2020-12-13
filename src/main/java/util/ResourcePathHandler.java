package util;

import java.io.File;
import java.util.StringJoiner;

public class ResourcePathHandler {
    private static final String RESOURCES_BASE_PATH = createResourcesPath();
    private static final String RESOURCES_IMG_DIR = "images";

    private static String createResourcesPath() {
        StringJoiner resPath = new StringJoiner(File.separator);
        resPath.add("src");
        resPath.add("main");
        resPath.add("resources");
        return resPath.toString();
    }

    public static String getImagePath(String fileName) {
        StringJoiner imgPath = new StringJoiner(File.separator);
        imgPath.add(RESOURCES_BASE_PATH);
        imgPath.add(RESOURCES_IMG_DIR);
        imgPath.add(fileName);
        return imgPath.toString();
    }
}
