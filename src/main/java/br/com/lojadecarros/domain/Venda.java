package br.com.lojadecarros.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain{

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = false , precision = 12 , scale = 2)
	private BigDecimal valorTotal;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Funcionario funcionario;
	
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy = "venda")
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<ItemVenda> itensvendas;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<ItemVenda> getItensvendas() {
		return itensvendas;
	}
	
	public void setItensvendas(List<ItemVenda> itensvendas) {
		this.itensvendas = itensvendas;
	}
	
}
