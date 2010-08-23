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
        service = new ItunesSearchService(
            domain:'ax.phobos.apple.com.edgesuite.net', 
            context:'WebObjects/MZStoreServices.woa/wa/wsSearch'
        )
    }

    @Test
    void testSearch(){
        command = new ItunesSearchCommand(term:'Led Zeppelin')
        List<Album> albums = service.search(command)
        assert albums

        for(album in albums){
            println album
            assert album.name
            assert album.artist
            assert album.link
            assert album.price
            assert album.image
            assert album.rights
            assert album.releaseDate
        }

    }
}
