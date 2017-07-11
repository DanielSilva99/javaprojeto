package br.com.lojadecarros.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import br.com.lojadecarros.domain.Pessoa;
import br.com.lojadecarros.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	public void salvar(){
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		System.out.println("pessoa encontrada " + pessoa.getNome());
		System.out.println("cpf "+ pessoa.getCpf());
		System.out.println("rg "+ pessoa.getRg());
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setSenhaSemCriptografia("vendedora");
		
		SimpleHash hash = new SimpleHash("md5" ,usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		
		usuario.setTipo('B');
		usuario.setPessoa(pessoa);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		
	}
}
