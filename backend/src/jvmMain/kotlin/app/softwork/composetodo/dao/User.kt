package app.softwork.composetodo.dao

import app.softwork.composetodo.definitions.Todos
import app.softwork.composetodo.definitions.Users
import io.ktor.auth.*
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

// TODO wait for Exposed DTO support
class User(id: EntityID<UUID>) : KotlinxUUIDEntity(id), Principal {
    companion object : KotlinxUUIDEntityClass<User>(Users)

    var firstName by Users.firstName
    var lastName by Users.lastName

    val todos by Todo referrersOn Todos.user
}