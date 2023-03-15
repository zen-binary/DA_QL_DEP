/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.ChiTietDep;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietDepService {
    public List<ChiTietDep> getLst();
    
    public boolean save(ChiTietDep ctd);
    
    public boolean delete(ChiTietDep ctd);
    
    public ChiTietDep getObj(String ma);
    
    public ChiTietDep getObjById(int id);
}
