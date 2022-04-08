package com.skilli.game.assignment.user.application.mapper;

import com.skilli.game.assignment.user.application.api.SignUpRequest;
import com.skilli.game.assignment.user.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUserEntity(SignUpRequest request);
}
