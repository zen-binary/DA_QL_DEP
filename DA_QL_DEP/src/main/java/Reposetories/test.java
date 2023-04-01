/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reposetories;

import Reposetories.implement.ChiTietDepReposetory;

/**
 *
 * @author Administrator
 */
public class test {
    public static void main(String[] args) {
            IChiTietDepReposetory c = new ChiTietDepReposetory();

        System.out.println("as" + c.getCount());
    }
}
