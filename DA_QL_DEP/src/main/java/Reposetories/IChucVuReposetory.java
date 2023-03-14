/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;

import Models.NguoiDungc;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChucVuReposetory {
    public List<NguoiDungc> getLstDb();
    
    public boolean save(NguoiDungc cv);
    
    public boolean delete(NguoiDungc cv);
    
    public NguoiDungc getObj(String ma);
    
    public NguoiDungc getObjById(int id);
}
