package ar.com.julio.controlclientes.web;

import ar.com.julio.controlclientes.datos.ClienteDaoJDBC;
import ar.com.julio.controlclientes.dominio.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    // Sera el encargado de recuperar los objetos de tipo cliente y compartirlo con un JSP (vista)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        request.setAttribute("clientes", clientes);
        request.setAttribute("totalClientes", clientes.size());
        request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        request.getRequestDispatcher("/clientes.jsp").forward(request, response);
    }
    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}
