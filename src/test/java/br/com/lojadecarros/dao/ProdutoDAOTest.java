package br.com.lojadecarros.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lojadecarros.domain.Fabricante;
import br.com.lojadecarros.domain.Produto;

public class ProdutoDAOTest {
	
	
	
	@Test
	public void salvar(){
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);
		
		Produto produto = new Produto();
		produto.setDescricao("Cruze");
		produto.setPreço(new BigDecimal("58000.00"));
		produto.setQuantidade(new Short("1"));
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("produto salvo com sucesso");
	}
	@Test
	@Ignore
	public void listar(){
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for (Produto produto : resultado){
			
			System.out.println("codigo do produto " + produto.getCodigo());
			System.out.println("quantidade " + produto.getQuantidade());
			System.out.println("fabricante " + produto.getFabricante().getDescricao());
			System.out.println("Preço do produto " + produto.getPreço());
			System.out.println("nome do produto " + produto.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		
		Long codigo = 1L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		System.out.println("codigo do produto " + produto.getCodigo());
		System.out.println("quantidade " + produto.getQuantidade());
		System.out.println("fabricante " + produto.getFabricante().getDescricao());
		System.out.println("Preço do produto " + produto.getPreço());
		System.out.println("nome do produto " + produto.getDescricao());
	}
	
	@Test
	@Ignore
	public void excluir(){
		
		Long codigo = 1L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		produtoDAO.excluir(produto);
		
		System.out.println("produto excluido");

		System.out.println("codigo do produto " + produto.getCodigo());
		System.out.println("quantidade " + produto.getQuantidade());
		System.out.println("fabricante " + produto.getFabricante().getDescricao());
		System.out.println("Preço do produto " + produto.getPreço());
		System.out.println("nome do produto " + produto.getDescricao());
	}
		
}
		
		

		
		
	

