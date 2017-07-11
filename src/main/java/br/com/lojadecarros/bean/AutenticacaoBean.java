package br.com.lojadecarros.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.lojadecarros.dao.UsuarioDAO;
import br.com.lojadecarros.domain.Pessoa;
import br.com.lojadecarros.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {

		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {

			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarioLogado = usuariodao.autenticar(usuario.getPessoa().getEmail(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("usuario ou senha invalidos");
				return;
			} else {
				Faces.redirect("./pages/principal.xhtml");
			}
		} catch (IOException erro) {
			Messages.addGlobalError(erro.getMessage());
			erro.printStackTrace();
		}
	}

	public String sair() {
		usuarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}

	public boolean temPermissoes(List<String> permissoes) {
		for (String permissao : permissoes) {
			if (usuarioLogado.getTipo() == permissao.charAt(0)){
				return true;
			}
			
		}
		return false;
	}
}
