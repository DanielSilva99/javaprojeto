package br.com.lojadecarros.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.lojadecarros.dao.EstadoDAO;
import br.com.lojadecarros.domain.Estado;
import br.com.lojadecarros.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {

		try {

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Erro ao listar estados do banco de deus");
			erro.printStackTrace();

		}
	}

	public void novo() {

		estado = new Estado();

	}

	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);

			novo();
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Erro ao salvar estado no banco de deus");
			erro.printStackTrace();

		}
	}

	public void excluir(ActionEvent evento) {

		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			estados = estadoDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover registro");
			erro.printStackTrace();
		}
	}
	public void editar(ActionEvent evento){
		
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		
	}
	public void imprimir(){
		try{
			
		String caminho = Faces.getRealPath("/relatorios/estados.jasper");
		
		Map<String, Object> parametros = new HashMap<>();
		
		Connection conexao = HibernateUtil.getConexao();
		
		JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
		JasperPrintManager.printReport(relatorio, true);
		
		}catch(JRException erro){
			Messages.addGlobalError("erro ao tentar imprimir relatorio");
		}
	}
}
