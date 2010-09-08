package uk.co.hashcode.itunes

/**
 * Represents an Artist on the iTunes Store.
 */
class Artist {
    long artistId
    String amgArtistId
    String amgVideoArtistId
    String name
    String link
    String artistType
    long genreId
    String genreName

    static constraints = {
    }

    static mapping = {
        table 'itunes_artist'
    }

    String toString(){
        "[Artist: $id, name:$name, artistId:$artistId, amgArtistId:$amgArtistId, amgVideoArtistId:$amgVideoArtistId, link:$link, artistType:$artistType, genreId:$genreId, genreName:$genreName]"
    }
}
