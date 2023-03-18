/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;


import Models.HoaDon;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonReposetory {
    public List<HoaDon> getLstDb();
//    public List<HoaDon> getAllByMa();
    
    public boolean save(HoaDon hd);
    
    public boolean delete(HoaDon hd);
    
    public HoaDon getObj(String ma);
    
    public HoaDon getObjById(int id);
}
