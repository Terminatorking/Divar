package ghazimoradi.soheil.divar.data.mapper.user

import ghazimoradi.soheil.divar.domain.model.user.User
import ghazimoradi.soheil.divar.network.dto.user.UserResponse

fun UserResponse.toDomain(): User {
    return User(
        name = name,
        family = family,
        email = email,
        token = token,
        mobile = mobile,
        createAt = createAt,
        updatedAt = updatedAt
    )
}