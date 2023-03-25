/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.ChucVu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChucVuService {

    public List<ChucVu> getLst();

    public boolean save(ChucVu cv);

    public boolean delete(ChucVu cv);

    public ChucVu getObj(String ma);

    public ChucVu getObjByTen(String ten);

    public ChucVu getObjById(int id);
}
