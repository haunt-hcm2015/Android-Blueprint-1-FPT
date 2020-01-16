package com.android.teamnovelvn.sakuranovel.Entity;

/**
 * Created by Akito on 24/11/2016.
 */

public class DanhsachTapTruyen {
    private String linkChuong;
    private String tenChuong;

    public DanhsachTapTruyen() {
    }

    public DanhsachTapTruyen(String linkChuong, String tenChuong) {
        this.linkChuong = linkChuong;
        this.tenChuong = tenChuong;
    }

    public String getLinkChuong() {
        return linkChuong;
    }

    public void setLinkChuong(String linkChuong) {
        this.linkChuong = linkChuong;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }
}
