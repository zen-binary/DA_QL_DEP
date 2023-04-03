/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Services.implement.ThongKeService;
import ViewThongKe.SanPhamView;

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
        ThongKeService tk = new ThongKeService();
        for (SanPhamView sanPhamView : tk.getAllSP()) {
            System.out.println("sp: " + sanPhamView.toString());
        }

    }
}
