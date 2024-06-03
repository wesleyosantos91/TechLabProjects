package io.github.wesleyosantos91.api.v1.controller;

import io.github.wesleyosantos91.api.v1.openapi.PersonControllerOpenApi;
import io.github.wesleyosantos91.api.v1.request.PersonQueryRequest;
import io.github.wesleyosantos91.api.v1.request.PersonRequest;
import io.github.wesleyosantos91.api.v1.response.PersonResponse;
import io.github.wesleyosantos91.core.mapper.PersonMapper;
import io.github.wesleyosantos91.core.validation.Groups;
import io.github.wesleyosantos91.core.validation.Groups.Create;
import io.github.wesleyosantos91.domain.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/persons")
public class PersonController implements PersonControllerOpenApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);


    private final PersonService service;
    private final PersonMapper mapper;

    public PersonController(PersonService service, PersonMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Validated(Create.class) @RequestBody PersonRequest request) {
        LOGGER.debug("Function started 'create person'");
        final var person = service.save(mapper.toEntity(request));
        LOGGER.debug("finished function with sucess 'create person'");

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(person));
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> search(PersonQueryRequest query, Pageable page) {
        LOGGER.debug("Function started 'find person'");
        final var pageEntity = service.search(mapper.toEntity(query), page);
        LOGGER.debug("finished function with sucess 'find person'");
        return ResponseEntity.ok().body(mapper.toPageResponse(pageEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable String id) {
        LOGGER.debug("Function started 'getById person'");
        final PersonResponse response = mapper.toResponse(service.findById(id));
        LOGGER.debug("finished function with sucess 'getById person'");

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable String id, @Validated(Groups.Update.class) @RequestBody PersonRequest request) {
        LOGGER.debug("Function started 'update person'");
        final var person = service.update(id, mapper.toEntity(request));
        LOGGER.debug("finished function with sucess 'update person'");
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(person));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        LOGGER.debug("Function started 'delete person'");
        service.delete(id);
        LOGGER.debug("finished function with sucess 'delete person'");

        return ResponseEntity.noContent().build();
    }
}

