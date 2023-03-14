/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;


import Models.LoaiDep;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ILoaiDepReposetory {
    public List<LoaiDep> getLstDb();
    
    public boolean save(LoaiDep ld);
    
    public boolean delete(LoaiDep ld);
    
    public LoaiDep getObj(String ma);
    
    public LoaiDep getObjById(int id);
}
