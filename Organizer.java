/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;

/**
 *
 * @author Ajis
 */
public class Organizer extends Akun{
    private boolean block=true;
    private boolean daftar=false;
    
    public Organizer(String username, String password) {
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
}
