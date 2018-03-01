/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import cr.ac.una.moviles.lab1.bl.ProductoBL;
import Vista.ProductoVista;
/**
 *
 * @author esteban
 */
import cr.ac.una.moviles.lab1.domain.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class Control implements ActionListener {
    public Control(){
        prodVista= new ProductoVista();
        prodVista.getAgregar().addActionListener(this);
        prodVista.getAgregar().setActionCommand("AGREGAR");
        prodVista.getCodigo().addActionListener(this);
        prodVista.getImportado().addActionListener(this);
        prodVista.getBuscarNombre().addActionListener(this);
        prodVista.getBuscarTipo().addActionListener(this);
        prodVista.getNombre().addActionListener(this);
        prodVista.getPrecio().addActionListener(this);
        prodVista.getSelectTipo().addActionListener(this);
        prodVista.getTextNombre().addActionListener(this);
        prodVista.getTipoProducto2().addActionListener(this);
        
        
        //falta logica de negocio
    }

   

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("AGREGAR")){
        JOptionPane.showMessageDialog(null, null,"prueba",JOptionPane.ERROR_MESSAGE);
        }
    }
    
   private ProductoVista prodVista;
}
