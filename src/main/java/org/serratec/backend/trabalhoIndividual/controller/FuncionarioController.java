package org.serratec.backend.trabalhoIndividual.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.trabalhoIndividual.domain.Funcionario;
import org.serratec.backend.trabalhoIndividual.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Operation(summary = "Lista todos os funcionários", 
			description = "A resposta lista os dados dos funcionários")
	@ApiResponses(value = {
					@ApiResponse(responseCode = "200",
					content = {@Content(schema = @Schema(implementation = Funcionario.class), 
					mediaType = "application/json")}, description = "Retorna todos os Funcionários"),
					@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
					@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	})

	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
			Optional<Funcionario>funcionarioOpt = funcionarioRepository.findById(id);
			if (funcionarioOpt.isPresent()) {
				Funcionario funcionario = funcionarioOpt.get();
				return ResponseEntity.ok(funcionario);
			}
			return ResponseEntity.notFound().build();
			}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario inserir(@RequestBody Funcionario funcionario) {
			return funcionarioRepository.save(funcionario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
		boolean funcionarioExists = funcionarioRepository.existsById(id);
		if (funcionarioExists) {
			funcionario.setId(id);
			funcionario = funcionarioRepository.save(funcionario);
			return ResponseEntity.ok(funcionario);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		boolean funcionarioExists = funcionarioRepository.existsById(id);
		if (funcionarioExists) {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
