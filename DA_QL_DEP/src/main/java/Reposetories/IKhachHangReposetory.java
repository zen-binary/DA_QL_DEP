/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;


import Models.KhachHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IKhachHangReposetory {
    public List<KhachHang> getLstDb();
    
    public List<KhachHang> getAllByObj(String ten);
    
    public boolean save(KhachHang kh);
    
    public boolean delete(KhachHang kh);
    
    public KhachHang getObj(String ma);
    
    public KhachHang getObjById(int id);
}
