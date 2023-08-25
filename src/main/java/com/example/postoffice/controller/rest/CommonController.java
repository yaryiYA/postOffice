package com.example.postoffice.controller.rest;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface CommonController<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto> {

    @GetMapping("/all")
    public ResponseEntity<List<S>> getAll();

    @GetMapping("/{UUID}")
    public ResponseEntity<?> getEntity(@PathVariable("UUID") UUID uuid);

    @PostMapping("/create")
    public ResponseEntity<?> createEntity(@RequestBody Q entity);

    @PutMapping("/update/{UUID}")
    public ResponseEntity<?> updateEntity(@RequestBody Q entity, @PathVariable("UUID") UUID uuid);

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteEntity(@PathVariable("UUID") UUID uuid);
}
