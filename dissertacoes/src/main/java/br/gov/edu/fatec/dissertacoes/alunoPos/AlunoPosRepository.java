package br.gov.edu.fatec.dissertacoes.alunoPos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoPosRepository extends JpaRepository<AlunoPos, Integer> {
	
	boolean existsByAlu_matricula(Integer alu_matricula);

	Optional<AlunoPos> findByAlu_matricula(Integer alu_matricula);

}
