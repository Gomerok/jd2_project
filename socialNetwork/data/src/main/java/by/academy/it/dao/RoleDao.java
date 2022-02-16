package by.academy.it.dao;

import by.academy.it.pojo.Role;

import java.io.Serializable;

public interface RoleDao {

    Serializable saveRole(Role role);

    Role readRoleByName(String roleName);

    int deleteRoleByName(String roleName);
}
