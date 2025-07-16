package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.AgendaMapper;
import com.Uninter.VidaPlus.Controller.Request.AgendaRequest;
import com.Uninter.VidaPlus.Controller.Response.AgendaResponse;
import com.Uninter.VidaPlus.Entity.AgendaEntity;
import com.Uninter.VidaPlus.Service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/agenda")
@RequiredArgsConstructor
@Tag(name = "Agenda", description = "Operações relacionadas à agenda médica")
public class AgendaController {

    private final AgendaService agendaService;

    @Operation(summary = "Listar todas as agendas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/")
    public ResponseEntity<List<AgendaResponse>> getAgenda() {
        List<AgendaEntity> agenda = agendaService.getAgenda();
        List<AgendaResponse> agendaResponse = agenda.stream().map(AgendaMapper::toAgendaResponse).toList();
        return ResponseEntity.ok(agendaResponse);
    }

    @Operation(summary = "Buscar uma agenda por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agenda encontrada"),
            @ApiResponse(responseCode = "404", description = "Agenda não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> getAgendaById(@PathVariable Long id) {
        Optional<AgendaEntity> agenda = agendaService.getAgendaById(id);
        if (agenda.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AgendaMapper.toAgendaResponse(agenda.get()));
    }

    @Operation(summary = "Excluir uma agenda por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agenda excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agenda não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable Long id) {
        Optional<AgendaEntity> agenda = agendaService.getAgendaById(id);
        if (agenda.isEmpty()) return ResponseEntity.notFound().build();
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Criar uma nova agenda")
    @ApiResponse(responseCode = "200", description = "Agenda criada com sucesso")
    @PostMapping("/")
    public ResponseEntity<AgendaResponse> createAgenda(@RequestBody AgendaRequest agenda) {
        AgendaEntity agendaEntity = agendaService.registerAgenda(agenda);
        return ResponseEntity.ok(AgendaMapper.toAgendaResponse(agendaEntity));
    }

    @Operation(summary = "Atualizar uma agenda existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agenda atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agenda não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponse> updateAgenda(@RequestBody AgendaRequest agenda, @PathVariable Long id) {
        Optional<AgendaEntity> agendaEntity = agendaService.getAgendaById(id);
        if (agendaEntity.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AgendaMapper.toAgendaResponse(agendaService.editAgenda(id, agenda)));
    }
}
