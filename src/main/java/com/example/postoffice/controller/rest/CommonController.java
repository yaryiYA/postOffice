package com.example.postoffice.controller.rest;

import com.example.postoffice.dto.AbstractRequestDto;
import com.example.postoffice.dto.AbstractResponseDto;
import com.example.postoffice.entity.AbstractEntity;
import com.example.postoffice.entity.enums.EntitySort;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface CommonController<E extends AbstractEntity,
        Q extends AbstractRequestDto,
        S extends AbstractResponseDto
        > {

    @GetMapping("/all")
    ResponseEntity<Page<S>> getAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                   @RequestParam(value = "sort", defaultValue = "ID_ASC") EntitySort sort);

    @GetMapping("/get")
    ResponseEntity<?> getEntity(@RequestParam("id") Long id);

    @PostMapping("/create")
    ResponseEntity<?> createEntity(@RequestBody Q requestEntity);

    @PutMapping("/update")
    ResponseEntity<?> updateEntity(@RequestBody Q requestEntity, @RequestParam("id") Long id);

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteEntity(@RequestParam("id") Long id);
}
