package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.domain.dto.request.SkinConsultaRequest;
import com.ramonkaizer.skinstore.domain.dto.response.SkinResponse;
import com.ramonkaizer.skinstore.service.SkinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skins")
@AllArgsConstructor
public class SkinController {

    private final SkinService service;

    @GetMapping()
    public ResponseEntity<List<SkinResponse>> consultaSkins(SkinConsultaRequest request) {
        List<SkinResponse> skins = service.consultaSkins(request);
        return ResponseEntity.status(HttpStatus.OK).body(skins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkinResponse> consultaPorId(@PathVariable Long id) {
        SkinResponse response = service.consultaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/oferta-semana")
    public ResponseEntity<List<SkinResponse>> consultaOfertaSemana() {
        List<SkinResponse> response = service.consultaOfertaSemana();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}