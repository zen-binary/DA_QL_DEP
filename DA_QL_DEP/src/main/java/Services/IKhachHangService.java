/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.KhachHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IKhachHangService {
    public List<KhachHang> getLst();
    
    public boolean save(KhachHang kh);
    
    public boolean delete(KhachHang kh);
    
    public KhachHang getObj(String ma);
    
    public KhachHang getObjById(int id);
}
