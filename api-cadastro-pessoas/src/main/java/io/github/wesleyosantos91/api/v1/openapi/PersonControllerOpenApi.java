package io.github.wesleyosantos91.api.v1.openapi;

import io.github.wesleyosantos91.api.v1.request.PersonQueryRequest;
import io.github.wesleyosantos91.api.v1.request.PersonRequest;
import io.github.wesleyosantos91.api.v1.response.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "persons")
public interface PersonControllerOpenApi {

    @Operation(summary = "Registers a new person", responses = {
            @ApiResponse(responseCode = "201", description = "Person successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid person payload provided.",
                    content = @Content(schema = @Schema(ref = "ErrorResponse"))),
    })
    ResponseEntity<PersonResponse> create(@RequestBody(description = "Representation of the new person", required = true) PersonRequest request);


    @Operation(summary = "Search persons with filters")
    ResponseEntity<Page<PersonResponse>> search(@Parameter(hidden = true) PersonQueryRequest query,
                                                @Parameter(hidden = true) Pageable page);

    @Operation(summary = "Find person by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Person found successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid person ID provided.",
                    content = @Content(schema = @Schema(ref = "ErrorResponse"))),
            @ApiResponse(responseCode = "404", description = "Person with the given ID not found.",
                    content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    ResponseEntity<PersonResponse> findById(@Parameter(description = "Unique identifier of the person",
                                                       example = "fbacbce6-2129-11ef-a5d8-0242ac120002",
                                                       required = true) String id);

    @Operation(summary = "Update a person's registration", responses = {
            @ApiResponse(responseCode = "200", description = "Person successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid person payload provided.",
                    content = @Content(schema = @Schema(ref = "ErrorResponse"))),
            @ApiResponse(responseCode = "404", description = "Person with the given ID not found.",
                    content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    ResponseEntity<PersonResponse> update(@Parameter(description = "Unique identifier of the person",
                                                     example = "fbacbce6-2129-11ef-a5d8-0242ac120002",
                                                     required = true) String id,
                                          @RequestBody(description = "The updated representation of the person") PersonRequest request);

    @Operation(summary = "Deletes a person by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Person successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Person with the given ID not found.",
                    content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    ResponseEntity<Void> delete(@Parameter(description = "Unique identifier of the person",
            example = "fbacbce6-2129-11ef-a5d8-0242ac120002", required = true) String id);
}
