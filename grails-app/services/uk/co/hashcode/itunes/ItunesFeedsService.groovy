package uk.co.hashcode.itunes

import java.text.SimpleDateFormat;

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
    	return convertRssParams(fetch(command))
    }
    
    List getJustAddedAlbums(FeedsCommand command) {
    	command.feedType = FeedType.JUST_ADDED
    	return convertRssParams(fetch(command))
    }
    
    List getFeaturedAlbums(FeedsCommand command) {
    	command.feedType = FeedType.FEATURED_ALBUMS
    	return convertRssParams(fetch(command))
    }
    
    List getTopAlbums(FeedsCommand command) {
    	command.feedType = FeedType.TOP_ALBUMS
    	return convertXmlParams(fetch(command))
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
    
    def convertRssParams = { paramsList ->
    	def albums = []
    	paramsList.each { params ->
    		def formatter = new SimpleDateFormat('MMM dd, yyyy')
    		def releaseDate = formatter.parse(params.releasedate)
	    	albums << new Album(
	    		rank:params.rank,
	    		artist:params.artist,
	    		artistLink:params.artistLink,
	    		name:params.album,
	    		link:params.albumLink,
	    		price:params.albumPrice,
	    		image:params.coverArt,
	    		rights:params.rights,
	    		releaseDate:releaseDate
	    	)
    	}
    	
    	return albums
    }
    
    def convertXmlParams = { paramsList ->
    	def albums = []
    	paramsList.each { params ->
	    	def formatter = new SimpleDateFormat('yyyy-MM-dd')
	    	def releaseDate = formatter.parse(params.releaseDate)
	    	albums << new Album(
	    		rank:params.rank,
	    		name:params.name,
	    		artist:params.artist,
	    		price:params.price,
	    		image:params.image,
	    		releaseDate:releaseDate,
	    		link:params.link
	    	)
    	}
    	
    	return albums
    }

}

