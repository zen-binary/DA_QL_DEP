/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.LoaiDep;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ILoaiDepService {
    public List<LoaiDep> getLst();
    
    public boolean save(LoaiDep ld);
    
    public boolean delete(LoaiDep ld);
    
    public LoaiDep getObj(String ma);
    
    public LoaiDep getObjById(int id);
}
