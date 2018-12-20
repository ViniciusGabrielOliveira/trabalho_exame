package br.gov.edu.fatec.dissertacoes.alunoGrad;

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
public class AlunoGrad extends Aluno{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3710680265038928619L;

	@Builder
	public AlunoGrad(Integer alu_matricula, String alu_nome) {
		super(alu_matricula, alu_nome);		
	}


}