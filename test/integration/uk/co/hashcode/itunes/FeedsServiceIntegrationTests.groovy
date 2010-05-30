package uk.co.hashcode.itunes

import grails.test.*

class FeedsServiceIntegrationTests extends GrailsUnitTestCase {
    def feedsService
        
    protected void setUp() {
        super.setUp()
        def url = 'http://ax.itunes.apple.com/WebObjects/MZStore.woa'
        def context = 'wpa/MRSS/newreleases'
        def parameters = 'sf=143441/limit=10/genre=14'
        def extension = 'rss.xml'
        feedsService = new FeedsService(url:"${url}/${context}/${parameters}/${extension}")
    }
    
    void testGetNewAlbumReleases() {
        def releases = feedsService.getNewAlbumReleases()
        
        assert releases != null
        assert releases.size() == 10
        
        releases.each { album ->
            assert album
            assert album.artist
            assert album.artistLink
            assert album.album
            assert album.albumLink
            assert album.albumPrice
            assert album.coverArt
            assert album.rights
            assert album.releasedate
            println album
        }
    }

    protected void tearDown() {
        super.tearDown()
    }

}
