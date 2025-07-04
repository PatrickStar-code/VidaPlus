package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.AgendaMapper;
import com.Uninter.VidaPlus.Controller.Mapper.VideoChamadaMapper;
import com.Uninter.VidaPlus.Controller.Request.ObservacaoRequest;
import com.Uninter.VidaPlus.Controller.Response.AgendaResponse;
import com.Uninter.VidaPlus.Controller.Response.VideoChamadaResponse;
import com.Uninter.VidaPlus.Entity.AgendaEntity;
import com.Uninter.VidaPlus.Entity.VideoChamadaEntity;
import com.Uninter.VidaPlus.Service.VideoChamadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/videoChamada")
@RequiredArgsConstructor
public class VideoChamadaController {

    private final VideoChamadaService videoChamadaService;

    @GetMapping("/")
    public ResponseEntity<List<VideoChamadaResponse>> getVideoChamada() {
        List<VideoChamadaEntity> videoChamada = videoChamadaService.getVideoChamada();
        List<VideoChamadaResponse> videoChamadaResponse = videoChamada.stream().map(VideoChamadaMapper::toResponse).toList();
        return ResponseEntity.ok(videoChamadaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoChamadaResponse> getVideoChamadaById(@PathVariable Long id) {
        Optional<VideoChamadaEntity> videoChamada = videoChamadaService.getVideoChamadaById(id);
        if (videoChamada.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(VideoChamadaMapper.toResponse(videoChamada.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoChamadaResponse> updateVideoChamada(@PathVariable Long id, @RequestBody ObservacaoRequest Observacao) {
        Optional<VideoChamadaEntity> videoChamadaEntity = videoChamadaService.getVideoChamadaById(id);
        if (videoChamadaEntity.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(VideoChamadaMapper.toResponse(videoChamadaService.editVideoChamada(id, Observacao)));
    }


}
