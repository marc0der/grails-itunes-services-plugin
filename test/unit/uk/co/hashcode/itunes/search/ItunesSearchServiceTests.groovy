package uk.co.hashcode.itunes.search

import org.junit.Before
import org.junit.Test
import uk.co.hashcode.itunes.Album
import uk.co.hashcode.itunes.Artist

class ItunesSearchServiceTests {
    ItunesSearchService service

    def domain = 'ax.phobos.apple.com.edgesuite.net'
    def context = 'WebObjects/MZStoreServices.woa/wa/wsSearch'
    def command
    def commandStr
    
    def jsonAlbumObject
    def jsonArtistObject

    @Before
    void setUp() {
        service = new ItunesSearchService(
            domain:domain,
            context:context
        )

        command = new ItunesSearchCommand(term:'search')
        commandStr = command.execute()

        jsonAlbumObject = [results:
            [
                   [
                            'wrapperType':'collection',
                            'collectionType':'Album',
                            'artistId':'994656',
                            'collectionId':'266077709',
                            'amgArtistId':'4739',
                            'amgVideoArtistId':null,
                            'artistName':'Led Zeppelin',
                            'collectionName':'The Complete Led Zeppelin (Remastered)',
                            'collectionCensoredName':'The Complete Led Zeppelin (Remastered)',
                            'artistViewUrl':'http://itunes.apple.com/us/artist/led-zeppelin/id994656?uo=4',
                            'collectionViewUrl':'http://itunes.apple.com/us/album/the-complete-led-zeppelin/id266077709?uo=4',
                            'artworkUrl60':'http://a1.phobos.apple.com/us/r1000/003/Music/ee/e1/26/mzi.hakselfh.60x60-50.jpg',
                            'artworkUrl100':'http://a1.phobos.apple.com/us/r1000/003/Music/ee/e1/26/mzi.hakselfh.100x100-75.jpg',
                            'collectionPrice':"99.99",
                            'collectionExplicitness':'notExplicit',
                            'contentAdvisoryRating':null,
                            'trackCount':"165",
                            'copyright':'2007 Atlantic Recording Corp., a Warner Music Group company',
                            'country':'USA',
                            'currency':'USD',
                            'releaseDate':'2007-11-09T08:00:00Z',
                            'primaryGenreName':'Rock'
                   ]
            ]
        ]

        jsonArtistObject = [results:
            [
                    [
                            'wrapperType':'artist',
                            'artistType':'Artist',
                            'artistName':'Led Zeppelin',
                            'artistId':'994656',
                            'amgArtistId':'4739',
                            'amgVideoArtistId':null,
                            'artistLinkUrl':'http://itunes.apple.com/us/artist/led-zeppelin/id994656?uo=4',
                            'primaryGenreId':'21',
                            'primaryGenreName':'Rock'
                    ]
            ]
        ]

    }

    @Test
    void testBuildUrlSuccess() {
        def url = service.buildUrl(command)
        assert url == "http://$domain/$context?$commandStr"

    }

    @Test
    void testBuildUrlNoDomainSet() {
        service.domain = null

        try{
            service.buildUrl(command)
            
        } catch (Exception e){
            assert e instanceof IllegalStateException
        }

    }

    @Test
    void testBuildUrlNoContextSet() {
        service.context = null

        try{
            service.buildUrl(command)

        } catch (Exception e){
            assert e instanceof IllegalStateException
        }
    }

    @Test
    void testUnmarshallJsonAlbumsSuccess() {
        def albums = service.unmarshallJsonAlbums(jsonAlbumObject)
        assert albums

        def result = jsonAlbumObject.results[0]

        Album album = albums[0]
        assert album.albumId == result.collectionId
        assert album.artist == result.artistName
        assert album.artistId == result.artistId
        assert album.name == result.collectionName
        assert album.link == result.collectionViewUrl
        assert album.price == result.collectionPrice
        assert album.image == result.artworkUrl100
        assert album.rights == result.copyright
        assert album.releaseDate == result.releaseDate
        assert album.artistLink == result.artistViewUrl
    }

    @Test
    void testUnmarshallJsonArtistsSuccess() {
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

}
