package com.skilli.game.assignment.user.application.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 1l;

    @Schema(example = "ntelega0")
    private String username;
    @Schema(example = "UUfXwzHlbU")
    private String password;
}
