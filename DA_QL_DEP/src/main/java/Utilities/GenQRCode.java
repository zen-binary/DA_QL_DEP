/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
public class GenQRCode {
    
    public static void GenQRCode(String path, String data){
        try {
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(data, BarcodeFormat.QR_CODE, 500, 500);

            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    public static void main(String[] args) {
        try {
            String data = "http://www.infybuzz.com";
            String path = "./QR/infybuzz.jpg";

            BitMatrix matrix = new MultiFormatWriter()
                    .encode(data, BarcodeFormat.QR_CODE, 500, 500);

            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (Exception e) {
        }

    }
}
