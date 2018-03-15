/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class ClientesTest {

    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void additems1() throws ExcepcionServiciosAlquiler{
    	
    }
    
    /**
     *  Clases de equivalencia
     * El cliente no sea nulo, entonces se generara un nuevo cliente.
     * SI el cliente es nulo genera excepcion.
     *  * Calculo Multa:
    * 
    * Frontera:
    * CF1: Multas a devoluciones hechas en la fecha exacta (multa 0).
    * 
    * Clases de equivalencia:
    * CE1: Multas hechas a devolciones realizadas en fechas posteriores
    * a la limite. (multa multa_diaria*dias_retraso).
    * 
    * CE2: Multa para un cliente sin antecedentes de multas.(multa multa_diaria*dias_retraso)
    * 
    * CE3: MUlta para un cliente  con antecedentes de multa.(multa multa_diaria*dias_retraso) + 
    * +intereses de multas anteriores.
        * 
     * 
    */
    //@Test
    public void regClientesCE1()throws ExcepcionServiciosAlquiler{
        ArrayList<ItemRentado> rentados= new ArrayList<ItemRentado>();
        TipoItem tiT=new TipoItem(12, "Religiones del mundo");
        Item it1= new Item(tiT, 123, "Las Religiones","Una Coleccion de todas las religiones del mundo", new Date(82,4,1,10,30,15), 2000, "DVD", "Documental") ;
        
        ItemRentado c1= new ItemRentado(it1, (java.sql.Date)new Date(2018,3,22), (java.sql.Date) new Date(2019,12,31));
        rentados.add(c1);
        LinkedList<Cliente> lisPru= new LinkedList<>();
        ServiciosAlquilerItemsStub pru= new ServiciosAlquilerItemsStub();

        Cliente clie= new Cliente("Perez", 2222222, "119119191", "Carrera 12 # 12-12", "12@1mail.com", true, rentados);
        
        pru.registrarCliente(clie);
        lisPru=(LinkedList<Cliente>) pru.consultarClientes();
        
        assertTrue(lisPru.contains(clie));
        
        
        
    }
    @Test
    public void regClientesCE2()throws ExcepcionServiciosAlquiler{
        LinkedList<Cliente> lisPru= new LinkedList<>();
        ServiciosAlquilerItemsStub pru= new ServiciosAlquilerItemsStub();
        Cliente clie=new Cliente("Pepito", 1111111, "1111111", "Calle 1 # 1-1", "1@1mail.com");
        
        pru.registrarCliente(clie);
        lisPru=(LinkedList<Cliente>) pru.consultarClientes();
        
        assertTrue(lisPru.contains(clie));
        
     
    }
        @Test
    public void regClientesCE3()throws ExcepcionServiciosAlquiler{
        ArrayList<ItemRentado> rentados= new ArrayList<ItemRentado>();
        ItemRentado c1= new ItemRentado();
        rentados.add(c1);
        LinkedList<Cliente> lisPru= new LinkedList<>();
        ServiciosAlquilerItemsStub pru= new ServiciosAlquilerItemsStub();

        Cliente clie= new Cliente("Perez", 2222222, "119119191", "Carrera 12 # 12-12", "12@1mail.com", true, rentados);
        
        pru.registrarCliente(clie);
        lisPru=(LinkedList<Cliente>) pru.consultarClientes();
        
        assertTrue(lisPru.contains(clie));
        
     
    }
    
    
    
    
}
