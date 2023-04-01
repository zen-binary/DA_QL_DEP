/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;

import Models.ChiTietDep;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietDepReposetory {

    public List<ChiTietDep> getLstDb();

    public List<ChiTietDep> getLstByTen(String ten);

    public List<ChiTietDep> getAllByObj(int tinhTrang, String ten, int soLuong);

    public boolean save(ChiTietDep ctd);

    public boolean delete(ChiTietDep ctd);

    public ChiTietDep getObj(String ma);

    public ChiTietDep getObjById(int id);

    public ChiTietDep getFindAllObj(int idDep, int idLoai, int idSize, int idMs, int idCl, int idNsx);

    public int getCount();

    public int getCountTinhTrang(int tinhTrang);

}
