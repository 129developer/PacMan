package mypacman.utils;

import java.net.URL;
import java.util.Random;

/**
 *
 * @author Mukil
 */
public class genUtils {

    public static URL getResource(String filename) {
        return genUtils.class.getClassLoader().getResource("mypacman/resources/images/" + filename);
    }

    public static String getBlankResource(String filename) {
        return getParent(getParent(genUtils.class.getClassLoader().getResource(filename).getPath()));
    }

    public static URL getParentResource(String filename) {
        return genUtils.class.getResource("mypacman/resources/images/" + filename);
    }

    private static String getParent(String resourcePath) {
        int index = resourcePath.lastIndexOf('/');
        if (index > 0) {
            return resourcePath.substring(0, index);
        }
        return "/";
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
