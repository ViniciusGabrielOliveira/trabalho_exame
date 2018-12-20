package br.gov.edu.fatec.dissertacoes.alunoGrad;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoGradRepository extends JpaRepository<AlunoGrad, Integer> {

	boolean existsByMatricula(Integer alu_matricula);

	Optional<AlunoGrad> findByAlu_matricula(Integer alu_matricula);

	boolean existsByAlu_matricula(Integer alu_matricula);

}
