/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories;

import Reposetories.implement.ChiTietDepReposetory;
import Reposetories.implement.HoaDonReposetory;

/**
 *
 * @author Administrator
 */
public class test {
    public static void main(String[] args) {
           IHoaDonReposetory hd = new HoaDonReposetory();

        System.out.println("as" + hd.getCountHoaDon("year", 1));
    }
}
