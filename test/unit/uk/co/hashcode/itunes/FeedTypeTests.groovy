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

    void testContext() {
        //MZStore feeds
        assert FeedType.NEW_RELEASES.context == 'wpa'
        assert FeedType.JUST_ADDED.context == 'wpa'
        assert FeedType.FEATURED_ALBUMS.context == 'wpa'

        //MZStoreServices feeds
        assert FeedType.TOP_ALBUMS.context == 'ws'
        assert FeedType.TOP_IMIXES.context == 'ws'
        assert FeedType.TOP_SONGS.context == 'ws'
    }

    void testSubContext() {
        //MZStore feeds
        assert FeedType.NEW_RELEASES.subContext == 'MRSS'
        assert FeedType.JUST_ADDED.subContext == 'MRSS'
        assert FeedType.FEATURED_ALBUMS.subContext == 'MRSS'

        //MZStoreServices feeds
        assert FeedType.TOP_ALBUMS.subContext == 'RSS'
        assert FeedType.TOP_IMIXES.subContext == 'RSS'
        assert FeedType.TOP_SONGS.subContext == 'RSS'

    }

    void testSuffix() {
        //MZStore feeds
        assert FeedType.NEW_RELEASES.suffix == 'rss.xml'
        assert FeedType.JUST_ADDED.suffix == 'rss.xml'
        assert FeedType.FEATURED_ALBUMS.suffix == 'rss.xml'

        //MZStoreServices feeds
        assert FeedType.TOP_ALBUMS.suffix == 'xml'
        assert FeedType.TOP_IMIXES.suffix == 'xml'
        assert FeedType.TOP_SONGS.suffix == 'xml'
    }
}
