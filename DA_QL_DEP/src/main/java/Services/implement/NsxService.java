/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.Nsx;
import Reposetories.INsxReposetory;
import Reposetories.implement.NsxReposetory;
import Services.INsxService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NsxService implements INsxService {

    INsxReposetory nsxRepo;

    public NsxService() {
        nsxRepo = new NsxReposetory();
    }

    @Override
    public List<Nsx> getLst() {
        return this.nsxRepo.getLstDb();
    }

    @Override
    public boolean save(Nsx nsx) {
        return this.nsxRepo.save(nsx);
    }

    @Override
    public boolean delete(Nsx nsx) {
        return this.nsxRepo.delete(nsx);
    }

    @Override
    public Nsx getObj(String ma) {
        return this.nsxRepo.getObj(ma);
    }

    @Override
    public Nsx getObjById(int id) {
        return this.nsxRepo.getObjById(id);
    }

    @Override
    public List<Nsx> getLstByTen(String ten) {
        return this.nsxRepo.getLstByTen(ten);
    }

}
