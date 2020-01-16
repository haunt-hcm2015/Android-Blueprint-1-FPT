package com.android.teamnovelvn.sakuranovel.Entity;

/**
 * Created by Akito on 21/11/2016.
 */

public class DanhSachTruyen {
    private String linkHinh;
    private String tenTruyen;

    public DanhSachTruyen() {
    }

    public DanhSachTruyen(String linkHinh, String tenTruyen) {
        this.linkHinh = linkHinh;
        this.tenTruyen = tenTruyen;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }
}
