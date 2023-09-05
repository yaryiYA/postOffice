package com.example.postoffice.controller.rest;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface CommonController<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto> {

    @GetMapping("/all")
    public ResponseEntity<List<S>> getAll(@RequestParam(value = "pageNo",defaultValue ="0") Integer pageNo,
                                          @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);

    @GetMapping("/{ID}")
    public ResponseEntity<?> getEntity(@PathVariable("ID") Long id);

    @PostMapping("/create")
    public ResponseEntity<?> createEntity(@RequestBody Q entity);

    @PutMapping("/update/{ID}")
    public ResponseEntity<?> updateEntity(@RequestBody Q entity, @PathVariable("ID") Long id);

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<String> deleteEntity(@PathVariable("ID") Long id);
}
