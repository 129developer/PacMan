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
    public static URL getResource(String filename){
    return genUtils.class.getClassLoader().getResource("mypacman/resources/images/"+filename);
    }
    
}
