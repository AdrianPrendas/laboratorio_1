package cr.ac.una.moviles.lab1.controller;

import cr.ac.una.moviles.lab1.domain.Jsonable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import cr.ac.una.moviles.lab1.domain.*;
import cr.ac.una.moviles.lab1.bl.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author _Adrián_Prendas_
 */
@WebServlet(name = "ConductorServlet", urlPatterns = {"/DriverServices"})
public class ServletController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");//text/html;charset=UTF-8
            RuntimeTypeAdapterFactory<Jsonable> rta = RuntimeTypeAdapterFactory.of(Jsonable.class, "_class")
                    .registerSubtype(Tipo.class, "Tipo")
                    .registerSubtype(Producto.class, "Producto");

            Gson gson = new GsonBuilder().registerTypeAdapterFactory(rta).setDateFormat("dd/MM/yyyy").create();
            String json;

            TipoBL tbl = new TipoBL();
            ProductoBL pbl = new ProductoBL();
            Producto product = new Producto();
            ArrayList<Producto> lista;
            boolean resp;

            String accion = request.getParameter("action");
System.out.println("accion: " + accion);
            switch (accion) {
                case "saveProduct":
                    json = request.getParameter("product");
                    product = gson.fromJson(json, Producto.class);
System.out.println(product);
                    resp = pbl.create(product);
                    json = gson.toJson(((resp)?new Exception("Exito"):new Exception("Error de codigo duplicada")));
                    out.print(json);
                    break;
                case "findAll":
                    lista = new ArrayList(pbl.findAll());
                    json = gson.toJson(lista);
System.out.println(json);
                    out.print(json);
                    break;
                case "findByName":
                        json = request.getParameter("name");
System.out.println(json);
                        lista = new ArrayList(pbl.findByName(json));
                        json = gson.toJson(lista);
System.out.println(json);
                        out.print(json);
                    break;
                    case "findByType":
                        json = request.getParameter("type");
System.out.println(json);
                        lista = new ArrayList(pbl.findByType(json));
                        json = gson.toJson(lista);
System.out.println(json);
                        out.print(json);
                    break;
                    case "findById":
                        json = request.getParameter("id");
System.out.println(json);
                        try{
                            int key = Integer.parseInt(json);
                            Producto pro = pbl.findByKey(key);
                            json = gson.toJson(pro);
                        }catch(Exception e){
                            json = gson.toJson(new Exception("Error: el pk es un entero"));
                        }
System.out.println(json);
                        out.print(json);
                    break;
                    case "update":
                    json = request.getParameter("product");
                    product = gson.fromJson(json, Producto.class);
System.out.println(product);
                    resp = pbl.update(product);
                    json = gson.toJson(((resp)?new Exception("Exito"):new Exception("Error al actualizar")));
                    out.print(json);
                        break;
                    case "delete":
                    json = request.getParameter("id");
System.out.println("Eliminando id: "+json);
                    resp = pbl.delete(Integer.parseInt(json));
                    json = gson.toJson(((resp)?new Exception("Exito"):new Exception("Error al tratar de eliminar el Producto: "+json)));
                    out.print(json);
                    break;
                        
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
