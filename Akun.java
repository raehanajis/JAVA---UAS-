/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;
import java.io.Serializable;
/**
 *
 * @author Ajis
 */
public class Akun implements Serializable{
    private String username;
    private String password;
    
    public Akun(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
