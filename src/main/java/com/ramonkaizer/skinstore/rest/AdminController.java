package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.dto.request.SkinRequest;
import com.ramonkaizer.skinstore.service.SkinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final SkinService skinService;

    @PostMapping("/inserir-skin")
    public ResponseEntity<Void> inserirSkin(@RequestBody SkinRequest request) {
        skinService.inserirSkin(request);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
