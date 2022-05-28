package feature.utils

fun retrieveIdForImage(url: String): String = url.filter { it.isDigit() }

fun retrieveImageByUrl(id: String, typeResponse: String): String {
    return "https://starwars-visualguide.com/assets/img/$typeResponse/$id.jpg"
}