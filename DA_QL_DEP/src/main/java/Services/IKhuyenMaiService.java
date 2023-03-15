/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.KhuyenMai;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IKhuyenMaiService {
    public List<KhuyenMai> getLst();
    
    public boolean save(KhuyenMai km);
    
    public boolean delete(KhuyenMai km);
    
    public KhuyenMai getObj(String ma);
    
    public KhuyenMai getObjById(int id);
}
