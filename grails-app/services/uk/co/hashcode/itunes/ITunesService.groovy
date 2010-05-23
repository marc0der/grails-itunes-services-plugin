package uk.co.hashcode.itunes

import com.sun.syndication.fetcher.*
import com.sun.syndication.fetcher.impl.*
import com.sun.syndication.feed.synd.SyndFeed

class Album {
    def name
}

class ITunesService {

    static transactional = false
    
    def url

    def getNewAlbumReleases() {
        FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
        FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);
        SyndFeed feed = feedFetcher.retrieveFeed(new URL(url));
        def albums = []
        feed.entries.each { album ->
            albums << new Album(
                name: album.title
            )
//            println " -> ${it.link} "
//            println " -> ${it.title} "
//            println " -> ${it.taxonomyUri}"

        }

    	return albums
    }
}
