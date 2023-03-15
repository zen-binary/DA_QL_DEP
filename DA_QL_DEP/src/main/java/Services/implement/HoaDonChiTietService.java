/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.HoaDonChiTiet;
import Reposetories.IHoaDonChiTietReposetory;
import Reposetories.implement.HoaDonChiTietReposetory;
import Services.IHoaDonChiTietService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService implements IHoaDonChiTietService {

    IHoaDonChiTietReposetory hdCtRepo;

    public HoaDonChiTietService() {
        hdCtRepo = new HoaDonChiTietReposetory();
    }

    @Override
    public List<HoaDonChiTiet> getLst() {
        return this.hdCtRepo.getLstDb();
    }

    @Override
    public boolean save(HoaDonChiTiet hdCt) {
        return this.hdCtRepo.save(hdCt);
    }

    @Override
    public boolean delete(HoaDonChiTiet hdCt) {
        return this.hdCtRepo.delete(hdCt);
    }

    @Override
    public HoaDonChiTiet getObj(String ma) {
        return this.hdCtRepo.getObj(ma);
    }

    @Override
    public HoaDonChiTiet getObjById(int id) {
        return this.hdCtRepo.getObjById(id);
    }

}
