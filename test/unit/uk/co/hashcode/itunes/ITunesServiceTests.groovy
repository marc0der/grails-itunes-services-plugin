package uk.co.hashcode.itunes

import grails.test.*

class ITunesServiceTests extends GrailsUnitTestCase {
    def iTunesService
    
    protected void setUp() {
        super.setUp()
        def url = 'http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topalbums/sf=143441/limit=1/genre=14/xml'
        iTunesService = new ITunesService(url:url)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetNewAlbumReleases() {
        def releases = iTunesService.getNewAlbumReleases(1)
        assert releases == 'success'
    }
}
