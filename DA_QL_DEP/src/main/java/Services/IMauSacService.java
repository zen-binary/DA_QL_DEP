/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.MauSac;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMauSacService {
    public List<MauSac> getLst();
    
    public boolean save(MauSac ms);
    
    public boolean delete(MauSac ms);
    
    public MauSac getObj(String ma);
    
    public MauSac getObjById(int id);
}
