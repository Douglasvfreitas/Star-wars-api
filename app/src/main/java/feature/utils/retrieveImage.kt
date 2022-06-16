package feature.utils

fun retrieveIdForImage(url: String): String = url.filter { it.isDigit() }

fun retrieveImageById(id: String, responseType: String): String {
    return "https://starwars-visualguide.com/assets/img/$responseType/$id.jpg"
}