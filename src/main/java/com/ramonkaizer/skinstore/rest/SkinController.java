package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.dto.request.SkinRequest;
import com.ramonkaizer.skinstore.dto.response.SkinResponse;
import com.ramonkaizer.skinstore.enums.StatusSkin;
import com.ramonkaizer.skinstore.service.SkinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skins")
@AllArgsConstructor
public class SkinController {

    private final SkinService service;

    @GetMapping()
    public ResponseEntity<List<SkinResponse>> consultaSkinsDisponiveis() {
        List<SkinResponse> response = service.consultaSkinsPorStatus(StatusSkin.DISPONIVEL);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
