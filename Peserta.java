/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;
import java.util.ArrayList;
/**
 *
 * @author Ajis
 */
public class Peserta extends Akun implements Peringkat {
        private boolean block=true;
        private boolean daftar=false;
        private ArrayList<String> lencanapeserta = new ArrayList<>();
        private int peringkat=0;
        
    public Peserta(String username, String password) {
        super(username, password);
    }
     public String getUsername() {
        return super.getUsername();
    }

    public String getPassword() {
        return super.getPassword();
    }
    public boolean isBlock() {
        return block;
    }
    public boolean isdaftar() {
        return daftar;
    }
    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setdaftar(boolean daftar) {
        this.daftar = daftar;
    }
    public void TambahLencana(String lencana){
        lencanapeserta.add(lencana);
    }
    @Override
    public void Turunperingkat() {
       peringkat = peringkat+1;
    }

    @Override
    public void Naikperingkat() {
        peringkat = peringkat-1;
    }
    public ArrayList<String> getLencana() {
        return lencanapeserta;
    }

    public int getPeringkat() {
        return peringkat;
    }
}
