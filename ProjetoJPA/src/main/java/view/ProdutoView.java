package view;

import dao.ProdutoDao;
import dao.TipoDao;
import model.Produto;
import model.Tipo;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class ProdutoView {
    public  void cadastrarProduto() {
        TipoView tipoView = new TipoView();
        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço"));
        int idTipo = Integer.parseInt(JOptionPane.showInputDialog(
                tipoView.consultarTipo() + "\n Informe o id do tipo do produto"));
        Produto produto = new Produto(nomeProduto, preco, idTipo);

        // conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        // iniciar a sessão
        em.getTransaction().begin();
        // cadastrar
        produtoDao.cadastrar(produto);
        // validar no banco
        em.getTransaction().commit();
        em.close();
    }
    public  String consultarProduto() {
        // conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        TipoDao tipoDao = new TipoDao(em);
        int totRegistro;
        // carrega ns variável todosRegistros o resultado da consulta
        List<Produto> todosRegistros = produtoDao.buscarTodos();

        String resultado = "ID - NOME - PRODUTO - PRECO - TIPO\n";
        totRegistro = todosRegistros.size(); // tamanho da lista
        for (int i = 0; i < totRegistro; i++) {
            Tipo tipo = tipoDao.buscarPorId(todosRegistros.get(i).getIdTipo());
            resultado += todosRegistros.get(i).getIdProduto() + " - " +
                    todosRegistros.get(i).getNomeProduto() + " - " +
                    todosRegistros.get(i).getValor() + " - " +
                    tipo.getAbreviacao() + "\n";
        }
        return resultado;
    }
    public  void removerProduto(int id) {
        // conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        // buscar produto por id
        Produto produto = produtoDao.buscarPorId(id);
        //iniciar transação
        em.getTransaction().begin();
        // remover
        produtoDao.removerProduto(produto);
        // validar a exclusão
        em.getTransaction().commit();
        em.close();
    }
    public  void alterarProduto(int id) {
        // conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto prod = produtoDao.buscarPorId(id);
        TipoView tipoView = new TipoView();
        String nomeProduto = JOptionPane.showInputDialog("Nome produto");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço"));
        int idTipo = Integer.parseInt(JOptionPane.showInputDialog(tipoView.consultarTipo() + "\n Informe o id do tipo do produto"));
        // iniciando a transação
        em.getTransaction().begin();
        // carrega na memória o registro
        produtoDao.alterarProduto(prod);
        // altera os campos da tabela
        prod.setNomeProduto(nomeProduto);
        prod.setValor(preco);
        prod.setIdTipo(idTipo);
        //valida a transação
        em.getTransaction().commit();
        em.close();
    }
}
