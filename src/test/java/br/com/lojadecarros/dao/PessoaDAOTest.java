package br.com.lojadecarros.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lojadecarros.domain.Cidade;
import br.com.lojadecarros.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	public void salvar() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(2L);

		Pessoa pessoa = new Pessoa();

		pessoa.setNome("priscila");
		pessoa.setCpf("55522255541");
		pessoa.setRg("2001002152654");
		pessoa.setRua("Rua ");
		pessoa.setNumero(new Short("30"));
		pessoa.setBairro("Passaré");
		pessoa.setCep("60863-720");
		pessoa.setComplemento("");
		pessoa.setTelefone("3452-8989");
		pessoa.setCelular("989003917");
		pessoa.setEmail("vendedora@gmail.com");
		pessoa.setCidade(cidade);

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
	}

	@Test
	@Ignore
	public void listar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();

		for (Pessoa pessoa : resultado) {

			System.out.println("codigo da pessoa " + pessoa.getCodigo());
			System.out.println("nome da pessoa " + pessoa.getNome());
			System.out.println("cpf da pessoa " + pessoa.getCpf());
			System.out.println("RG da pessoa " + pessoa.getRg());
			System.out.println("cidade da pessoa " + pessoa.getCidade().getNome());

		}
	}

	@Test
	@Ignore
	public void buscar() {

		Long codigo = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		System.out.println("codigo da pessoa " + pessoa.getCodigo());
		System.out.println("nome da pessoa " + pessoa.getNome());
		System.out.println("cpf da pessoa " + pessoa.getCpf());
		System.out.println("RG da pessoa " + pessoa.getRg());
		System.out.println("cidade da pessoa " + pessoa.getCidade().getNome());

	}
	@Test
	@Ignore
	public void excluir() {

		Long codigoPessoa = 1L;
		

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		pessoaDAO.excluir(pessoa);
		
		System.out.println("pessoa excuida foi");

		System.out.println("codigo da pessoa " + pessoa.getCodigo());
		System.out.println("nome da pessoa " + pessoa.getNome());
		System.out.println("cpf da pessoa " + pessoa.getCpf());
		System.out.println("RG da pessoa " + pessoa.getRg());
		System.out.println("cidade da pessoa " + pessoa.getCidade().getNome());

		
	}
	@Test
	@Ignore
	public void editar(){
		
		Long codigoPessoa = 1L;
		Long codigoCidade = 1L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		
		System.out.println("codigo da cidade " + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		
		System.out.println("pessoa a ser editada");

		System.out.println("codigo da pessoa " + pessoa.getCodigo());
		System.out.println("nome da pessoa " + pessoa.getNome());
		System.out.println("cpf da pessoa " + pessoa.getCpf());
		System.out.println("RG da pessoa " + pessoa.getRg());
		System.out.println("cidade da pessoa " + pessoa.getCidade().getNome());
		
		pessoa.setNome("rafaela costa");
		pessoa.setCidade(cidade);
		
		pessoaDAO.editar(pessoa);
		System.out.println("pessoa editada");

		
		System.out.println("codigo da pessoa " + pessoa.getCodigo());
		System.out.println("nome da pessoa " + pessoa.getNome());
		
		
	}

}
