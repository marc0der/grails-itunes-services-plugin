package uk.co.hashcode.itunes

import grails.test.*

class ITunesServiceIntegrationTests extends GrailsUnitTestCase {
    def iTunesService
    def url
    def parameters
        
    protected void setUp() {
        super.setUp()
        url = 'http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa'
        parameters = 'ws/RSS/topalbums/sf=143441/limit=10/genre=14/xml'
        iTunesService = new ITunesService(url:"${url}/${parameters}")
    }

    void testGetNewAlbumReleases() {
        def releases = iTunesService.getNewAlbumReleases()
        
        assert releases != null
        assert releases.size() == 10
        
        releases.each { album ->
            assert album
            assert album.name
            println album.name
        }
    }

    protected void tearDown() {
        super.tearDown()
    }

}
