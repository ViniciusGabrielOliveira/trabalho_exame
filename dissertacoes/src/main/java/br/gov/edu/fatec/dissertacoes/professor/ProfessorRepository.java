package br.gov.edu.fatec.dissertacoes.professor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	boolean existsByPro_matricula(Integer pro_matricula);

	Optional<Professor> findByPro_matricula(Integer pro_matricula);

}
