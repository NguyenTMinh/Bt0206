package com.example.bt0206;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class PhongBan {
    private String maPhongBan;
    private String tenPhongBan;
    private String tenTruongPhong = "";
    private List<String> danhSachPhoPhong;
    private List<NhanVien> danhSachNhanVien;

    public PhongBan() {
        danhSachPhoPhong = new ArrayList<>();
        danhSachNhanVien = new ArrayList<>();
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getTenTruongPhong() {
        return tenTruongPhong;
    }

    public void setTenTruongPhong(String tenTruongPhong) {
        this.tenTruongPhong = tenTruongPhong;
    }

    public List<String> getDanhSachPhoPhong() {
        return danhSachPhoPhong;
    }

    public void setDanhSachPhoPhong(List<String> danhSachPhoPhong) {
        this.danhSachPhoPhong = danhSachPhoPhong;
    }

    public List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<NhanVien> danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public void addPhoPhong(String phoPhong) {
        danhSachPhoPhong.add(phoPhong);
    }

    public void addNhanVien(NhanVien nhanVien) {
        danhSachNhanVien.add(nhanVien);
    }

    public int getSoLuongNhanVien() {
        int res = 0;
        if (!TextUtils.isEmpty(tenTruongPhong)) {
            res++;
        }
        res += danhSachPhoPhong.size();
        res += danhSachNhanVien.size();
        return res;
    }
}
