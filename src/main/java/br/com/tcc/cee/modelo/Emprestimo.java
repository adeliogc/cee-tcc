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
@Table(name="EMPRESTIMOS")
public class Emprestimo implements Serializable{
	
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
	
	

}
