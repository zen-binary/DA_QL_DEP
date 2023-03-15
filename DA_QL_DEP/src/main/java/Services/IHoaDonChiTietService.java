/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonChiTietService {
    public List<HoaDonChiTiet> getLst();
    
    public boolean save(HoaDonChiTiet hdCt);
    
    public boolean delete(HoaDonChiTiet hdCt);
    
    public HoaDonChiTiet getObj(String ma);
    
    public HoaDonChiTiet getObjById(int id);
}
