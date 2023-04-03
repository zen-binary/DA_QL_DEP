/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.ChiTietDep;
import Services.ISanPhamView;
import ViewThongKe.SanPhamView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SanPhamViewServie implements ISanPhamView {
  
    @Override
    public List<SanPhamView> getLstView() {
        List<SanPhamView> lst = new  ArrayList<>();
        for (ChiTietDep ctd : ctdService.getLst()) {
            SanPhamView spView = new SanPhamView();
            spView.setMa(ctd.getDep().getMa());
            spView.setTen(ctd.getDep().getTen());
            spView.setNgayTao(ctd.getNgayTao());
            spView.setTen(ctd.getDep().getTen());
            spView.setTen(ctd.getDep().getTen());
        }
        
        
        return lst;
    }

}
