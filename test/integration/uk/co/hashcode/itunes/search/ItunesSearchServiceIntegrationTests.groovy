package uk.co.hashcode.itunes.search

import org.junit.Before
import org.junit.Test
import uk.co.hashcode.itunes.Album

/**
 * Created by IntelliJ IDEA.
 * User: marco
 * Date: 22-Aug-2010
 * Time: 12:02:42
 */
class ItunesSearchServiceIntegrationTests {
    ItunesSearchService service
    ItunesSearchCommand command

    @Before
    void setUp(){
        command = new ItunesSearchCommand(term:'Led Zeppelin')
        service = new ItunesSearchService(
            domain:'ax.phobos.apple.com.edgesuite.net',
            context:'WebObjects/MZStoreServices.woa/wa/wsSearch'
        )
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
}
