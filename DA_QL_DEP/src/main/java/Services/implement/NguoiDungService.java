/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.NguoiDung;
import Reposetories.INguoiDungReposetory;
import Reposetories.implement.NguoiDungReposetory;
import Services.INguoiDungService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NguoiDungService implements INguoiDungService {

    INguoiDungReposetory ndRepo;

    public NguoiDungService() {
        ndRepo = new NguoiDungReposetory();
    }

    @Override
    public List<NguoiDung> getLst() {
        return this.ndRepo.getLstDb();
    }

    @Override
    public boolean save(NguoiDung nd) {
        return this.ndRepo.save(nd);

    }

    @Override
    public boolean delete(NguoiDung nd) {
        return this.ndRepo.delete(nd);
    }

    @Override
    public NguoiDung getObj(String ma) {
        return this.ndRepo.getObj(ma);
    }

    @Override
    public NguoiDung getObjById(int id) {
        return this.ndRepo.getObjById(id);
    }

    @Override
    public List<NguoiDung> getAllByObj(String ten, String cv) {
        return this.ndRepo.getAllByObj(ten, cv);
    }

}
