/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import modelo.ProductoVista;

public class Main {
ProductoVista vista;
Control control;
    
    public static void main(String[] args) {
        Main principal = new Main();
        principal.iniciar();
    }
    public void iniciar(){
        control = new Control();
        vista = new ProductoVista("LogIn",control);
        
        login.setVisible(true);
    }
}
