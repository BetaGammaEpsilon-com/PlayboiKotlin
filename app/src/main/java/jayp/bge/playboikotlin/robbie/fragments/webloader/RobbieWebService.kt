package jayp.bge.playboikotlin.robbie.fragments.webloader

import retrofit2.http.GET
import retrofit2.http.Query

interface RobbieWebService {
    @GET("insert")
    suspend fun robbieInsert8Ball(
        @Query("message") message: String,
        @Query("id") id: Int,
        @Query("you_will_live_to") liveTo: Int,
        @Query("demand") demand: String,
    ): List<EightBall>

}