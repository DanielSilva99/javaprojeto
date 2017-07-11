package br.com.lojadecarros.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lojadecarros.domain.Fabricante;

public class FabricanteDAOTest {

	@Test
	public void salvar() {

		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("chevrolet");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);

	}

	@Test
	@Ignore
	public void listar() {

		FabricanteDAO fabricaDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricaDAO.listar();

		for (Fabricante fabricante : resultado) {

			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}

	}

	@Test
	@Ignore
	public void buscar() {

		Long codigo = 4L;
		FabricanteDAO fabricaDAO = new FabricanteDAO();
		Fabricante fabricante = fabricaDAO.buscar(codigo);

		if (fabricante == null) {

			System.out.println("fabricante não encontrado");
		} else {

			System.out.println("registro encontrado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());

		}

	}

	@Test
	@Ignore
	public void excluir() {

		Long codigo = 8L;
		FabricanteDAO fabricaDAO = new FabricanteDAO();
		Fabricante fabricante = fabricaDAO.buscar(codigo);

		if (fabricante == null) {

			System.out.println("fabricante não encontrado");
		} else {

			fabricaDAO.excluir(fabricante);
			System.out.println("fabricante removido:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());

		}

	}

	@Test
	@Ignore
	public void editar() {

		Long codigo = 3L;
		FabricanteDAO fabricaDAO = new FabricanteDAO();
		Fabricante fabricante = fabricaDAO.buscar(codigo);

		if (fabricante == null) {

			System.out.println("fabricante não encontrado");
		} else {

			fabricante.setDescricao("mercedes");
			fabricaDAO.editar(fabricante);
			System.out.println("fabricante editado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());

		}
	}
}