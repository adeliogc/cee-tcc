package br.com.tcc.cee.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "EMPRESTIMOS")
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String numeroEmprestimo;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime data;

	@ManyToOne(optional = false)
	@JoinColumn(name = "funcionario_id", foreignKey = @ForeignKey(name = "fk_funcionario_emp"))
	private Funcionario funcionario;
	@Column(length = 400)
	private String observacao;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "emprestimo", fetch = FetchType.EAGER)
	@Valid
	@Size(min = 1, message = "Deve possuir pelo menos um item")
	@NotNull(message = "Não há itens incluídos no empréstimo")
	private List<EmprestimoItem> itens = new ArrayList<>();

	public Emprestimo() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroEmprestimo() {
		return numeroEmprestimo;
	}

	public void setNumeroEmprestimo(String numeroEmprestimo) {
		this.numeroEmprestimo = numeroEmprestimo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<EmprestimoItem> getItens() {
		return itens;
	}

	public void setItens(List<EmprestimoItem> itens) {
		this.itens = itens;
	}

}
