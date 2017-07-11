package br.com.lojadecarros.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lojadecarros.dao.ClienteDAO;
import br.com.lojadecarros.dao.FuncionarioDAO;
import br.com.lojadecarros.dao.ProdutoDAO;
import br.com.lojadecarros.dao.VendaDAO;
import br.com.lojadecarros.domain.Cliente;
import br.com.lojadecarros.domain.Funcionario;
import br.com.lojadecarros.domain.ItemVenda;
import br.com.lojadecarros.domain.Produto;
import br.com.lojadecarros.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{
	
	private Venda venda;
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	
	private List<Venda> vendas;
	
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
		
	}
	
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}
	
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public void limpar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtos = produtoDAO.listar();
		
	}
	
	public void novo() {
		try {
			
			venda = new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));
			venda.setHorario(new Date());
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
			
			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}
	
	
	
	public void listar(){
		try{
		VendaDAO vendaDAO = new VendaDAO();
		 vendas = vendaDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("erro ao tentar listar vendas");
		}
		
	}
	
	public void adicionar(ActionEvent evento){
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		
		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			
			itemVenda.setValorParcial(produto.getPreço());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));
			
			itensVenda.add(itemVenda);
		}else{
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreço().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}	
		
		calcular();

	}
	public void atualizarPrecoParcial(){
		for (ItemVenda itemvenda :this.itensVenda) {
			itemvenda.setValorParcial(itemvenda.getProduto().getPreço().multiply(new BigDecimal(itemvenda.getQuantidade())));
		this.calcular();	
		}
	}
	
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou = -1;
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			if(itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())){
				achou = posicao;
			}
		}
		
		if(achou > -1){
			itensVenda.remove(achou);
		}
		calcular();
	}
	
	public void calcular(){
		venda.setValorTotal(new BigDecimal("0.00"));
		
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setValorTotal(venda.getValorTotal().add(itemVenda.getValorParcial()));
		}
	}
	
	public void finalizar(){
		
		try {
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
			
			venda.setHorario(new Date());
			
		}catch(RuntimeException erro){
			
			Messages.addFlashGlobalError("ocorreu um erro ao tentar finalizar compra");
		}
	}
	
	public void salvar() {
		try {
			if(venda.getValorTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a venda");
				return;
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);
			
			novo();
			
			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			erro.printStackTrace();
		}
	}
	
}


