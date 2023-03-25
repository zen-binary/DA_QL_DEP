/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.MauSac;
import Reposetories.IMauSacReposetory;
import Reposetories.implement.MauSacReposetory;
import Services.IMauSacService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MauSacService implements IMauSacService {

    IMauSacReposetory msRepo;

    public MauSacService() {
        msRepo = new MauSacReposetory();
    }

    @Override
    public List<MauSac> getLst() {
        return this.msRepo.getLstDb();
    }

    @Override
    public boolean save(MauSac ms) {
        return this.msRepo.save(ms);
    }

    @Override
    public boolean delete(MauSac ms) {
        return this.msRepo.delete(ms);
    }

    @Override
    public MauSac getObj(String ma) {
        return this.msRepo.getObj(ma);
    }

    @Override
    public MauSac getObjById(int id) {
        return this.msRepo.getObjById(id);
    }

    @Override
    public List<MauSac> getLstByMs(String ms) {
        return msRepo.getLstByMs(ms);
    }

    @Override
    public MauSac getObjByMauSac(String ms) {
        return msRepo.getObjByMauSac(ms);
    }

}
