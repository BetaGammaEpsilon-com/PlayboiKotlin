package jayp.bge.playboikotlin.robbie

data class RobbieViewState(
    val clicks: Int = 0,
    val robbieString: String? = null, // nullable
    val loading: Boolean = false
)