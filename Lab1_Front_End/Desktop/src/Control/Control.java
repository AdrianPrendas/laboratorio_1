package Control;

import Vista.Ventana;
import cr.ac.una.moviles.lab1.bl.ProductoBL;
import cr.ac.una.moviles.lab1.bl.TipoBL;
import cr.ac.una.moviles.lab1.domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Control implements ActionListener{
    
    private Ventana v;
    
    public Control() {
        this.v = new Ventana();
        this.v.getBtnAgregar().setActionCommand("agregar");
        this.v.getBtnAgregar().addActionListener(this);
        this.v.getBtnBuscarNombre().setActionCommand("buscar por nombre");
        this.v.getBtnBuscarNombre().addActionListener(this);
        this.v.getBtnBuscarTipo().setActionCommand("buscar por tipo");
        this.v.getBtnBuscarTipo().addActionListener(this);
        this.v.getBtnModificar().setActionCommand("modificar");
        this.v.getBtnModificar().addActionListener(this);
        this.v.getBtnEliminar().setActionCommand("eliminar");
        this.v.getBtnEliminar().addActionListener(this);
        this.v.getErrCodigo().setText("");
        this.v.getErrNombre().setText("");
        this.v.getErrPrecio().setText("");
        this.findAll();
        this.loadTypes();
        
    }
    
    public void loadTypes(){
        ArrayList<Tipo> lista = new ArrayList();
        TipoBL tbl = new TipoBL();
        
        lista = new ArrayList(tbl.findAll());
        
        lista.forEach(t->{
            this.v.getSelectTipoAgregar().add(t.getNombre());
            this.v.getSelectTipoBuscar().add(t.getNombre());
        });
    }
    
    public void cleanForm(){
        this.v.getBtnAgregar().setLabel("Agregar");
        this.v.getCheckboxImportado().setState(false);
        this.v.gettFCodigo().setText("");
        this.v.gettFCodigo().setEditable(true);
        this.v.gettFNombre().setText("");
        this.v.gettFPrecio().setText("");
    }
    
    public void cleanTable(){
        DefaultTableModel model = (DefaultTableModel) this.v.getTable().getModel();
        while (model.getRowCount()>0){
             model.removeRow(0);
        }
    }
    
    public void cleanValidation(){
        this.v.getErrCodigo().setText("");
        this.v.getErrNombre().setText("");
        this.v.getErrPrecio().setText("");
    }
    
    public int isRowSelected(){
        return  ((!this.v.getTable().getSelectionModel().isSelectionEmpty())
                ? this.v.getTable().getSelectionModel().getMinSelectionIndex()
                : -1);
    }
    
    public void modify(int row){
        this.v.getBtnAgregar().setLabel("Modificar");
        Integer codigo = (Integer)this.v.getTable().getModel().getValueAt(row, 0);
        String nombre = (String)this.v.getTable().getModel().getValueAt(row, 1);
        Float precio = (Float)this.v.getTable().getModel().getValueAt(row, 3);
        this.v.gettFCodigo().setText(String.valueOf(codigo));
        this.v.gettFCodigo().setEditable(false);
        this.v.gettFNombre().setText(nombre);
        this.v.gettFPrecio().setText(String.valueOf(precio));
    }
    
    public int validation(){
        this.cleanValidation();
        int result = 0; //todo enbi
        int codigo;
        String nombre;
        boolean importado;
        float precio; 
        
        try{
            codigo = Integer.parseInt(v.gettFCodigo().getText());    
        }catch(Exception e){
            v.getErrCodigo().setText("solo numeros enteros");
            result--;
        }
        nombre = v.gettFNombre().getText();        
        if(nombre.isEmpty()){
            v.getErrNombre().setText("complete este campo");
            result--;
        }else{
            try{
                Integer.parseInt(nombre);
                v.getErrNombre().setText("Escriba un nombre valido");
                result--;
            }catch(Exception e){}
        }
        importado = v.getCheckboxImportado().getState();
         try{
            precio = Float.parseFloat(v.gettFPrecio().getText());    
        }catch(Exception e){
            v.getErrPrecio().setText("solo numeros, tambien reales");
            result--;
        }
        return result;
    }
    
    public void save(){
        if(validation()<0)
            return;
        int codigo = Integer.parseInt(v.gettFCodigo().getText()); 
        String nombre = v.gettFNombre().getText(); 
        float precio = Float.parseFloat(v.gettFPrecio().getText());    
        boolean importado = v.getCheckboxImportado().getState();
        String tipo = v.getSelectTipoAgregar().getSelectedItem();
        
        Producto p = new Producto(codigo,nombre,precio,importado,tipo);
        
        boolean resp = new ProductoBL().create(p);
        if(resp){
            JOptionPane.showMessageDialog(null, "Se agrego con exito", "Exito!!!", JOptionPane.INFORMATION_MESSAGE);
            this.cleanForm();
            this.findAll();//vuelve a cargar la tabla
        }else{
            JOptionPane.showMessageDialog(null, "Error de codigo duplicado", "Error!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void update(){
      if(validation()<0)
            return;
        int codigo = Integer.parseInt(v.gettFCodigo().getText()); 
        String nombre = v.gettFNombre().getText(); 
        float precio = Float.parseFloat(v.gettFPrecio().getText());    
        boolean importado = v.getCheckboxImportado().getState();
        String tipo = v.getSelectTipoAgregar().getSelectedItem();
        
        Producto p = new Producto(codigo,nombre,precio,importado,tipo);
        
        boolean resp = new ProductoBL().update(p);
        if(resp){
            JOptionPane.showMessageDialog(null, "Se actualizo con exito", "Exito!!!", JOptionPane.INFORMATION_MESSAGE);
            this.cleanForm();
            this.findAll();//vuelve a cargar la tabla
            
        }else
            JOptionPane.showMessageDialog(null, "Algo sucedio con la base de datos", "Error!!!", JOptionPane.ERROR_MESSAGE);
            
        
    }
    
    public void findAll() {
        ArrayList<Producto> lista = new ArrayList();
        ProductoBL pbl = new ProductoBL();
        DefaultTableModel model = (DefaultTableModel) this.v.getTable().getModel();
        this.cleanTable();
        
        lista = new ArrayList(pbl.findAll());
        Collections.sort(lista,(p,q)->p.getCodigo()-q.getCodigo());
        
        lista.forEach((p) -> model.addRow(new Object[]
        {   p.getCodigo(),
            p.getNombre(),
            p.getImportado(),
            p.getPrecio(),
            p.getTipo(),
            p.getPorcentaje(),
            p.getImpuesto(),
            p.getPrecioFinal()
        }));
        
    }
    
    public void findByName() {
        ArrayList<Producto> lista = new ArrayList();
        ProductoBL pbl = new ProductoBL();
        String findstr = this.v.gettFNombreBuscar().getText();
        DefaultTableModel model = (DefaultTableModel) this.v.getTable().getModel();
        this.cleanTable();
        
        if (findstr.isEmpty()) {//buscar todos
            findAll();
        } else {
            lista = new ArrayList(pbl.findByName(findstr));
            Collections.sort(lista,(p,q)->p.getCodigo()-q.getCodigo());
            
            lista.forEach((p) -> model.addRow(new Object[]
            {   p.getCodigo(),
                p.getNombre(),
                p.getImportado(),
                p.getPrecio(),
                p.getTipo(),
                p.getPorcentaje(),
                p.getImpuesto(),
                p.getPrecioFinal()
            }));
        }
    }
    
    public void findByType(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
        ProductoBL pbl = new ProductoBL();
        String findstr = this.v.getSelectTipoBuscar().getSelectedItem();
        
        DefaultTableModel model = (DefaultTableModel) this.v.getTable().getModel();
        this.cleanTable();
        
        lista = new ArrayList(pbl.findByType(findstr));
        Collections.sort(lista,(p,q)->p.getCodigo()-q.getCodigo());
            
        lista.forEach((p) -> model.addRow(new Object[]
        {   p.getCodigo(),
            p.getNombre(),
            p.getImportado(),
            p.getPrecio(),
            p.getTipo(),
            p.getPorcentaje(),
            p.getImpuesto(),
            p.getPrecioFinal()
        }));
    }
    
    public void delete(int row){
       boolean resp = new ProductoBL().delete((Integer)this.v.getTable().getModel().getValueAt(row, 0));
        if(resp){
            JOptionPane.showMessageDialog(null, "Exito al eminar el registro", "Exito!!!", JOptionPane.INFORMATION_MESSAGE);
            this.findAll();//vuelve a cargar la tabla
            
        }else
            JOptionPane.showMessageDialog(null, "Algo sucedio con la base de datos", "Error!!!", JOptionPane.ERROR_MESSAGE);
            
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "agregar":
                if(this.v.getBtnAgregar().getLabel().equals("Agregar"))
                    save();
                else
                    update();
                break;
            case "buscar por nombre":
                findByName();
                break;
            case "buscar por tipo":
                findByType();
                break;
            case "modificar":
                if(isRowSelected()>=0)
                    modify(isRowSelected());
                else
                    JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "eliminar":
                if(isRowSelected()>=0)
                    delete(isRowSelected());
                else
                    JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        //JOptionPane.showMessageDialog(null, "Esto es un mensaje", "hola", JOptionPane.INFORMATION_MESSAGE);
    }

}
