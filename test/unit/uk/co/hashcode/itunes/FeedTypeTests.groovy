package uk.co.hashcode.itunes

import grails.test.*

class FeedTypeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testService() {
    	assert FeedType.NEW_RELEASES.service == 'newreleases'
        assert FeedType.JUST_ADDED.service == 'justadded'
        assert FeedType.FEATURED_ALBUMS.service == 'featuredalbums'
        assert FeedType.TOP_ALBUMS.service == 'topalbums'
        assert FeedType.TOP_IMIXES.service == 'topimixes'
        assert FeedType.TOP_SONGS.service == 'topsongs'
    }
    
    void testWoa() {
        //MZStore feeds
        assert FeedType.NEW_RELEASES.woa == 'MZStore.woa'
        assert FeedType.JUST_ADDED.woa == 'MZStore.woa'
        assert FeedType.FEATURED_ALBUMS.woa == 'MZStore.woa'
        	
        //MZStoreServices feeds
        assert FeedType.TOP_ALBUMS.woa == 'MZStoreServices.woa'
        assert FeedType.TOP_IMIXES.woa == 'MZStoreServices.woa'
        assert FeedType.TOP_SONGS.woa == 'MZStoreServices.woa'
    }
}
