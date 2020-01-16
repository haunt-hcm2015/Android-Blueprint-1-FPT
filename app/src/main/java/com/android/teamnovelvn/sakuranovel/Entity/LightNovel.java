package com.android.teamnovelvn.sakuranovel.Entity;

/**
 * Created by Akito on 21/11/2016.
 */

public class LightNovel {
    private String Hinh;
    private String TacGia;
    private String TheLoai;
    private String TomTat;
    private String TienTrinh;
    private String TeamDich;

    public LightNovel() {
    }

    public LightNovel(String hinh, String tacGia, String theLoai, String tomTat, String tienTrinh, String teamDich) {
        this.Hinh = hinh;
        TacGia = tacGia;
        TheLoai = theLoai;
        TomTat = tomTat;
        TienTrinh = tienTrinh;
        TeamDich = teamDich;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        this.Hinh = hinh;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getTomTat() {
        return TomTat;
    }

    public void setTomTat(String tomTat) {
        TomTat = tomTat;
    }

    public String getTienTrinh() {
        return TienTrinh;
    }

    public void setTienTrinh(String tienTrinh) {
        TienTrinh = tienTrinh;
    }

    public String getTeamDich() {
        return TeamDich;
    }

    public void setTeamDich(String teamDich) {
        TeamDich = teamDich;
    }
}
