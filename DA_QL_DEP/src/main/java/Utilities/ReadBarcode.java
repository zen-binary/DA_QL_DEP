/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class ReadBarcode {
    public static void main(String[] args) {
        try {
			
			String path = "./QR/barcode.jpg";
			
			BufferedImage bf = ImageIO.read(new FileInputStream(path));
			
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
					new BufferedImageLuminanceSource(bf)));
			
			Result result = new MultiFormatReader().decode(bitmap);
			
			System.out.println(result.getText());
			
		} catch(Exception e) {
			System.out.println("Error while reading barcode " + e.getMessage());
		}
    }
}
