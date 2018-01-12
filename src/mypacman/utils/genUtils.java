/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.utils;

import java.net.URL;

/**
 *
 * @author bayasys
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

}
