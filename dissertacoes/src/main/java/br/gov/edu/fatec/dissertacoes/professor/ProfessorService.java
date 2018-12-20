package br.gov.edu.fatec.dissertacoes.professor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService{
	
	@Autowired
	ProfessorRepository professorRepository;

	public Professor save(Professor professor) {
		
		return professorRepository.existsByPro_matricula(professor.getPro_matricula())?professorRepository.save(professor):null;
	}

	public Professor remove(Professor professor) {
		professorRepository.delete(professor);
		return professor;
	}

	public Professor update(Professor professor, Professor proUpdate) {
		Optional<Professor> pro = professorRepository.findByPro_matricula(proUpdate.getPro_matricula());
		if(pro.isPresent()) {
			proUpdate.setPro_matricula(pro.get().getPro_matricula());
			return save(proUpdate);
		}
		return professor;
	}

	public Optional<Professor> findByPro_matricula(Integer pro_matricula) {
		Optional<Professor> professor = professorRepository.findByPro_matricula(pro_matricula);
		return professor.isPresent()?professor:null;
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

}
