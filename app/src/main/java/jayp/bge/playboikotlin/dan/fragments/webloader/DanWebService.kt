package jayp.bge.playboikotlin.dan.fragments.webloader

import retrofit2.http.GET
import retrofit2.http.Path

interface DanWebService {

    @GET("/users/{id}/todos")
    suspend fun getUsers(@Path("id") id: Int) : List<User>
}