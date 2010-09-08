package uk.co.hashcode.itunes.feeds

import uk.co.hashcode.itunes.Album

/**
 * Used for refining requests to the ITunesFeedsService.
 */
class ItunesFeedsCommand {
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
		
		genre = genre ?: Genre.ALL
		def genreStr = ''
		if(genre != Genre.ALL) genreStr = "/genre=${genre.id}"
			
		return "/WebObjects/${feedType.woa}/${feedType.context}/${feedType.subContext}/${feedType.service}/${countryStr}/${limitStr}${genreStr}/${feedType.suffix}"
	}
}

/**
 * Exposes the iTunes RSS feeds as a Grails service to be used
 * from within controllers. Returns results as lists of Album
 * instances.
 */
class ItunesFeedsService {

    static transactional = false
    
    def command = new ItunesFeedsCommand()

    def protocol

    def domain
    
    def feedFetcher
    
    /**
     * Gets new album releases from the iTunes Store.
     *
     * @param command The criteria for refining results.
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getNewAlbumReleases(ItunesFeedsCommand command) {
    	command.feedType = FeedType.NEW_RELEASES
    	convertRssParams fetch(command)
    }
    
    /**
     * Gets new album releases from the iTunes Store.
     * A convenience method that uses default command values.
     * 
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getNewAlbumReleases() {
    	getNewAlbumReleases command
    }
    
    /**
     * Gets albums that were just added to the iTunes Store.
     *
     * @param command The criteria for refining results.
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getJustAddedAlbums(ItunesFeedsCommand command) {
    	command.feedType = FeedType.JUST_ADDED
    	convertRssParams fetch(command)
    }
    
    /**
     * Gets albums that were just added to the iTunes Store.
     * A convenience method that uses default command values.
     * 
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getJustAddedAlbums() {
    	getJustAddedAlbums command
    }
    
    /**
     * Gets featured albums on the iTunes Store.
     *
     * @param command The criteria for refining results.
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getFeaturedAlbums(ItunesFeedsCommand command) {
    	command.feedType = FeedType.FEATURED_ALBUMS
    	convertRssParams fetch(command)
    }
    
    /**
     * Gets featured albums on the iTunes Store.
     * A convenience method that uses default command values.
     *
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getFeaturedAlbums() {
    	getFeaturedAlbums command
    }
    
    /**
     * Gets the top albums on the iTunes Store.
     *
     * @param command The criteria for refining results.
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getTopAlbums(ItunesFeedsCommand command) {
    	command.feedType = FeedType.TOP_ALBUMS
    	convertXmlParams fetch(command)
    }

    /**
     * Gets the top albums on the iTunes Store.
     * A convenience method that uses default command values.
     *
     * @return A list of uk.co.hashcode.itunes.Album instances
     */
    List getTopAlbums() {
    	getTopAlbums command
    }
    
    /**
     * Top iMixes on the iTunes Store are not supported in this plugin yet.
     */
    List getTopIMixes(ItunesFeedsCommand command) {
    	throw new UnsupportedOperationException()
    }

    /**
     * Top iMixes on the iTunes Store are not supported in this plugin yet.
     */
    List getTopIMixes() {
    	throw new UnsupportedOperationException()
    }
    
    /**
     * Top Songs on the iTunes Store are not currently supported in this plugin yet.
     */
    List getTopSongs(ItunesFeedsCommand command) {
    	throw new UnsupportedOperationException()
    }

    /**
     * Top Songs on the iTunes Store are not currently supported in this plugin yet.
     */
    List getTopSongs() {
    	throw new UnsupportedOperationException()
    }
    
    def fetch = { command ->
        def commandStr = command.execute()
        def feed = feedFetcher.retrieveFeed(new URL("${protocol}://${domain}${commandStr}"));
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
	    	albums << new Album(
	    		rank:params.rank,
	    		artist:params.artist,
	    		artistLink:params.artistLink,
	    		name:params.album,
	    		link:params.albumLink,
	    		price:params.albumPrice,
	    		image:params.coverArt,
	    		rights:params.rights,
	    		releaseDate:params.releasedate
	    	)
    	}
    	
    	return albums
    }
    
    def convertXmlParams = { paramsList ->
    	def albums = []
    	paramsList.each { params ->
	    	albums << new Album(
	    		rank:params.rank,
	    		name:params.name,
	    		artist:params.artist,
	    		price:params.price,
	    		image:params.image,
	    		releaseDate:params.releaseDate,
	    		link:params.link
	    	)
    	}
    	
    	return albums
    }

}

