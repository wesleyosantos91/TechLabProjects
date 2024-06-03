package io.github.wesleyosantos91.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.core.validation.Groups;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(name = "PersonRequest")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PersonRequest(
        @Schema(example = "fbacbce6-2129-11ef-a5d8-0242ac120002")
        @NotBlank(groups = Groups.Create.class)
        @Size(min = 11, max = 11, groups = { Groups.Create.class, Groups.Update.class })
        String cpf,
        @Schema(example = "Carlos Santos")
        @NotBlank(groups = Groups.Create.class)
        @Size(max = 80, groups = { Groups.Create.class, Groups.Update.class })
        String name,
        @Schema(example = "1988-11-30")
        @Past(groups = { Groups.Create.class, Groups.Update.class })
        LocalDate dateOfBirth,
        @Schema(example = "carlos@email.com")
        @Email(groups = { Groups.Create.class, Groups.Update.class })
        String email,
        @Schema(example = "987654321")
        String phone
) {
}
