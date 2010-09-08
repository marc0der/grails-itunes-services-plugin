package uk.co.hashcode.itunes.search

import uk.co.hashcode.itunes.Album
import org.junit.Test
import uk.co.hashcode.itunes.Artist
import uk.co.hashcode.itunes.Track

import org.junit.Before

class UnmarshallingServiceTests {

    def service


    @Before
    void setUp() {
        service = new UnmarshallingService()
    }

    @Test
    void testUnmarshallJsonAlbumsSuccess() {
        def jsonAlbumObject = [results:
            [
                   [
                            "collectionExplicitness":"notExplicit",
                            "collectionType":"Album",
                            "artworkUrl60":"http://a1.phobos.apple.com/us/r1000/034/Music/a7/7b/bf/mzi.rcjnjmwz.60x60-50.jpg",
                            "primaryGenreName":"Electronic",
                            "collectionId":293921148,
                            "wrapperType":"collection",
                            "collectionViewUrl":"http://itunes.apple.com/us/album/id293921148?uo=4",
                            "copyright":"2008 4AD",
                            "currency":"USD",
                            "country":"USA",
                            "releaseDate":"2008-11-03T08:00:00Z",
                            "artistId":80591287,
                            "artistViewUrl":"http://itunes.apple.com/us/artist/johann-johannsson/id80591287?uo=4",
                            "amgVideoArtistId":null,
                            "contentAdvisoryRating":null,
                            "artistName":"Jóhann Jóhannsson",
                            "collectionCensoredName":"Fordlândia (Bonus Track Version)",
                            "artworkUrl100":"http://a1.phobos.apple.com/us/r1000/034/Music/a7/7b/bf/mzi.rcjnjmwz.100x100-75.jpg",
                            "amgArtistId":460419,
                            "collectionName":"Fordlândia (Bonus Track Version)",
                            "trackCount":12,
                            "collectionPrice":9.99
                   ]
            ]
        ]

        def albums = service.unmarshallJsonAlbums(jsonAlbumObject)
        assert albums

        def result = jsonAlbumObject.results[0]

        Album album = albums[0]
        assert album.albumId == result.collectionId
        assert album.artist == result.artistName
        assert album.artistId == result.artistId
        assert album.name == result.collectionName
        assert album.link == result.collectionViewUrl
        assert album.price == "$result.collectionPrice"
        assert album.image == result.artworkUrl100
        assert album.rights == result.copyright
        assert album.releaseDate == result.releaseDate
        assert album.artistLink == result.artistViewUrl
    }

    @Test
    void testUnmarshallJsonArtistsSuccess() {
        def jsonArtistObject = [results:
            [
                    [
                            "artistType":"Artist",
                            "primaryGenreName":"Pop",
                            "artistId":20122591,
                            "artistLinkUrl":"http://itunes.apple.com/us/artist/led/id20122591?uo=4",
                            "primaryGenreId":14,
                            "amgVideoArtistId":null,
                            "wrapperType":"artist",
                            "artistName":"LED",
                            "amgArtistId":null
                    ]
            ]
        ]

        def artists = service.unmarshallJsonArtists(jsonArtistObject)
        assert artists

        def result = jsonArtistObject.results[0]

        Artist artist = artists[0]
        assert artist.artistId == result.artistId
        assert artist.amgArtistId == result.amgArtistId
        assert artist.amgVideoArtistId == result.amgVideoArtistId
        assert artist.name == result.artistName
        assert artist.artistType == result.artistType
        assert artist.genreId == result.primaryGenreId
    }


    void testUnmarshallJsonTrackSuccess() {
        def jsonTrackObject = [results:
            [
                    [
                            "wrapperType":"track",
                            "collectionId":266075192,
                            "trackTimeMillis":482307,
                            "collectionViewUrl":"http://itunes.apple.com/us/album/stairway-to-heaven/id266075192?i=266075552&uo=4",
                            "trackName":"Stairway to Heaven",
                            "kind":"song",
                            "currency":"USD",
                            "releaseDate":"2007-11-13 08:00:00 Etc/GMT",
                            "artistId":994656,
                            "collectionName":"Mothership (Remastered)",
                            "trackCount":24,
                            "trackId":266075552,
                            "primaryGenreName":"Rock",
                            "trackNumber":13,
                            "discNumber":1,
                            "previewUrl":"http://a1.phobos.apple.com/us/r1000/014/Music/02/c8/a3/mzm.njrnfnre.aac.p.m4a",
                            "artistViewUrl":"http://itunes.apple.com/us/artist/led-zeppelin/id994656?uo=4",
                            "trackPrice":1.29,
                            "artistName":"Led Zeppelin",
                            "artworkUrl100":"http://a1.phobos.apple.com/us/r1000/011/Music/73/55/c3/mzi.wcqgtuty.100x100-75.jpg",
                            "trackViewUrl":"http://itunes.apple.com/us/album/stairway-to-heaven/id266075192?i=266075552&uo=4",
                            "collectionPrice":13.99
                    ]
            ]
        ]

        def tracks = service.unmarshallJsonTracks(jsonTrackObject)
        assert tracks

        def result = jsonTrackObject.results[0]

        Track track = tracks[0]
        assert track.trackId == result.trackId
        assert track.trackName == result.trackName
        assert track.trackTimeInMillis == result.trackTimeMillis
        assert track.trackNumber == result.trackNumber
        assert track.trackCount == result.trackCount
        assert track.discNumber == result.discNumber
        assert track.releaseDate == result.releaseDate
        assert track.trackPrice == result.trackPrice
        assert track.albumPrice == result.collectionPrice
        assert track.currency == result.currency
        assert track.trackLink == result.trackViewUrl
        assert track.audioPreviewLink == result.previewUrl
        assert track.imageLink == result.artworkUrl100
        assert track.albumLink == result.collectionViewUrl
        assert track.trackType == result.kind
        assert track.albumId == result.collectionId
        assert track.albumName == result.collectionName
        assert track.artistId == result.artistId
        assert track.artistName == result.artistName
        assert track.artistLink == result.artistViewUrl
        assert track.genre == result.primaryGenreName
    }
}
