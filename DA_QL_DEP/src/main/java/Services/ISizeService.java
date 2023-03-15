/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.Size;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISizeService {
    
    public List<Size> getLst();
    
    public boolean save(Size s);
    
    public boolean delete(Size s);
    
    public Size getObj(String ma);
    
    public Size getObjById(int id);
}
