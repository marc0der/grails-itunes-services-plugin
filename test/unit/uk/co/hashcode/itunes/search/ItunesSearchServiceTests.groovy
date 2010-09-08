package uk.co.hashcode.itunes.search

import org.junit.Before
import org.junit.Test

class ItunesSearchServiceTests {

    ItunesSearchService service

    def domain = 'ax.phobos.apple.com.edgesuite.net'
    def context = 'WebObjects/MZStoreServices.woa/wa/wsSearch'
    def command
    def commandStr
    
    def jsonAlbumObject
    def jsonArtistObject
    def jsonTrackObject

    @Before
    void setUp() {
        service = new ItunesSearchService(
            domain:domain,
            context:context
        )

        command = new ItunesSearchCommand(term:'search')
        commandStr = command.execute()

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


}
