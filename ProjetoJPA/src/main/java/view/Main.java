package view;

import dao.FormaPagamentoDao;
import dao.PedidoConsultaDao;
import dao.ProdutoDao;
import dao.TipoDao;
import model.FormaPagamento;
import model.PedidoConsulta;
import model.Produto;
import model.Tipo;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String resultado = "";
        String botao[] = {"Produto", "Forma de pagamento", "Tipo", "Pedido", "Encerrar"};
        String botaoCadastro[] = {"Cadastrar", "Consultar", "Alterar", "Deletar", "Voltar"};
        int opcao = 0, opcaoCadastro;
        do {
            opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "CADASTRO", 0, 3, null, botao, 0);
            switch (opcao) {
                case 0: // Produto
                    ProdutoView produtoView = new ProdutoView();
                    opcaoCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção", "PRODUTO", 0, 3, null, botaoCadastro, 0);
                    switch (opcaoCadastro) {
                        case 0:
                            produtoView.cadastrarProduto();
                            break;
                        case 1:
                            resultado = produtoView.consultarProduto();
                            JOptionPane.showMessageDialog(null, resultado);
                            break;
                        case 2:
                            resultado = produtoView.consultarProduto();
                            int id = Integer.parseInt(JOptionPane.showInputDialog(
                                    resultado + "\nDigite o id do produto que deseja alterar"));
                            produtoView.alterarProduto(id);
                            JOptionPane.showMessageDialog(null,
                                    "Registro alterado com sucesso!");
                            break;
                        case 3:
                            resultado = produtoView.consultarProduto();
                            id = Integer.parseInt(JOptionPane.showInputDialog(
                                    resultado + "\nDigite o id do produto que deseja excluir"));
                            produtoView.removerProduto(id);
                            JOptionPane.showMessageDialog(null,
                                    "Registro excluído com sucesso!");
                            break;
                    }
                    break;
                case 1:// Forma de pagamento
                    FormaPagamentoView formaPagamentoView = new FormaPagamentoView();
                    opcaoCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção", "FORMA DE PAGAMENTO", 0, 3, null, botaoCadastro, 0);
                    switch (opcaoCadastro) {
                        case 0:
                            formaPagamentoView.cadastrarFormaPagamento();
                            break;
                        case 1:
                            resultado = formaPagamentoView.consultarFormaPagamento();
                            JOptionPane.showMessageDialog(null, resultado);
                            break;
                        case 2:
                            resultado = formaPagamentoView.consultarFormaPagamento();
                            int id = Integer.parseInt(JOptionPane.showInputDialog(
                                    resultado + "\nDigite o id que deseja alterar"));
                            formaPagamentoView.alterarFormaPagamento(id);
                            break;
                        case 3:
                            resultado = formaPagamentoView.consultarFormaPagamento();
                            id = Integer.parseInt(JOptionPane.showInputDialog(
                                    resultado + "\nDigite o id que deseja excluir"));
                            formaPagamentoView.removerFormaPagamento(id);
                            // removerFormaPagamento();
                            break;
                    }
                    break;
                case 2:// Tipo
                    TipoView tipoView = new TipoView();
                    opcaoCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção", "TIPO", 0, 3, null, botaoCadastro, 0);
                    switch (opcaoCadastro) {
                        case 0:
                            tipoView.cadastrarTipo();
                            break;
                        case 1:
                            resultado = tipoView.consultarTipo();
                            JOptionPane.showMessageDialog(null, resultado);
                            break;
                        case 2:
                            resultado = tipoView.consultarTipo();
                            int id = Integer.parseInt(JOptionPane.showInputDialog(
                                    resultado + "\nQual o id que degeja alterar?"));
                            tipoView.alterarTipo(id);
                            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso");
                            break;
                        case 3:
                            resultado = tipoView.consultarTipo();
                            id = Integer.parseInt(JOptionPane.showInputDialog(
                                    resultado + "\nQual o id que degeja excluir?"));
                            tipoView.removerTipo(id);
                            JOptionPane.showMessageDialog(null, "Registro excluído com sucesso");
                            break;
                    }
                    break;
                case 3:
                    resultado = consultarPedidoCompra();
                    JOptionPane.showMessageDialog(null, resultado);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Obrigada pela preferência!");
            }
        } while (opcao !=4);
    }

    public static String consultarPedidoCompra() {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoConsultaDao pedidoConsultaDao = new PedidoConsultaDao(em);

        List<PedidoConsulta> todosRegistros = pedidoConsultaDao.buscarTodos();
        int totalRgistros = todosRegistros.size();
        String resultado = "ID - DATA - CLIENTE - PRODUTO - QTDE\n";

        for (int i=0 ; i < totalRgistros ; i++) {
            resultado += todosRegistros.get(i).getIdAtendimento() + " - " +
                    todosRegistros.get(i).getDataAtendimento() + " - " +
                    todosRegistros.get(i).getNomeCliente() + " - " +
                    todosRegistros.get(i).getNomeProduto() + " - " +
                    todosRegistros.get(i).getQtde() + " - " + "\n";
        }
        return resultado;
    }










}
