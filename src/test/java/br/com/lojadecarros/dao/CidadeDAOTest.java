package br.com.lojadecarros.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lojadecarros.domain.Cidade;
import br.com.lojadecarros.domain.Estado;

public class CidadeDAOTest {
	
	@Test
	public void salvar() {
		EstadoDAO estadoDAo = new EstadoDAO();
		Estado estado = estadoDAo.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setNome("Eusebio");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);

	}

	@Test
	@Ignore
	public void listar() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		for (Cidade cidade : resultado) {

			System.out.println("codigo da cidade " + cidade.getCodigo());
			System.out.println("Nome da cidade " + cidade.getNome());
			System.out.println("codigo do Estado " + cidade.getEstado().getCodigo());
			System.out.println("sigla do Estado " + cidade.getEstado().getSigla());
			System.out.println("Nome do Estado " + cidade.getEstado().getNome());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Long codigo = 2L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		System.out.println("codigo da cidade " + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());

	}
	@Test
	@Ignore
	public void excluir() {
		Long codigoCidade = 2L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		cidadeDAO.excluir(cidade);
		
		System.out.println("a cidade removida foi");
		System.out.println("codigo da cidade " + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());

	}
	@Test
	@Ignore
	public void editar(){
		
		Long codigoCidade = 2L;
		Long codigoEstado = 2L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("codigo do Estado " + estado.getCodigo());
		System.out.println("sigla do Estado " + estado.getSigla());
		System.out.println("Nome do Estado " + estado.getNome());
		
		System.out.println("A cidade a ser editada");
		System.out.println("codigo da cidade " + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());
		
		cidade.setNome("Santos");
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
		
		
		System.out.println(" cidade editada");
		System.out.println("codigo da cidade " + cidade.getCodigo());
		System.out.println("Nome da cidade " + cidade.getNome());
		System.out.println("codigo do Estado " + cidade.getEstado().getCodigo());
		System.out.println("sigla do Estado " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado " + cidade.getEstado().getNome());
		
		
	}
}
