package br.com.lojadecarros.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lojadecarros.dao.FabricanteDAO;
import br.com.lojadecarros.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar fabricante");
			erro.printStackTrace();
		}
	}

	public void novo() {

		fabricante = new Fabricante();
	}

	public void salvar() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);

		novo();
		fabricantes = fabricanteDAO.listar();

		try {
			Messages.addGlobalInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar fabricante");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);
			fabricantes = fabricanteDAO.listar();

			Messages.addGlobalInfo("Registro removido com sucesso");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o resistro");
			erro.printStackTrace();
		}

	}
	public void editar(ActionEvent evento){
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

	}
}
