package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.Skin;
import com.ramonkaizer.skinstore.dto.request.SkinRequest;
import com.ramonkaizer.skinstore.dto.response.SkinResponse;
import com.ramonkaizer.skinstore.enums.StatusSkin;
import com.ramonkaizer.skinstore.repository.SkinRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkinService {

    private final SkinRepository repository;
    private final ModelMapper modelMapper;

    public List<SkinResponse> consultaSkinsPorStatus(StatusSkin status) {
        List<Skin> skins = repository.findByStatus(status);

        return skins.stream().map(skin -> modelMapper.map(skin, SkinResponse.class)).toList();
    }

    public void inserirSkin(SkinRequest request) {
        Skin skin = modelMapper.map(request, Skin.class);
        repository.save(skin);
    }
}
