package br.com.lojadecarros.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lojadecarros.domain.Funcionario;
import br.com.lojadecarros.domain.ItemVenda;
import br.com.lojadecarros.domain.Produto;

public class ItemVendaDAOTest {
	
	@Test
	@Ignore 
	public void salvar(){
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(1L);
		
		ItemVenda itemvenda = new ItemVenda();
		itemvenda.setQuantidade(new Short("1"));
		itemvenda.setValorParcial(new BigDecimal("25852.00"));
		itemvenda.setFuncionario(funcionario);
		itemvenda.setProduto(produto);
		
		ItemVendaDAO itemvendaDAO = new ItemVendaDAO();
		itemvendaDAO.salvar(itemvenda);
		
		
		System.out.println("item adicionado com sucesso");
	}
	
	@Test
	public void excluir(){
		
		Long codigo = 3L;
		ItemVendaDAO itemvendaDAO = new ItemVendaDAO();
		ItemVenda itemvenda = itemvendaDAO.buscar(codigo);
		itemvendaDAO.excluir(itemvenda);
		
	}
}
