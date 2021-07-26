/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Ajis
 */
public class Acara implements Serializable{
    private String nama;
    private String OrganizName;
    private String sport;
    private String peringkat;
    private String lokasi;
    private int tanggal;
    private int bulan ;
    private int tahun;
    private int jumMin;
    private int jumMax;
    
    //counter jumlah
    private int OrderedNow = 0;
    private ArrayList<String> peserta = new ArrayList<>();
    private int price;
    
    //cancel atau penuh
    private boolean status= true;
    private boolean full=false;
    
    public Acara (String nama, String OrganizName, String sport, String peringkat, int tanggal, int bulan, int tahun, String lokasi, int jumMin, int jumMax, int price){
    
        this.nama = nama;
        this.OrganizName = OrganizName;
        this.sport =sport;
        this.peringkat = peringkat;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumMin = jumMin;
        this.jumMax = jumMax;
        this.price = price;
    }
    
    public String getNama() {
        return nama;
    }
    public String getOrganizName() {
        return OrganizName;
    }
    
    public String getSport() {
        return sport;
    }
    
    public String getPeringkat() {
        return peringkat;
    }
    
    public int getTanggal() {
        return tanggal;
    }
    
    public int getBulan() {
        return tanggal;
    }
    public int getTahun() {
        return tahun;
    }
    public int getJumMin() {
        return jumMin;
    }
    public int getJumMax() {
        return jumMax;
    }
    public int getPrice() {
        return price;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean isStatus() {
        return status;
    }
    public void AddPeserta (String username){
        OrderedNow = OrderedNow+1;
        if (jumMax==OrderedNow){
            full=true;
        }
        peserta.add(username);
    }
    public void SubstractPeserta (String username) {
        OrderedNow = OrderedNow-1;
        full = false;
        peserta.remove(new String(username));
    }
    public ArrayList<String> getPeserta() {
        return peserta;
    }
    public boolean isfull() {
        return full;
    }
}
