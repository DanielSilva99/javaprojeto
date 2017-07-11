package br.com.lojadecarros.dao;

import java.util.Date;

import org.junit.Test;

import br.com.lojadecarros.domain.Cliente;
import br.com.lojadecarros.domain.Pessoa;

public class ClienteDAOTest {
	@Test
	public void salvar(){
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		
		System.out.println("cliente salvo com sucesso");
	}

}
