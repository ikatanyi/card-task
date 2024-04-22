package com.logicea.card.service.impl;

import com.logicea.card.exception.APIException;
import com.logicea.card.mapper.RoleMapper;
import com.logicea.card.model.dto.RoleDto;
import com.logicea.card.model.entity.RoleEntity;
import com.logicea.card.repository.RoleRepository;
import com.logicea.card.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kennedy Ikatanyi
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleDto createRole(RoleDto data) {

        if (roleRepository.findByName(data.name()).isPresent()) {
            throw APIException.conflict("Role with name {0} already exists.", data.name());
        }

        var role = new RoleEntity(data.name(), data.description());
        var savedRole = roleRepository.save(role);
        return roleMapper.mapRoleEntityToRoleDto(savedRole);
    }

    public RoleEntity getRoleByName(String name) {
        return roleRepository.findByName(name)
            .orElseThrow(() -> APIException.notFound("Role with name {0} does not exists.", name));
    }

    public RoleEntity getRoleWithNoFoundDetection(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> APIException.notFound("Role with id {0} not found", id));
    }

    public Page<RoleDto> retrieveAllRoles(Pageable page) {
        return roleRepository.findAll(page).map(roleMapper::mapRoleEntityToRoleDto);
    }

    public String changeRoleStatus(Long id, String command) {
        RoleEntity role = getRoleWithNoFoundDetection(id);
        if (command.equals("enabled")) {
            role.setDisabled(Boolean.FALSE);
        } else {
            role.setDisabled(Boolean.TRUE);
        }
        roleRepository.save(role);
        return "RoleEntity " + command;
    }

    public RoleDto updateRole(Long id, RoleDto data) {
        RoleEntity role = getRoleWithNoFoundDetection(id);
        if (!role.getName().equals(data.name())) {
            role.setName(data.name());
        }
        if (!role.getDescription().equals(data.description())) {
            role.setDescription(data.description());
        }
        var roleEntity = roleRepository.save(role);
        return roleMapper.mapRoleEntityToRoleDto(roleEntity);
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
