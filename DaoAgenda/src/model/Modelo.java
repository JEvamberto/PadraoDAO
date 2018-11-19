/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AgendaBDDao;
import dao.AgendaDaoFile;
import dao.Dao;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Modelo {
    
    private Agenda agenda;
    private AgendaBDDao agendaBd;
    private AgendaDaoFile agendaFile; 
    private Dao dao;
    
    public Modelo(){
        this.agenda= new Agenda();
        this.agendaBd=new AgendaBDDao();
        this.agendaFile=new AgendaDaoFile();
        this.dao=this.agendaBd;
        
    }
    
    ArrayList <Observer> observer = new ArrayList<>();
    
    public void attach(Observer observer){
        
        this.observer.add(observer);
    }
    
    public void dettach(Observer observer){
        this.observer.add(observer);
    
    }
    public void NotifyTtoAll(){
        for (Observer observer1 : this.observer) {
            observer1.update();
        }
    }
    
    public void escolheDao(String dao){
        if ("file".equals(dao)) {
            this.dao=this.agendaFile;
        }else if("Bd".equals(dao)){
            this.dao=this.agendaBd;
        }else{
                //Padrão
             this.dao=this.agendaBd;
        }
        
        this.NotifyTtoAll();
        
    }
    public void resgistraContato(String nome, String email, String telefone){
        
        this.agenda.setEmail(email);
        this.agenda.setNome(nome);
        this.agenda.setTelefone(telefone);
        
        this.dao.inserir(agenda);
        this.NotifyTtoAll();
    
    }
    
      public void atualizaContato(int id,String nome, String email, String telefone){
        this.agenda.setId(id);
        this.agenda.setEmail(email);
        this.agenda.setNome(nome);
        this.agenda.setTelefone(telefone);
        
        this.dao.atualizar(agenda);
        this.NotifyTtoAll();
    
    }
      
      public void excluir(int id){
          this.agenda.setId(id);
          this.dao.deletar(this.agenda);
          this.NotifyTtoAll();
      }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

  

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
    
    
    
    
    
}
