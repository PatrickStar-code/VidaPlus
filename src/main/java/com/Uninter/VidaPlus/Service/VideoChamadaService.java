package com.Uninter.VidaPlus.Service;

import com.Uninter.VidaPlus.Controller.Request.ObservacaoRequest;
import com.Uninter.VidaPlus.Entity.VideoChamadaEntity;
import com.Uninter.VidaPlus.Repository.VideoChamadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoChamadaService {

    private final VideoChamadaRepository videoChamadaRepository;

    public List<VideoChamadaEntity> getVideoChamada() {
        return videoChamadaRepository.findAll();
    }

    public Optional<VideoChamadaEntity> getVideoChamadaById(Long id) {
        Optional<VideoChamadaEntity> videoChamada = videoChamadaRepository.findById(id);
        if (videoChamada.isEmpty()) return Optional.empty();
        return Optional.of(videoChamada.get());
    }

    public VideoChamadaEntity editVideoChamada(Long id, ObservacaoRequest observacao) {
        VideoChamadaEntity videoChamada = videoChamadaRepository.findById(id).get();
        videoChamada.setObservacoes(observacao.observacoes());
        return videoChamadaRepository.save(videoChamada);
    }
}
