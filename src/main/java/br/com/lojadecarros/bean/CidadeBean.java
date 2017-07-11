package br.com.lojadecarros.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lojadecarros.dao.CidadeDAO;
import br.com.lojadecarros.dao.EstadoDAO;
import br.com.lojadecarros.domain.Cidade;
import br.com.lojadecarros.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void novo() {
		try {
			cidade = new Cidade();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalInfo("Erro ao tentar salvar novo registro");
			erro.printStackTrace();

		}
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalInfo("Erro ao tentar listar os registros");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);

			cidade = new Cidade();
			cidades = cidadeDAO.listar();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			Messages.addFlashGlobalInfo("Cidade salva com sucesso.");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalInfo("Erro ao tentar salvar o registro");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);

			cidades = cidadeDAO.listar();

			Messages.addGlobalInfo("Registro removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a cidade");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalInfo("Erro ao tentar salvar novo registro");
			erro.printStackTrace();

		}
	}
}
