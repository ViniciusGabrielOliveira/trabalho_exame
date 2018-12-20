package br.gov.edu.fatec.dissertacoes.dissertacao;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.gov.edu.fatec.dissertacoes.alunoPos.AlunoPos;
import br.gov.edu.fatec.dissertacoes.professor.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dissertacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String tipo;
	
	private Calendar ano;
	
	private String titulo;
	
	@OneToOne
	@JoinColumn(name="alp_alu_matricula")
	private AlunoPos dir_alp_alu_matricula;
	
	@ManyToOne
	@JoinColumn(name="pro_matricula")
	private Professor dir_pro_matricula;
	
}
