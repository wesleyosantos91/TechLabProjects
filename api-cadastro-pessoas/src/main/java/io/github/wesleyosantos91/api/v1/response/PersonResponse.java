package io.github.wesleyosantos91.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PersonResponse(
        String id,
        String cpf,
        String name,
        LocalDate dateOfBirth,
        String email,
        String phone,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
