/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class ImageUltil {
    public static Image KichThuoc(Image orignalImage, int width, int height) {
        Image result = orignalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return result;
    }
    
    public static ImageIcon KichThuocIcon(ImageIcon icon,int width,int height){
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}
