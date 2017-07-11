package br.com.lojadecarros.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import br.com.lojadecarros.domain.Estado;

public class EstadoDAOTest {

	@Test
	public void salvar() {

		Estado estado = new Estado();
		estado.setNome("Ceará");
		estado.setSigla("CE");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {

		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}

	}

	@Test
	@Ignore
	public void buscar() {

		Long codigo = 12L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("estado não encontrado");
		} else {

			System.out.println("registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

		}
	}

	@Test
	@Ignore
	public void excluir() {

		Long codigo = 9L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("estado não encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("registro excluido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

		}

	}

	@Test
	@Ignore
	
	public void editar() {
		Long codigo = 11L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		estado.setSigla("PE");
		estadoDAO.editar(estado);

	}
			
	
}
