package br.com.lojadecarros.dao;

import java.util.Date;

import org.junit.Test;

import br.com.lojadecarros.domain.Funcionario;
import br.com.lojadecarros.domain.Pessoa;

public class FuncionarioDAOTest {
	@Test
	public void salvar(){
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		System.out.println("codigo da pessoa " + pessoa.getCodigo());
		System.out.println("nome da pessoa " + pessoa.getNome());
		System.out.println("cpf da pessoa " + pessoa.getCpf());
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiraTrabalho("120485-88");
		funcionario.setDataAdmissao(new Date());
		funcionario.setPessoa(pessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
		
		
	}

}
