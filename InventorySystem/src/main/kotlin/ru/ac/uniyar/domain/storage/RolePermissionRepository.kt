package ru.ac.uniyar.domain.storage

import java.util.*
//четвертое
class RolePermissionRepository(
    rolePermissionRepository: List<RolePermissions> = emptyList()
) {
    val roles = rolePermissionRepository.associateBy { it.id }.toMutableMap()

    fun fetch(id: UUID): RolePermissions? = roles[id]

    fun add(rolePermissions: RolePermissions){
        if(roles.containsKey(rolePermissions.id)) return
        roles[rolePermissions.id] = rolePermissions
    }

    fun list() = roles.values.toList()
}