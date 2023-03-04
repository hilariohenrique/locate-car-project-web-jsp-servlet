package br.com.ada.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/cadastrarAluguel")
public class LocateCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomeCliente = req.getParameter("nomeCliente");
        String placa = req.getParameter("placa");
        String dataLocacao = req.getParameter("dataLocacao");
        String dataDevolucao = req.getParameter("dataDevolucao");

        req.setAttribute("nomeCliente",nomeCliente);
        req.setAttribute("placa",placa);
        req.setAttribute("dataLocacao",dataLocacao);
        req.setAttribute("dataDevolucao",dataDevolucao);

    }
}
