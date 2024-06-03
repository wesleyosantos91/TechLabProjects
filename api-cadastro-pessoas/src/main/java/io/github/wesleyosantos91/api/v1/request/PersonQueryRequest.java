package io.github.wesleyosantos91.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PersonQueryRequest(
        String id,
        String cpf,
        String name,
        LocalDate dateOfBirth,
        String email,
        String phone
) {
}
