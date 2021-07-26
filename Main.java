/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Ajis
 */
public class Main {
     public static void main(String[] args) {
        ArrayList<Organizer> organizers = new ArrayList<>();
        ArrayList<Peserta> pesertaa = new ArrayList<>();
        ArrayList<Acara> Acaraa = new ArrayList<>();
        Admin admin = new Admin();
        
                String fileOrganizers = "organizers.txt";
        String filePemains = "pesertaa.txt";
        String fileKegiatan = "kegiatan.txt";
        String fileAdmin = "admin.txt";

        try {
       
            ObjectInputStream inOrganizers = new ObjectInputStream(new FileInputStream(fileOrganizers));
            organizers = (ArrayList<Organizer>) inOrganizers.readObject();
            inOrganizers.close();

         
            ObjectInputStream inPeserta = new ObjectInputStream(new FileInputStream(filePemains));
            pesertaa = (ArrayList<Peserta>) inPeserta.readObject();
            inPeserta.close();

 
            ObjectInputStream inAcara = new ObjectInputStream(new FileInputStream(fileKegiatan));
            Acaraa = (ArrayList<Acara>) inAcara.readObject();
            inAcara.close();

         
            ObjectInputStream inAdmin = new ObjectInputStream(new FileInputStream(fileAdmin));
            admin = (Admin) inAdmin.readObject();
            inAdmin.close();
            System.out.println("LOAD SUCCESS\n");

        } catch(IOException ex) {
            System.out.println("NO DATA\n");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        Scanner read = new Scanner(System.in);
        int jwb;

        do {
            Infologin(admin);

            try{
                jwb = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jwb = 5;
            }
            String dummy=read.nextLine();

            switch (jwb){
                case 1 : {
                    adminMenu(admin, organizers, pesertaa);
                    break;
                }
                case 2 : {
                    organizersLogin(organizers, Acaraa);
                    break;
                }
                case 3 : {
                    pesertaaLogin(pesertaa, Acaraa);
                    break;
                }
                case 4 : {
                    String pilihan;
                    do{
                        System.out.print("Save Data? [ya/tidak]");
                        pilihan = read.nextLine();
                    }while (!pilihan.equals("ya") && !pilihan.equals("tidak"));
                    if (pilihan.equals("ya")){
                        saveData(organizers, pesertaa, Acaraa, admin, fileOrganizers, filePemains, fileKegiatan, fileAdmin);
                    }
                    // exit and save
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada\n");
                    break;
                }
            }

        }while (jwb!=4);
     }
     
    private static void saveData(ArrayList<Organizer> organizers, ArrayList<Peserta> pesertaa, ArrayList<Acara> Acaraa, Admin admin, String fileOrganizers, String filePemains, String fileKegiatan, String fileAdmin) {
        try
        {
        
            ObjectOutputStream outOrganizer = new ObjectOutputStream(new FileOutputStream(fileOrganizers));
            outOrganizer.writeObject(organizers);

       
            ObjectOutputStream outPemains = new ObjectOutputStream(new FileOutputStream(filePemains));
            outPemains.writeObject(pesertaa);

          
            ObjectOutputStream outKegiatan = new ObjectOutputStream(new FileOutputStream(fileKegiatan));
            outKegiatan.writeObject(Acaraa);
            
            ObjectOutputStream outAdmin = new ObjectOutputStream(new FileOutputStream(fileAdmin));
            outAdmin.writeObject(admin);

            System.out.println("\nDONE SAVE");

        }
        catch(IOException ex)
        {
            System.out.println("Error SAVE DATA");
        }
    }
    private static void Infologin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void pesertaaLogin(ArrayList<Peserta> pesertaa, ArrayList<Acara> Acaraa) {
        Scanner read = new Scanner (System.in);
        int jwb;
        
        do{
            System.out.println("--Selamat Datang Peserta--");
            System.out.println("1. Create");
            System.out.println("2. Login");
            System.out.println("3. EXIT");
            System.out.print("Pilihan : ");
            try{
                jwb = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jwb = 4;
            }
             String dummy=read.nextLine();
             
           switch (jwb){
                case 1 : {
             
                    String username, password;
                    boolean usernameC = false;
                    do {
                        do {
                            usernameC = false;
                            System.out.print("\nmasukkan username : ");
                            username = read.nextLine();
                        }while (username.length()>15 || username.length()<1);

                        for (Peserta p : pesertaa){
                            if (p.getUsername().equals(username)){
                                usernameC = true;
                                System.out.println("username sudah digunakan");
                            }
                        }
                    }while (usernameC);

                    //input password
                    do {
                        System.out.print("masukkan password : ");
                        password = read.nextLine();
                    }while (password.length()>10 || password.length()<1);

                    pesertaa.add(new Peserta(username, password));
                    System.out.println("Berhasil membuat akun silahkan tunggu verifikasi admin untuk melanjutkan");
                    break;
                }
                case 2 : {
                                    String username, password;
                    boolean status = false, benar = false;
                    //login
                    if (pesertaa.size()>0){
                        System.out.println("\n DATA LOGIN");
                        System.out.println("| username        | password        |");
                        for (Peserta p : pesertaa){
                            System.out.printf("| %20s | %20s |\n", p.getUsername(), p.getPassword());
                        }

                        System.out.print("\nmasukkan username : ");
                        username = read.nextLine();
                        System.out.print("masukkan password : ");
                        password = read.nextLine();

                        for (Peserta p : pesertaa){
                            if (p.getUsername().equals(username) && p.getPassword().equals(password)){
                                benar = true;
                                if (p.isBlock() && p.isdaftar()){
                                    status = true;
                                }
                            }
                        }
                        if (!benar){
                            System.out.println("username atau password salah");
                        } else if (!status){
                            System.out.println("account terblock atau belum diregistrasi");
                        }else {
                            //menu yg bisa dilakukan organizer
                            pesertaaMenu(pesertaa, Acaraa, username);
                        }

                    }else {
                        System.out.println("\n\nBELUM ADA AKUN YANG DIBUAT");
                    }
                    break;
                }
                case 3 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
           }
        }while (jwb!=3);
    }
    private static void pesertaaMenu(ArrayList<Peserta> pesertaa, ArrayList<Acara> Acaraa, String username) {
        Scanner read = new Scanner(System.in);
        int jwb;
        
        do {
        
            System.out.println("\n Menu Peserta");
            System.out.println("1. View Semua Kegiatan");
            System.out.println("2. Registrasi Kegiatan");
            System.out.println("3. Cancell");
            System.out.println("4. Memberi lencana ");
            System.out.println("5. Peringkat & Lencana ");
            System.out.println("6. EXIT");
            System.out.print("Pilihan : ");
            try{
                jwb = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jwb = 7;
            }
            String dummy=read.nextLine();
            switch (jwb){
            
               case 1 : {
                    if (Acaraa.size()>0){
                        viewSemuaAcara(Acaraa);
                    }else System.out.println("\nBELUM ADA KEGIATAN YANG DIBUAT");
                    break;
                }
               case 2 : {
                    if (Acaraa.size()>0){
                        int pilihan;
                        //view data
                        viewSemuaAcara(Acaraa);

                        //choose data
                        do{
                            System.out.print("Masukkan pilihan Kegiatan [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>Acaraa.size());

                        pilihan=pilihan-1;
                        Acara k = Acaraa.get(pilihan);
                        if (!k.isStatus()){
                            System.out.println("\nKegiatan Tutup");
                        } else if (k.getPeserta().contains(username)){
                            System.out.println("\nAnda sudah Terdaftar");
                        } else if (k.isPenuh()){
                            System.out.println("\nKegiatan Penuh");
                        } else {
                            for (Peserta p : pesertaa){
                                if (p.getUsername().equals(username)){
                                    p.Naikperingkat();
                                }
                            }
                            k.Addpesserta(username);
                            System.out.println("\nPendaftaraan Success");
                        }
                    }else System.out.println("\nBelum ada acara dibuat");
                    break;
                }
               case 3 : {
                    String batal;
                    int counter=0;
                    boolean ada = false;

                    for (Acara k : Acaraa){
                        if (k.getpesrta().contains(username)){
                            counter++;
                        }
                    }

                    if (counter>0){
                        //view data
                        viewKegiatanPeserta(Acaraa, username);

                        int year = Calendar.getInstance().get(Calendar.YEAR);
                        int month = Calendar.getInstance().get(Calendar.MONTH);
                        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                        month=month+1;

                        System.out.print("nama kegiatan yang mau dibatalkan / diselesaikan : ");
                        batal = read.nextLine();

                        //tanggal sekarng
                        for (Acara k : Acaraa){
                            if (k.getpeserta().contains(username) && k.getNama().equals(batal) && k.isStatus()){
                                ada=true;
                                for (Peserta p : pesertaa){
                                    if (p.getUsername().equals(username) && year<k.getTahun()){
                                        p.Turunperingkat();
                                        k.SubstractPeserta(username);
                                        System.out.println("\nAcara Berhasil dibatalkan");
                                    } else if (p.getUsername().equals(username) && year==k.getTahun()){
                                        if (month<k.getBulan()){
                                            p.Turunperingkat();
                                            k.SubstractPeserta(username);
                                            System.out.println("\nAcara Berhasil dibatalkan");
                                        } else if (month==k.getBulan()){
                                            if (day<k.getTanggal()){
                                                p.Turunperingkat();
                                                k.SubstractPeserta(username);
                                                System.out.println("\nAcara Berhasil dibatalkan");
                                            } if (day>=k.getTanggal())System.out.println("\nMelebihi Tanggal Pembatalan");
                                        } if (month>k.getBulan())System.out.println("\nMelebihi Tanggal Pembatalan");
                                    } if (year>k.getTahun())System.out.println("\nMelebihi Tanggal Pembatalan");
                                }
                            }
                        }
                        if (!ada){
                            System.out.println("\nKegiatan Tidak Ada atau Sudah Ditutup");
                        }
                    }else System.out.println("\nBelum Membuat Kegiatan");

                    break;
                }
                          case 4 : {
                    if (pesertaa.size()>1){
                        int chose;
                        String lencana;

                        viewPeserta(pesertaa);

                        do{
                            System.out.print("Masukkan pilihan Pemain : ");
     
                        }while (chose>1 || chose>pesertaa.size());

                        do {
                            System.out.print("Tulis Lencana : ");
                           
                        }while (lencana.length()<1 || lencana.length()>10);

                       
                        Peserta p = pesertaa.get(chose);
                        if (p.getUsername().equals(username)){
                            System.out.println("\nTidak Dapat Memberi Lencana Pada Diri Sendiri");
                        } else {
                          
                            System.out.println("\nBerhasil Ditambahkan Lencana ");
                        }

                    }else System.out.println("\nbelum ada peserta");
                    break;
                }
               
            
            }
        }while (jwb!=6);
        
    }

    private static void organizersLogin(ArrayList<Organizer> organizers, ArrayList<Acara> Acaraa) {
        Scanner read = new Scanner(System.in);
        int jwb; 
        do{
            System.out.println("-Selamat Datang--");
            System.out.println("1. Create");
            System.out.println("2. Login");
            System.out.println("3. EXIT");
            System.out.print("Pilihan : ");
            try{
                jwb = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jwb = 4;
            }
             String dummy=read.nextLine();
             switch (jwb){
                case 1 : {
                    
                  
                    String username, password;
                    boolean usernameC = false;
                    do {
                        do {
                            usernameC = false;
                            System.out.print("\nmasukkan username [15]: ");
                            username = read.nextLine();
                        }while (username.length()>15 || username.length()<1);

                        for (Organizer p : organizers){
                            if (p.getUsername().equals(username)){
                                usernameC = true;
                                System.out.println("username sudah digunakan");
                            }
                        }
                    }while (usernameC);

                    //input password
                    do {
                        System.out.print("masukkan password [15]: ");
                        password = read.nextLine();
                    }while (password.length()>10 || password.length()<1);

                    organizers.add(new Organizer(username, password));
                    System.out.println("Berhasil membuat akun silahkan tunggu verifikasi admin untuk melanjutkan");
                    break;
                }
                case 2 : {
                                    String username, password;
                    boolean status = false, benar = false;
                    //login
                    if (organizers.size()>0){
                        System.out.println("\n DATA LOGIN");
                        System.out.println("| username        | password        |");
                        for (Organizer p : organizers){
                            System.out.printf("| %20s | %20s |\n", p.getUsername(), p.getPassword());
                        }

                        System.out.print("\nmasukkan username [15]: ");
                        username = read.nextLine();
                        System.out.print("masukkan password [15]: ");
                        password = read.nextLine();

                        for (Organizer p : organizers){
                            if (p.getUsername().equals(username) && p.getPassword().equals(password)){
                                benar = true;
                                if (p.isBlock() && p.isdaftar()){
                                    status = true;
                                }
                            }
                        }
                        if (!benar){
                            System.out.println("username atau password salah");
                        } else if (!status){
                            System.out.println("account terblock atau belum diregistrasi");
                        }else {
                            //menu yg bisa dilakukan organizer
                            OrganizerMenu(organizers, Acaraa, username);
                        }

                    }else {
                        System.out.println("\n\nBELUM ADA AKUN YANG DIBUAT");
                    }
                    break;
                }
                case 3 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
           }
        }while (jwb!=3);
    }
    private static void OrganizerMenu(ArrayList<Organizer> organizers, ArrayList<Acara> Acaraa, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private static void adminMenu(Admin admin, ArrayList<Organizer> organizers, ArrayList<Peserta> pesertaa) {
        Scanner read = new Scanner(System.in);
        int jwb; 
        
        do{
        
            System.out.println("\n\nMENU ADMIN");
            System.out.println("1. Info");
            System.out.println("2. Registrasi Akun");
            System.out.println("3. Block Akun ");
            System.out.println("4. EXIT");
            System.out.print("Pilihan : ");
            try{
                jwb = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jwb = 5;
            }
            switch (jwb) {
                case 1:
                    Info(admin);
                    break;
                case 2:
                    registrasi(organizers,pesertaa);
                    break;
                case 3:
                    block(organizers,pesertaa);
                    break;
                case 4:
                    System.out.println("\n");
                    break;
                case 5:
                    return;
            }
        }while (jwb!=4);
        
        
    }

    private static void viewSemuaAcara(ArrayList<Acara> Acaraa) {
        
    }

    //Menu Admin Dibawah ini
    private static void block(ArrayList<Organizer> organizers, ArrayList<Peserta> pesertaa) {
 
    }

    private static void Info(Admin admin) {

    }

    private static void registrasi(ArrayList<Organizer> organizers, ArrayList<Peserta> pesertaa) {
    
    }

    private static void viewKegiatanPeserta(ArrayList<Acara> Acaraa, String username) {

    }

    private static void viewPeserta(ArrayList<Peserta> pesertaa) {
       
    }

}
