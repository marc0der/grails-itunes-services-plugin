package uk.co.hashcode.itunes

import com.sun.syndication.fetcher.*;
import com.sun.syndication.fetcher.impl.*;

import grails.test.*

class FeedsServiceIntegrationTests extends GrailsUnitTestCase {
    def feedsService
    def feedsCommand
    
    protected void setUp() {
        super.setUp()
        FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.instance;
        FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);

        feedsService = new FeedsService(
        	domain:'http://ax.itunes.apple.com',
        	feedFetcher:feedFetcher
        )
        feedsCommand = new FeedsCommand()
    }
    
    void testGetAlbumReleases() {
        def releases = feedsService.getNewAlbumReleases(feedsCommand)
        
        assert releases != null
        assert releases.size() == 10
        
        validateAlbums(releases)
    }
    
    void testGetJustAddedAlbumReleases() {
    	def releases = feedsService.getJustAddedAlbumReleases(feedsCommand)

        assert releases != null
        assert releases.size() == 10
        
        validateAlbums(releases)
    }
    
    def validateAlbums = { albums ->
            albums.each { album ->
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
