/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.Dep;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IDepService {
    public List<Dep> getLst();
    
    public boolean save(Dep d);
    
    public boolean delete(Dep d);
    
    public Dep getObj(String ma);
    
    public Dep getObjById(int id);
}
