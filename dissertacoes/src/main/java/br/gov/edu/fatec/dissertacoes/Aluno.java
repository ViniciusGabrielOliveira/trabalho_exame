package br.gov.edu.fatec.dissertacoes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Aluno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2957084931640842778L;

	@Id
	private Integer alu_matricula;
	
	@NotNull
	private String alu_nome;
}
