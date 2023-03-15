/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.KhachHang;
import Reposetories.IKhachHangReposetory;
import Reposetories.implement.KhachHangReposetory;
import Services.IKhachHangService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangService implements IKhachHangService {

    IKhachHangReposetory khRepo;

    public KhachHangService() {
        khRepo = new KhachHangReposetory();
    }

    @Override
    public List<KhachHang> getLst() {
        return this.khRepo.getLstDb();
    }

    @Override
    public boolean save(KhachHang kh) {
        return this.khRepo.save(kh);
    }

    @Override
    public boolean delete(KhachHang kh) {
        return this.khRepo.delete(kh);
    }

    @Override
    public KhachHang getObj(String ma) {
        return this.khRepo.getObj(ma);
    }

    @Override
    public KhachHang getObjById(int id) {
        return this.khRepo.getObjById(id);
    }

}
