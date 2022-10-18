package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.RolePermissionRepository
import java.util.*

class FetchPermissionsViaIdOperation(
    private val permissionsRepository: RolePermissionRepository,
) {
    operator fun invoke(roleId: UUID) = permissionsRepository.fetch(roleId)
}