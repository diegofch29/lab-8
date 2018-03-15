/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    private List<Cliente> lisClien;
    private List<Item> lisItem;
    private long idTCliente;
    private List<ItemRentado> itemsRetrasados;

    
    
    
    public AlquilerItemsBean() throws ExcepcionServiciosAlquiler {

        lisClien=new ArrayList<Cliente>();
        lisItem=new ArrayList<Item>();
        TipoItem tiT=new TipoItem(12, "Religiones del mundo");
        Item it1= new Item(tiT, 123, "Las Religiones","Una Coleccion de todas las religiones del mundo", new Date(82,4,1,10,30,15), 2000, "DVD", "Documental") ;
        Item it2= new Item(tiT, 321, "Las Historias mas locas de la historia","Una Coleccion de todas las historias mas bizarras al rededor del mundo", new Date(82,4,1,10,30,15), 2089, "DVD", "Documental") ;
        Cliente c1= new Cliente("Juan Perez",3842,"24234","calle 123","aa@gmail.com");
        Cliente c2= new Cliente("Juana Parez",31842,"214234","calle 13","aabcd@gmail.com");
        sp.registrarItem(it1);
        sp.registrarItem(it2);
        sp.registrarCliente(c1);
        sp.registrarCliente(c2);
        //lisClien.add(c1);
        //lisClien.add(c2);
        //lisItem.add(it1);
        //lisItem.add(it2);
        for(int i=0; i<2;i++){
            lisClien=sp.consultarClientes();
            Cliente c= lisClien.get(i);
            System.out.println("mirar cliente"+ c.getNombre());
            
        }
        //Datos estaticos    
    }
    
    public void setTargetCliente(long id){
        idTCliente=id;
    }
    
    public long  getTargetCliente(){
        return idTCliente;
    }
    
    public List<ItemRentado> ConsultaItemRetrasados(long client) throws ExcepcionServiciosAlquiler{
        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Iterator<ItemRentado> itemsCliente=sp.consultarItemsCliente(client).iterator();
        itemsRetrasados=new ArrayList<ItemRentado>();
        ItemRentado item;
        while(itemsCliente.hasNext()){
            item=itemsCliente.next();
            if(item.getFechafinrenta().after(now)){
                itemsRetrasados.add(item);
            }
        }
        
        return itemsRetrasados;
    }
    
    public List<Long> getMultas() throws ExcepcionServiciosAlquiler{
        Iterator<ItemRentado> items =itemsRetrasados.iterator();
        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        List<Long> multas=new ArrayList<Long>();
        ItemRentado item;
        while(items.hasNext()){
            item = items.next();
            multas.add(sp.consultarMultaAlquiler(item.getItem().getId(), now));
        }
        return multas;
    }
    
    
    public List<Cliente> getClien() throws ExcepcionServiciosAlquiler{
        System.out.println("mirar"+ sp.consultarCliente(3842));
        for(int i=0; i<2;i++){
            Cliente c= lisClien.get(i);
            System.out.println("mirar cliente"+ c.getNombre());
            
        }
        
        return sp.consultarClientes();
    }
    
    public List<Item> getItem(){
        return sp.consultarItemsDisponibles();
    
    }
    
    
    
    
    
    
}
