/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.Dep;
import Reposetories.IDepReposetory;
import Reposetories.implement.DepReposetory;
import Services.IDepService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DepService implements IDepService {

    IDepReposetory depRepo;

    public DepService() {
        depRepo = new DepReposetory();
    }

    @Override
    public List<Dep> getLst() {
        return depRepo.getLstDb();
    }

    @Override
    public boolean save(Dep d) {
        return depRepo.save(d);
    }

    @Override
    public boolean delete(Dep d) {
        return depRepo.delete(d);
    }

    @Override
    public Dep getObj(String ma) {
        return depRepo.getObj(ma);
    }

    @Override
    public Dep getObjById(int id) {
        return depRepo.getObjById(id);
    }

    @Override
    public Dep getObjByTen(String ten) {
       return depRepo.getObjByTen(ten);
    }

}
