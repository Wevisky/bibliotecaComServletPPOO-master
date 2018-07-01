package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.AlunoDAO;
import com.exemplo.biblioteca.entidades.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cria-aluno-servlet")
public class CriarAlunoServlet extends HttpServlet {

    @Inject
    private AlunoDAO dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");

        try {
            String nome = req.getParameter("nome");
            String idade = req.getParameter("idade");
            String matricula = req.getParameter("matricula");

            Aluno novoAluno = new Aluno();
            novoAluno.setNome(nome);
            novoAluno.setIdade(Integer.valueOf(idade));
            novoAluno.setMatricula(Integer.valueOf(matricula));
            dao.adicionaAluno(novoAluno);
            out.println("<h1>Aluno adicionado com sucesso</h1>");
        } catch (NumberFormatException ex) {
            out.println("<h1>Erro ao adicionar um novo Aluno</h1>");
        }
        out.println("<a href=\"/bibliotecaComServlet\">Biblioteca</a>");
        out.println("</body></html>");
    }

}
