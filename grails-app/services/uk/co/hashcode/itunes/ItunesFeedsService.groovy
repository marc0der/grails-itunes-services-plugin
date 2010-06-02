package uk.co.hashcode.itunes

import com.sun.syndication.feed.synd.SyndFeed;

class FeedsCommand {
	def feedType
	def country
	int limit
	def genre
	
	String execute(){
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
			
		return "/WebObjects/${feedType.woa}/${feedType.context}/${feedType.subContext}/${feedType.service}/${countryStr}/${limitStr}/${genreStr}/${feedType.suffix}"
	}
}

class ItunesFeedsService {

    static transactional = false
    
    def domain
    
    def feedFetcher
    
    List getNewAlbumReleases(FeedsCommand command) {
    	command.feedType = FeedType.NEW_RELEASES
    	return fetch(command)
    }
    
    List getJustAddedAlbums(FeedsCommand command) {
    	command.feedType = FeedType.JUST_ADDED
    	return fetch(command)
    }
    
    List getFeaturedAlbums(FeedsCommand command) {
    	command.feedType = FeedType.FEATURED_ALBUMS
    	return fetch(command)
    }
    
    List getTopAlbums(FeedsCommand command) {
    	command.feedType = FeedType.TOP_ALBUMS
    	return fetch(command)
    }
    
    List getTopIMixes(FeedsCommand command) {
    	throw new UnsupportedOperationException()
    }
    
    List getTopSongs(FeedsCommand command) {
    	throw new UnsupportedOperationException()
    }
    
    def fetch = { command ->
        def commandStr = command.execute()
        def feed = feedFetcher.retrieveFeed(new URL("${domain}${commandStr}"));
        def albums = []
        feed.entries.eachWithIndex { item, count ->
            def params = [rank:(++count)]
            item.foreignMarkup.each { markup ->
                params.put markup.name, (markup.value ?: 'Not Available')
            }
            if(item.link) params.put 'link', item.link
            albums << params
        }

    	return albums
    }    
}

