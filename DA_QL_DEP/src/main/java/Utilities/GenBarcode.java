/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
public class GenBarcode {

    public static void GenBarcode(String path, String text) {
        try {
            Code128Writer writer = new Code128Writer();
            BitMatrix matrix = writer.encode(text, BarcodeFormat.CODE_128, 500, 200);

            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));

            System.out.println("Barcode created...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while creating barcode");
        }
    }

    public static void main(String[] args) {
        try {

            String text = "SP123";
            String path = "./QR/Sp123.jpg";

            Code128Writer writer = new Code128Writer();
            BitMatrix matrix = writer.encode(text, BarcodeFormat.CODE_128, 500, 200);

            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));

            System.out.println("Barcode created...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while creating barcode");
        }
    }
}
