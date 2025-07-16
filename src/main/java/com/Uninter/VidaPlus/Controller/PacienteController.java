package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.PacienteMapper;
import com.Uninter.VidaPlus.Controller.Request.PacienteRequest;
import com.Uninter.VidaPlus.Controller.Response.PacienteResponse;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/paciente")
@RequiredArgsConstructor
@Tag(name = "Paciente", description = "Operações relacionadas aos pacientes do sistema")
public class PacienteController {

    private final PacienteService service;

    @Operation(summary = "Listar todos os pacientes")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso")
    @GetMapping("/")
    public ResponseEntity<List<PacienteResponse>> getPacientes() {
        List<PacienteEntity> pacientes = service.getPacientes();
        List<PacienteResponse> pacientesResponse = pacientes.stream().map(PacienteMapper::toPacienteResponse).toList();
        return ResponseEntity.ok(pacientesResponse);
    }

    @Operation(summary = "Buscar paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado",
                    content = @Content(schema = @Schema(implementation = PacienteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPaciente(@PathVariable Long id) {
        Optional<PacienteEntity> paciente = service.getPaciente(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(PacienteMapper.toPacienteResponse(paciente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Excluir paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        Optional<PacienteEntity> paciente = service.getPaciente(id);
        if (paciente.isPresent()) {
            service.deletePaciente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cadastrar novo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = PacienteResponse.class)))
    })
    @PostMapping("/")
    public ResponseEntity<PacienteResponse> registerPaciente(@RequestBody PacienteRequest paciente) {
        PacienteEntity pacienteEntity = service.registerPaciente(paciente);
        return ResponseEntity.ok(PacienteMapper.toPacienteResponse(pacienteEntity));
    }

    @Operation(summary = "Atualizar paciente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = PacienteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable Long id, @RequestBody PacienteRequest paciente) {
        Optional<PacienteEntity> pacienteEntity = service.getPaciente(id);
        if (pacienteEntity.isPresent()) {
            Optional<PacienteEntity> newPaciente = service.editPaciente(id, paciente);
            return ResponseEntity.ok(PacienteMapper.toPacienteResponse(newPaciente.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
