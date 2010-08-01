package uk.co.hashcode.itunes.feeds

import org.junit.*

class FeedTypeTests {
	
	@Before
    void setUp() {

	}

	@After
    void tearDown() {

	}

	@Test
    void testService() {
    	assert FeedType.NEW_RELEASES.service == 'newreleases'
        assert FeedType.JUST_ADDED.service == 'justadded'
        assert FeedType.FEATURED_ALBUMS.service == 'featuredalbums'
        assert FeedType.TOP_ALBUMS.service == 'topalbums'
        assert FeedType.TOP_IMIXES.service == 'topimixes'
        assert FeedType.TOP_SONGS.service == 'topsongs'
    }
    
	@Test
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

	@Test
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

	@Test
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

	@Test
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
