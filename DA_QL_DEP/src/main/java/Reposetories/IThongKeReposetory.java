/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;

import Models.HoaDon;
import Reposetories.implement.ChiTietDepReposetory;
import ViewThongKe.DoanhThuView;
import ViewThongKe.SanPhamView;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IThongKeReposetory {
    IChiTietDepReposetory ctdRepo = new ChiTietDepReposetory();
    
    public List<Object[]> getDoanhThuSpView(int index);

    public List<Object[]> getDoanhThu(String year, String month);
    
    public List<String> getMonthDoanhThu(String year);
    public List<String> getYearDoanhThu();
}
