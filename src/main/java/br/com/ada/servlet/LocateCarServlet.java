package br.com.ada.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.io.IOException;
import java.util.List;


@WebServlet("/cadastroAluguel")
public class LocateCarServlet extends HttpServlet {
    private BigDecimal valorDiaria = BigDecimal.valueOf(100);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String nomeCliente = req.getParameter("nomeCliente");
        String placa = req.getParameter("placa");
        String dataLocacao = req.getParameter("dataLocacao").replace('/', '-');
        String dataDevolucao = req.getParameter("dataDevolucao").replace('/', '-');
        BigDecimal valorLocacao = calcularValorDiarias(dataLocacao, dataDevolucao);
        if (valorLocacao.equals(null)) {
            resp.getWriter().println("<h3>Data digitada em formato incorreto. Formato correto é ano-mês-dia -> [aaaa-mm-dd)]</h3>");
        }

        if (nomeCliente.equals("") || placa.equals("") || dataLocacao.equals("") || dataDevolucao.equals("")) {
            resp.getWriter().println("<h2>Não pode haver campos vazios! Retorne a página anterior e preencha todos os campos.</h2>");
        } else {
            req.setAttribute("nomeCliente", nomeCliente);
            req.setAttribute("placa", placa);
            req.setAttribute("dataLocacao", dataLocacao);
            req.setAttribute("dataDevolucao", dataDevolucao);

            req.setAttribute("valorAluguel", valorLocacao);

            RequestDispatcher reqDisp = req.getRequestDispatcher("data.jsp");
            reqDisp.forward(req, resp);
        }
    }


    private BigDecimal calcularValorDiarias(String dataLocacao, String dataDevolucao) {
        BigDecimal valor = null;
        try {
            LocalDate dataInicialCalc = LocalDate.parse(dataLocacao, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate dataFinalCalc = LocalDate.parse(dataDevolucao, DateTimeFormatter.ISO_LOCAL_DATE);
            valor = this.valorDiaria.multiply(BigDecimal.valueOf(Math.abs(ChronoUnit.DAYS.between(dataInicialCalc, dataFinalCalc))));
        } catch (IllegalArgumentException e) {
        }
        return valor;
    }
}
