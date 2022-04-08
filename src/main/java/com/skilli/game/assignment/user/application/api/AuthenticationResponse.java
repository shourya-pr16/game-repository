package com.skilli.game.assignment.user.application.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1l;
    private String jwtToken;
}
