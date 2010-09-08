package uk.co.hashcode.itunes.search

import org.junit.Before
import org.junit.Test
import uk.co.hashcode.itunes.Album
import uk.co.hashcode.itunes.Artist
import javax.sound.midi.Track

/**
 * Created by IntelliJ IDEA.
 * User: marco
 * Date: 22-Aug-2010
 * Time: 12:02:42
 */
class ItunesSearchServiceIntegrationTests {
    ItunesSearchService service
    ItunesSearchCommand command
    UnmarshallingService unmarshallingService

    @Before
    void setUp(){
        unmarshallingService = new UnmarshallingService()
        command = new ItunesSearchCommand(term:'Led Zeppelin')
        service = new ItunesSearchService(
            unmarshallingService:unmarshallingService,
            domain:'ax.phobos.apple.com.edgesuite.net',
            context:'WebObjects/MZStoreServices.woa/wa/wsSearch'
        )

        service.metaClass.search = {
            return new Object()
        }
    }

    @Test
    void testSearchWithDefaults(){
        def results = service.search(command)
        assert results

        for(album in results.albums){
            println album
            assert album.collectionName
            assert album.artistName
            assert album.collectionViewUrl
            assert album.collectionPrice
            assert album.artworkUrl100
            assert album.copyright
            assert album.releaseDate
        }

    }

    @Test
    void testGetMetadataWithDefaults(){
        def metaData = service.getMetadata(command)
        
        assert metaData.contains('collectionExplicitness')
        assert metaData.contains('collectionType')
        assert metaData.contains('artworkUrl60')
        assert metaData.contains('primaryGenreName')
        assert metaData.contains('collectionId')
        assert metaData.contains('wrapperType')
        assert metaData.contains('collectionViewUrl')
        assert metaData.contains('copyright')
        assert metaData.contains('currency')
        assert metaData.contains('country')
        assert metaData.contains('releaseDate')
        assert metaData.contains('artistId')
        assert metaData.contains('artistViewUrl')
        assert metaData.contains('amgVideoArtistId')
        assert metaData.contains('contentAdvisoryRating')
        assert metaData.contains('artistName')
        assert metaData.contains('collectionCensoredName')
        assert metaData.contains('artworkUrl100')
        assert metaData.contains('amgArtistId')
        assert metaData.contains('collectionName')
        assert metaData.contains('trackCount')
        assert metaData.contains('collectionPrice')
        
    }

    @Test
    void testSearchAlbumsByNameSuccessAlbumType(){
        validateAlbums service.searchAlbumsByName('Fordlandia')
    }

    @Test
    void testSearchAlbumsByNameSuccessCompilationType(){
        validateAlbums service.searchAlbumsByName('Presence')
    }


    @Test
    void testSearchAlbumsByArtistSuccess(){
        validateAlbums service.searchAlbumsByArtist('Led Zeppelin')
    }

    @Test
    void testSearchTracksByNameSuccess(){
        validateTracks service.searchTracksByName('One')
    }

    @Test
    void testSearchTracksByArtistSuccess(){
        validateTracks service.searchTracksByArtist('Led Zeppelin')
    }

    private void validateAlbums(albums){
        assert albums != null
        assert albums instanceof List<Album>

        assert !albums.empty
        albums.each { album ->
            assert album.name
            assert album.link
            assert album.artist
            assert album.price
            assert album.image
            assert album.releaseDate
            println album
        }
    }

    @Test
    void testSearchArtistsByName(){
        validateArtists service.searchArtistsByName('Led')
    }

    private void validateArtists(artists){
        assert artists != null
        assert artists instanceof List<Artist>

        assert !artists.empty
        artists.each { artist ->
            assert artist
            assert artist.name
            assert artist.link
            assert artist.artistId
            assert artist.artistType
            println artist
        }
    }

    private void validateTracks(tracks){
        assert tracks != null
        assert tracks instanceof List<Track>

        assert !tracks.empty
        tracks.each { track ->
            assert track
            println track
        }
    }
}
