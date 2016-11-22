package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.model;

import java.io.Serializable;

/**
 * Created by WildanZ on 22/11/2016.
 */
public class Resep implements Serializable {
    public String judul;
    public String deskripsi;
    public String bahan;
    public String langkah;

    public Resep(String judul, String deskripsi, String bahan, String langkah) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.bahan = bahan;
        this.langkah = langkah;
    }
}
