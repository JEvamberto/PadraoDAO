/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agenda;

/**
 *
 * @author jose
 */
public class AgendaDaoFile implements Dao {

    FileWriter fileWrite;
    FileReader ler;
    static int id = 1;
    

    @Override
    public boolean inserir(Object object) {
        Agenda agenda = (Agenda) object;
       
        
        try {

            ler = new FileReader(new File("Dao.txt"));
            BufferedReader lerArq = new BufferedReader(ler);
            String s;

            while ((s = lerArq.readLine()) != null) {
                id++;
            }
            lerArq.close();
            ler.close();

            //---
            agenda.setId(id);
            fileWrite = new FileWriter(new File("Dao.txt"), true);

            fileWrite.write(agenda.toString() + "\n");
            fileWrite.flush();
            fileWrite.close();
            
            return true;

        } catch (IOException ex) {

            System.err.println("Erro ao criar o arquivo");
            return false;
        }

    }

    @Override
    public boolean deletar(Object object) {

        boolean verifica = true;
        Agenda agenda = (Agenda) object;
        ArrayList<String> list = new ArrayList<>();

        try {
            ler = new FileReader(new File("Dao.txt"));
            BufferedReader lerArq = new BufferedReader(ler);
            String s;

            while ((s = lerArq.readLine()) != null) {

                verifica = true;

                if (s.contains("id:" + agenda.getId())) {
                    verifica = false;
                    System.out.println("Olha sou eu");
                }

                if (verifica) {
                    System.out.println("HELLo");
                    list.add(s);
                }

            }
            lerArq.close();
            ler.close();

            fileWrite = new FileWriter("Dao.txt");

            for (String list1 : list) {

                this.fileWrite.write(list1 + "\n");
            }

            fileWrite.flush();
            fileWrite.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AgendaDaoFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgendaDaoFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    @Override
    public List buscar() {

        ArrayList<String> list = new ArrayList<>();

        try {
            ler = new FileReader(new File("Dao.txt"));
            BufferedReader lerArq = new BufferedReader(ler);
            String s;

            while ((s = lerArq.readLine()) != null) {

                list.add(s);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AgendaDaoFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgendaDaoFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean atualizar(Object object) {
        boolean verifica = true;

        Agenda agenda = (Agenda) object;
        ArrayList<String> list = new ArrayList<>();

        try {
            ler = new FileReader(new File("Dao.txt"));
            BufferedReader lerArq = new BufferedReader(ler);
            String s;

            while ((s = lerArq.readLine()) != null) {

                verifica = true;

                if (s.contains("id:" + agenda.getId())) {

                    s = agenda.toString();
                }

                list.add(s);

            }
            lerArq.close();
            ler.close();

            fileWrite = new FileWriter("Dao.txt");

            for (String list1 : list) {

                this.fileWrite.write(list1 + "\n");
            }

            fileWrite.flush();
            fileWrite.close();

            return true;

        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

    }

}
