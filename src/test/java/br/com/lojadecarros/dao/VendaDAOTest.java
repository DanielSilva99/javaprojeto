package br.com.lojadecarros.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lojadecarros.domain.Cliente;
import br.com.lojadecarros.domain.Funcionario;
import br.com.lojadecarros.domain.Venda;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar(){
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);
		
		
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		
		
		Venda venda = new Venda();
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal("50850.00"));
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
		
		System.out.println("venda salva com sucesso");
	}
	@Test
	public void excluir(){
		
		Long codigo = 3L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);
		vendaDAO.excluir(venda);
	}
}
