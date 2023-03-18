/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.HoaDon;
import Reposetories.IHoaDonReposetory;
import Reposetories.implement.HoaDonReposetory;
import Services.IHoaDonService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService implements IHoaDonService {

    IHoaDonReposetory hdRepo;

    public HoaDonService() {
        hdRepo = new HoaDonReposetory();
    }

    @Override
    public List<HoaDon> getLst() {
        return this.hdRepo.getLstDb();
    }

    @Override
    public boolean save(HoaDon hd) {
        return this.hdRepo.save(hd);
    }

    @Override
    public boolean delete(HoaDon hd) {
        return this.hdRepo.delete(hd);
    }

    @Override
    public HoaDon getObj(String ma) {
        return this.hdRepo.getObj(ma);
    }

    @Override
    public HoaDon getObjById(int id) {
        return this.hdRepo.getObjById(id);
    }

}
