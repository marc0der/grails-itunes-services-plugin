package uk.co.hashcode.itunes.search

import uk.co.hashcode.itunes.Album
import uk.co.hashcode.itunes.Artist
import uk.co.hashcode.itunes.Track

class UnmarshallingService {

    static transactional = false

    List<Album> unmarshallJsonAlbums(def jsonObject){
        def albums = []
        jsonObject.results.each { album ->
            albums << new Album(
                    albumId:album.collectionId,
                    name:album.collectionName,
                    link:album.collectionViewUrl,
                    artistId:album.artistId,
                    artist:album.artistName,
                    price:album.collectionPrice,
                    image:album.artworkUrl100,
                    rights:album.copyright,
                    releaseDate:album.releaseDate,
                    artistLink:album.artistViewUrl
            )
        }

        return albums
    }

    List<Artist> unmarshallJsonArtists(def jsonObject){
        def artists = []
        jsonObject.results.each { artist ->
            assert artist.wrapperType == 'artist'
            artists << new Artist(
                    artistId:artist.artistId,
                    amgArtistId:artist.amgArtistId,
                    amgVideoArtistId:artist.amgVideoArtistId,
                    name:artist.artistName,
                    link:artist.artistLinkUrl,
                    artistType:artist.artistType,
                    genreId:artist.primaryGenreId,
                    genreName:artist.primaryGenreName
            )
        }
        return artists
    }

    List<Track> unmarshallJsonTracks(def jsonObject){
        def tracks = []
        jsonObject.results.each { track ->
            assert track.wrapperType == 'track'
            tracks << new Track(
                    trackId:track.trackId,
                    trackName:track.trackName,
                    trackTimeInMillis:track.trackTimeMillis,
                    trackNumber:track.trackNumber,
                    trackCount:track.trackCount,
                    discNumber:track.discNumber,
                    releaseDate:track.releaseDate,
                    trackPrice:track.trackPrice,
                    albumPrice:track.collectionPrice,
                    currency:track.currency,
                    trackLink:track.trackViewUrl,
                    audioPreviewLink:track.previewUrl,
                    imageLink:track.artworkUrl100,
                    albumLink:track.collectionViewUrl,
                    trackType:track.kind,
                    albumId:track.collectionId,
                    albumName:track.collectionName,
                    artistId:track.artistId,
                    artistName:track.artistName,
                    artistLink:track.artistViewUrl,
                    genre:track.primaryGenreName
            )
        }
        return tracks
    }
}
