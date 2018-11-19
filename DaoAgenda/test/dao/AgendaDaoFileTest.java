/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Agenda;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author jose
 */
public class AgendaDaoFileTest {
    
    public AgendaDaoFileTest() {
    }
    
    AgendaDaoFile dao;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao= new AgendaDaoFile();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of inserir method, of class AgendaDaoFile.
     */
    @Test
    
    public void testInserir() {
        
        Agenda agenda= new Agenda();
        
        agenda.setEmail("lauro@foda.com");
        //agenda.setId(1);
        agenda.setNome("Evamberto");
        agenda.setTelefone("88 10102345");
        assertTrue(dao.inserir(agenda));
       
      
    }

    /**
     * Test of deletar method, of class AgendaDaoFile.
     */
    @Test
    @Ignore
    public void testDeletar() {
        Agenda agenda= new Agenda();
        agenda.setId(3);
        dao.deletar(agenda);
       
    }

    /**
     * Test of buscar method, of class AgendaDaoFile.
     */
    @Test
    public void testBuscar() {
       
    }

    /**
     * Test of atualizar method, of class AgendaDaoFile.
     */
    @Test
    @Ignore
    public void testAtualizar() {
        Agenda agenda = new Agenda();
        
        agenda.setEmail("b@b.com");
        agenda.setId(4);
        agenda.setTelefone("99282323");
        agenda.setNome("Ricardo");
        dao.atualizar(agenda);
       
    }
    
}
