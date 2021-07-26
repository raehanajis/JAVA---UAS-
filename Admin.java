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
public class Admin implements Serializable{
    private String info = "";

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
