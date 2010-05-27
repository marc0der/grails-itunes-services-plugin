package uk.co.hashcode.itunes

import com.sun.syndication.fetcher.*
import com.sun.syndication.fetcher.impl.*
import com.sun.syndication.feed.synd.SyndFeed

class ITunesService {

    static transactional = false
    
    def feedFetcher
    
    def url

    def getNewAlbumReleases() {
        FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.instance;
        FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);
        SyndFeed feed = feedFetcher.retrieveFeed(new URL(url));
        def releases = []
        feed.entries.eachWithIndex { item, count ->
            def album = [rank:(++count)]
            item.foreignMarkup.each { foreignMarkup ->
                album.put foreignMarkup.name, (foreignMarkup.value ?: 'Not Available')
            }
            releases << album
        }

    	return releases
    }
}

