package br.gov.edu.fatec.dissertacoes.professor;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.edu.fatec.dissertacoes.Response;

@RestController
@RequestMapping("/Professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping(path = "/new")
	public ResponseEntity<Response<Professor>> cadastrar(@Valid @RequestBody Professor professor, BindingResult result) {
		Response<Professor> response = new Response<Professor>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Professor alunoSalvo = this.professorService.save(professor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professor.getPro_matricula())
				.toUri();
		response.setData(alunoSalvo);
		return ResponseEntity.created(location).body(response);
	}
	
	@PutMapping("/put/{pro_matricula}")
	public ResponseEntity<Response<Professor>> atualizar(@Valid @RequestBody Professor professor, BindingResult result, @PathVariable("pro_matricula") Integer pro_matricula){
		Response<Professor> response = new Response<Professor>();
		

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		
		Optional<Professor> professorUpdate = professorService.findByPro_matricula(pro_matricula);

		Professor alunoSalvo = professorService.update(professor, professorUpdate.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professor.getPro_matricula()).toUri();
		response.setData(alunoSalvo);
		return ResponseEntity.created(location).body(response);
	 }
	
	@GetMapping
	public ResponseEntity<List<Professor>> listar() {
		List<Professor> alunos = professorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(alunos);
	}

	@GetMapping(path = "/{pro_matricula}")
	public ResponseEntity<Response<Professor>> buscar(@PathVariable("pro_matricula") Integer pro_matricula) {
  
		Optional<Professor> aluno = professorService.findByPro_matricula(pro_matricula);
		Response<Professor> response = new Response<Professor>();
		response.setData(aluno.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{pro_matricula}")
    public ResponseEntity<Response<Professor>> deletePersona(@PathVariable("pro_matricula") Integer pro_matricula) {

		if (professorService.professorRepository.existsByPro_matricula(pro_matricula)) {
			professorService.remove(professorService.findByPro_matricula(pro_matricula).get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
    }
	
}
