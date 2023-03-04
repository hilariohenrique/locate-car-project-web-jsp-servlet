package br.com.ada.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/cadastroAluguel")
public class LocateCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nomeCliente = req.getParameter("nomeCliente");
        String placa = req.getParameter("placa");
        String dataLocacao = req.getParameter("dataLocacao");
        String dataDevolucao = req.getParameter("dataDevolucao");

        LocalDateTime dataInicialCalc =
        if (nomeCliente.equals("") || placa.equals("") || dataLocacao.equals("") || dataDevolucao.equals("")) {
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Não pode haver campos vazios!</h1>");
        } else {

            req.setAttribute("nomeCliente", nomeCliente);
            req.setAttribute("placa", placa);
            req.setAttribute("dataLocacao", dataLocacao);
            req.setAttribute("dataDevolucao", dataDevolucao);

            RequestDispatcher reqDisp = req.getRequestDispatcher("data.jsp");
            reqDisp.forward(req, resp);
        }
    }
    public double calcularValorDiarias(LocalDateTime dataAluguel, LocalDate dataDevolucao) {
        BigDecimal valor = Math.abs(ChronoUnit.SECONDS.between(dataAluguel, dataDevolucao)/86400d))
        return valor.floatValue() ;
    }
}
