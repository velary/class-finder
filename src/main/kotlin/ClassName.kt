class ClassName(val fullName: String) {
    val simpleName: String

    init {
        val lastDotIndex = fullName.lastIndexOf('.')
        simpleName =
            if (lastDotIndex == -1) {
                fullName
            } else {
                fullName.substring(lastDotIndex + 1)
            }
    }
}



