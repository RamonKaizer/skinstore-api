package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.Skin;
import com.ramonkaizer.skinstore.dto.request.SkinConsultaRequest;
import com.ramonkaizer.skinstore.dto.request.SkinSaveRequest;
import com.ramonkaizer.skinstore.dto.response.SkinResponse;
import com.ramonkaizer.skinstore.repository.SkinRepository;
import com.ramonkaizer.skinstore.specification.SkinSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SkinService {

    private final SkinRepository repository;
    private final ModelMapper modelMapper;

    public void inserirSkin(SkinSaveRequest request) {
        Skin skin = modelMapper.map(request, Skin.class);
        repository.save(skin);
    }

    public List<SkinResponse> consultaOfertaSemana() {
        List<Skin> skins = repository.ofertaSemana();

        return skins.stream().map(skin -> modelMapper.map(skin, SkinResponse.class)).toList();
    }

    public SkinResponse consultaPorId(Long id) {
        Optional<Skin> skin = repository.findById(id);

        return modelMapper.map(skin, SkinResponse.class);
    }

    public List<SkinResponse> consultaSkins(SkinConsultaRequest filtros) {
        List<Skin> skins = repository.findAll(SkinSpecification.toSpec(filtros));

        return skins.stream().map(skin -> modelMapper.map(skin, SkinResponse.class)).toList();
    }
}
