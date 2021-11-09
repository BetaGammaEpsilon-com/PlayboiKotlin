package jayp.bge.playboikotlin.zeb.fragments.hurricankotalina

import retrofit2.http.GET
import retrofit2.http.Query

interface ZebWebService {

    @GET("/api/v2/8ball")
    suspend fun getFortune(): EightBallJson

    @GET("/api/v2/8ball/insert")
    suspend fun addFortune(
        @Query("demand") demand: String,
        @Query("id") id: Int,
        @Query("message") message: String,
        @Query("you_will_live_to") you_will_live_to: Int): List<EightBallJson>
}
