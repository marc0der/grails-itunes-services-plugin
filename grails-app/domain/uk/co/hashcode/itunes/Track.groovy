package uk.co.hashcode.itunes

class Track {
    long trackId
    String trackName
    long trackTimeInMillis

    int trackNumber
    int trackCount
    int discNumber

    String releaseDate

    double trackPrice
    double albumPrice
    String currency

    String trackLink
    String albumLink
    String audioPreviewLink
    String imageLink

    String trackType

    long albumId
    String albumName

    long artistId
    String artistName
    String artistLink

    String genre

    static constraints = {

    }
}
