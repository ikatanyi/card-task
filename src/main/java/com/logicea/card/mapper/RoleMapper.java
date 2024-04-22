package com.logicea.card.mapper;

import com.logicea.card.Enums.RoleEnum;
import com.logicea.card.model.dto.RoleDto;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.entity.RoleEntity;
import com.logicea.card.model.entity.UserEntity;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper {

   @Mapping(expression = "java(role.name())", target ="name")
   @Mapping(expression = "java(role.name())", target ="description")
   RoleEntity mapRoleDtoToRoleEntity(RoleEnum role);

   default Set<RoleEntity> map(RoleEnum value){
      Set<RoleEntity> roles = new HashSet<>();
      roles.add(new RoleEntity(value.name(), value.name()));
      return roles;
   }

   RoleDto mapRoleEntityToRoleDto(RoleEntity roleEntity);
}
