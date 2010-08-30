package uk.co.hashcode.itunes.feeds

import com.sun.syndication.fetcher.*
import com.sun.syndication.fetcher.impl.*

import grails.test.*

class ItunesFeedsServiceIntegrationTests extends GrailsUnitTestCase {
    def feedsService
    def feedsCommand
    
    protected void setUp() {
        super.setUp()
        FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.instance;
        FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);

        feedsService = new ItunesFeedsService(
            protocol:'http',
        	domain:'ax.itunes.apple.com',
        	feedFetcher:feedFetcher
        )
        feedsCommand = new FeedsCommand()
    }
    
    void testGetAlbumReleases() {
        def albums = feedsService.getNewAlbumReleases(feedsCommand)
        
        assert albums != null
        assert albums.size() == 10
        
        validate(albums)
    }
    
    void testGetJustAddedAlbums() {
    	def albums = feedsService.getJustAddedAlbums(feedsCommand)

        assert albums != null
        assert albums.size() == 10
        
        validate(albums)
    }
    
    void testGetFeaturedAlbums() {
    	feedsCommand.limit = 2
    	def albums = feedsService.getFeaturedAlbums(feedsCommand)
    	
    	assert albums != null
    	assert albums.size() == 2
    	
    	validate(albums)
    }
    
    void testGetTopAlbums() {
    	feedsCommand.limit = 5
    	def albums = feedsService.getTopAlbums(feedsCommand)
    	
    	assert albums != null
    	assert albums.size() == 5
    	
    	albums.eachWithIndex { album, idx ->
    		assert album
    		assert album.rank == ++idx
    		assert album.name
    		assert album.artist
    		assert album.price
    		assert album.image
    		assert album.releaseDate
    		assert album.link
    		println album
    	}
    }
    

    def validate = { albums ->
            albums.eachWithIndex { album, idx ->
            assert album
            assert album.rank == ++idx
            assert album.artist
            assert album.artistLink
            assert album.name
            assert album.link
            assert album.price
            assert album.image
            assert album.rights
            assert album.releaseDate
            println album
        }
    }

    protected void tearDown() {
        super.tearDown()
    }

}
