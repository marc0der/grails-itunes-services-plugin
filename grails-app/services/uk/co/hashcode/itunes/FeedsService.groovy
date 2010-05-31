package uk.co.hashcode.itunes

import com.sun.syndication.fetcher.*
import com.sun.syndication.fetcher.impl.*
import com.sun.syndication.feed.synd.SyndFeed


class FeedsCommand {
	def urlBase
	def feedType
	def country
	int limit
	def genre
	
	final String WEBOBJECTS = 'WebObjects'
	final String SUFFIX = 'rss.xml'
	
	String execute(){
		if(!urlBase)
			throw new IllegalStateException("URL base not set.")

		if(feedType in [FeedType.TOP_IMIXES, FeedType.TOP_SONGS]) 
			throw new UnsupportedOperationException("The FeedType ${feedType.service} is not supported.")
		
		feedType = feedType ?: FeedType.NEW_RELEASES
		
		limit = limit ?: 10
		if(limit < 0) limit = 1
		if(limit > 25) limit = 25
		def limitStr = "limit=${limit}"
		
		country = country ?: Country.USA
		def countryStr = "sf=${country.id}"
		
		genre = genre ?: Genre.POP
		def genreStr = "genre=${genre.id}"
			
		return "${urlBase}/${WEBOBJECTS}/${feedType.woa}/${feedType.context}/${feedType.subContext}/${feedType.service}/${countryStr}/${limitStr}/${genreStr}/${SUFFIX}"
	}
}

class FeedsService {

    static transactional = false
    
    def feedFetcher
    
    def url

    def getNewAlbumReleases() {
        FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.instance;
        FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);
        SyndFeed feed = feedFetcher.retrieveFeed(new URL(url));
        def releases = []
        feed.entries.eachWithIndex { item, count ->
            def params = [rank:(++count)]
            item.foreignMarkup.each { foreignMarkup ->
                params.put foreignMarkup.name, (foreignMarkup.value ?: 'Not Available')
            }
            releases << new Album(params)
        }

    	return releases
    }
}

