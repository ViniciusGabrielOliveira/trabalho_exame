package br.gov.edu.fatec.dissertacoes.alunoPos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoPosService {
	
	@Autowired
	public AlunoPosRepository alunoPosRepository;

	public AlunoPos save(AlunoPos alunoPos) {
		
		return alunoPosRepository.existsByAlu_matricula(alunoPos.getAlu_matricula())?alunoPosRepository.save(alunoPos):null;
	}

	public AlunoPos remove(AlunoPos alunoPos) {
		alunoPosRepository.delete(alunoPos);
		return alunoPos;
	}

	public AlunoPos update(AlunoPos alunoPos, AlunoPos alunoPosUpdate) {
		Optional<AlunoPos> al = findByAlu_matricula(alunoPos.getAlu_matricula());
		if(al.isPresent()) {
			alunoPosUpdate.setAlu_matricula(al.get().getAlu_matricula());
			return save(alunoPosUpdate);
		}
		return alunoPos;
	}

	public Optional<AlunoPos> findByAlu_matricula(Integer alu_matricula) {
		Optional<AlunoPos> alunoPos = alunoPosRepository.findByAlu_matricula(alu_matricula);
		return alunoPos.isPresent()?alunoPos:null;
	}

	public List<AlunoPos> findAll() {
		return alunoPosRepository.findAll();
	}

}
