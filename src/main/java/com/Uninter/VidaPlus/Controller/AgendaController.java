package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.AgendaMapper;
import com.Uninter.VidaPlus.Controller.Request.AgendaRequest;
import com.Uninter.VidaPlus.Controller.Response.AgendaResponse;
import com.Uninter.VidaPlus.Entity.AgendaEntity;
import com.Uninter.VidaPlus.Service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping("/")
    public ResponseEntity<List<AgendaResponse>> getAgenda() {
        List<AgendaEntity> agenda = agendaService.getAgenda();
        List<AgendaResponse> agendaResponse = agenda.stream().map(AgendaMapper::toAgendaResponse).toList();
        return ResponseEntity.ok(agendaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> getAgendaById(@PathVariable Long id) {
        Optional<AgendaEntity> agenda = agendaService.getAgendaById(id);
        if (agenda.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AgendaMapper.toAgendaResponse(agenda.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable Long id) {
        Optional<AgendaEntity> agenda = agendaService.getAgendaById(id);
        if (agenda.isEmpty()) return ResponseEntity.notFound().build();
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<AgendaResponse> createAgenda(@RequestBody AgendaRequest agenda) {
        AgendaEntity agendaEntity = agendaService.registerAgenda(agenda);
        return ResponseEntity.ok(AgendaMapper.toAgendaResponse(agendaEntity));
    }
}
