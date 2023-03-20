/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.LoaiDep;
import Reposetories.ILoaiDepReposetory;
import Reposetories.implement.LoaiDepReposetory;
import Services.ILoaiDepService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiDepService implements ILoaiDepService {

    ILoaiDepReposetory ldRepo;

    public LoaiDepService() {
        ldRepo = new LoaiDepReposetory();
    }

    @Override
    public List<LoaiDep> getLst() {
        return ldRepo.getLstDb();
    }

    @Override
    public boolean save(LoaiDep ld) {
        return ldRepo.save(ld);
    }

    @Override
    public boolean delete(LoaiDep ld) {
        return ldRepo.delete(ld);
    }

    @Override
    public LoaiDep getObj(String ma) {
        return ldRepo.getObj(ma);
    }

    @Override
    public LoaiDep getObjById(int id) {
        return ldRepo.getObjById(id);
    }

    @Override
    public List<LoaiDep> getLstByTen(String loai) {
        return ldRepo.getLstByTen(loai);
    }

}
