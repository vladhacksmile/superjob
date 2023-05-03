package com.vladhacksmile.searchjob.dto.auth.refresh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class TokenRefreshDTO {
    @NotBlank
    private String refreshToken;
}
