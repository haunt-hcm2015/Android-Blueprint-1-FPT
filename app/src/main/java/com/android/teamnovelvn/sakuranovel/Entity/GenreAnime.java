package com.android.teamnovelvn.sakuranovel.Entity;

/**
 * Created by Thanh on 12/7/2016.
 */

public class GenreAnime {
    private String Hinh;
    private String Ten;
    private String TheLoai;
    private String NoiDung;

    public GenreAnime() {
    }

    public GenreAnime(String hinh, String ten, String theLoai, String noiDung) {
        Hinh = hinh;
        Ten = ten;
        TheLoai = theLoai;
        NoiDung = noiDung;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

}
