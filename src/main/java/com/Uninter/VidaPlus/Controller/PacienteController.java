package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.PacienteMapper;
import com.Uninter.VidaPlus.Controller.Request.PacienteRequest;
import com.Uninter.VidaPlus.Controller.Response.PacienteResponse;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @GetMapping("/")
    public ResponseEntity<List<PacienteResponse>> getPacientes() {
        List<PacienteEntity> pacientes = service.getPacientes();
        List<PacienteResponse> pacientesResponse = pacientes.stream().map(PacienteMapper::toPacienteResponse).toList();
        return ResponseEntity.ok(pacientesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPaciente(@PathVariable Long id) {
        Optional<PacienteEntity> paciente = service.getPaciente(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(PacienteMapper.toPacienteResponse(paciente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        Optional<PacienteEntity> paciente = service.getPaciente(id);
        if (paciente.isPresent()) {
            service.deletePaciente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<PacienteResponse> registerPaciente(@RequestBody PacienteRequest paciente) {
        PacienteEntity pacienteEntity = service.registerPaciente(paciente);
        return ResponseEntity.ok(PacienteMapper.toPacienteResponse(pacienteEntity));
    }

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
