package br.gov.edu.fatec.dissertacoes.alunoPos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.gov.edu.fatec.dissertacoes.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@PrimaryKeyJoinColumn(name="alu_matricula")
public class AlunoPos extends Aluno{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3058245074261624692L;


	private String alp_agenciaBolsa;

	@Builder
	public AlunoPos(Integer alu_matricula, String alu_nome, String alp_agenciaBolsa) {
		super(alu_matricula, alu_nome);
		this.alp_agenciaBolsa = alp_agenciaBolsa;
		
	}


}