package jayp.bge.playboikotlin.josh.fragments.http

import retrofit2.http.GET
import retrofit2.http.Query

interface JoshWebService {

    @GET("/api/v2/8ball")
    suspend fun queryAteBall(): AteBall

    @GET("/api/v2/8ball/insert")
    suspend fun addAteBall(
        @Query("demand") demand: String,
        @Query("id") id: Int,
        @Query("message") message: String,
        @Query("you_will_live_to") you_will_live_to: Int
    ): List<AteBall>

}

data class AteBall(
    var demand: String = "",
    var id: Int = 0,
    var message: String = "",
    var you_will_live_to: Int = 0
)