package uk.co.hashcode.itunes

import grails.test.*

class FeedsServiceIntegrationTests extends GrailsUnitTestCase {
    def feedsService
    def feedsCommand
    
    protected void setUp() {
        super.setUp()
        feedsService = new FeedsService()
        feedsCommand = new FeedsCommand(
        	urlBase:'http://ax.itunes.apple.com'
        )
    }
    
    void testGet10NewUsaPopAlbumReleases() {
        def releases = feedsService.getNewAlbumReleases(feedsCommand)
        
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
