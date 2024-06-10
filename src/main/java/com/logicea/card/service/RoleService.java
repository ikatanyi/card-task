package com.logicea.card.service;

import com.logicea.card.model.dto.RoleDto;
import com.logicea.card.model.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface RoleService {

  RoleDto createRole(RoleDto data);

  RoleEntity getRoleByName(String name);

  RoleEntity getRoleWithNoFoundDetection(Long id);

  Page<RoleDto> retrieveAllRoles(Pageable page);

  String changeRoleStatus(Long id, String command);

  RoleDto updateRole(Long id, RoleDto data);

  void deleteRole(Long roleId);

}
