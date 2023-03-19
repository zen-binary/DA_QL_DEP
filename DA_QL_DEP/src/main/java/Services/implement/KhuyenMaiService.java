/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.KhuyenMai;
import Reposetories.IKhuyenMaiReposetory;
import Reposetories.implement.KhuyenMaiReposetory;
import Services.IKhuyenMaiService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhuyenMaiService implements IKhuyenMaiService {

    IKhuyenMaiReposetory kmRepo;

    public KhuyenMaiService() {
        kmRepo = new KhuyenMaiReposetory();
    }

    @Override
    public List<KhuyenMai> getLst() {
        return this.kmRepo.getLstDb();
    }

    @Override
    public boolean save(KhuyenMai km) {
        return this.kmRepo.save(km);
    }

    @Override
    public boolean delete(KhuyenMai km) {
        return this.kmRepo.delete(km);
    }

    @Override
    public KhuyenMai getObj(String ma) {
        return this.kmRepo.getObj(ma);
    }

    @Override
    public KhuyenMai getObjById(int id) {
        return this.kmRepo.getObjById(id);
    }

    @Override
    public List<KhuyenMai> getAllByTen(String ten) {
         return this.kmRepo.getAllByTen(ten);
    }

}
