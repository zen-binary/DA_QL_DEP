/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.Nsx;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface INsxService {
    public List<Nsx> getLst();
    
    public boolean save(Nsx nsx);
    
    public boolean delete(Nsx nsx);
    
    public Nsx getObj(String ma);
    
    public Nsx getObjById(int id);
}
