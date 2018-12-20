package br.gov.edu.fatec.dissertacoes.professor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import br.gov.edu.fatec.dissertacoes.dissertacao.Dissertacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@PrimaryKeyJoinColumn(name="alu_matricula")
public class Professor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 271489845984307993L;

	@Id
	private Integer pro_matricula;
	
	@NotNull
	private String pro_nome;
	
	@OneToMany
	private List<Dissertacao> pro_dicertacoes;

}