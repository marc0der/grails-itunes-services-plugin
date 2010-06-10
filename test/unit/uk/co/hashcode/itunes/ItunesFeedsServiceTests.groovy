package uk.co.hashcode.itunes

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail

class ItunesFeedsServiceTests {
	def feedsService
	
	@Before
    void setUp() {
        feedsService = new ItunesFeedsService()
    }

	@After
    void tearDown() {

	}

	@Test
    void testConvertRssParams() {
    	def rank = 1
    	def artist = 'Lee DeWyze'
    	def artistLink = 'http://itunes.apple.com/artist/lee-dewyze/id356767680?uo=1&v0=9988'
    	def album = 'Debut Album'
    	def albumLink = 'http://itunes.apple.com/preorder/debut-album/id374940214?uo=1&v0=9988'
    	def albumPrice = '$11.99'
    	def coverArt = 'http://a1.phobos.apple.com/us/r30/Features/cf/67/68/dj.eqozraou.100x100-75.jpg'
    	def rights = 'Not Available'
    	def releasedate = 'October 26, 2010'
    		
    	def params = [[
    	    rank:rank, 
    	    artist:artist, 
    	    artistLink:artistLink, 
    	    album:album,
    	    albumLink:albumLink,
    	    albumPrice:albumPrice,
    	    coverArt:coverArt,
    	    rights:rights,
    	    releasedate:releasedate
    	]]
    	
    	List albums  = feedsService.convertRssParams(params)
    	assert albums.size() == 1
    	
    	Album albumInstance = albums[0]
    	assert albumInstance.rank == rank
    	assert albumInstance.artist == artist
    	assert albumInstance.artistLink == artistLink
    	assert albumInstance.name == album
    	assert albumInstance.link == albumLink
    	assert albumInstance.price == albumPrice
    	assert albumInstance.image == coverArt
    	assert albumInstance.rights == rights
    	assert albumInstance.releaseDate == releasedate
    	
    }
    
	@Test
    void testConvertXmlParams(){
    	def rank = 1
    	def name = 'Rokstarr (Bonus Track Version)'
    	def contentType = 'Not Available'
    	def artist = 'Taio Cruz'
    	def price = '$6.99'
    	def image = 'http://a1.phobos.apple.com/us/r30/Music/38/4a/e9/mzi.mwjgftoj.170x170-75.jpg'
    	def releaseDate = '2010-06-01T00:00:00-07:00'
    	def itemCount = '13'
    	def link = 'http://itunes.apple.com/us/album/rokstarr-bonus-track-version/id373808864?uo=2&uo=2'

    	def params = [[
    	    rank:rank,
    	    name:name,
    	    contentType:contentType,
    	    artist:artist,
    	    price:price,
    	    image:image,
    	    releaseDate:releaseDate,
    	    itemCount:itemCount,
    	    link:link,
    	]]

    	
    	List albums = feedsService.convertXmlParams(params)
    	assert albums.size() == 1
    	
    	Album albumInstance = albums[0]
    	assert albumInstance.rank == rank
    	assert albumInstance.name == name
    	assert albumInstance.artist == artist
    	assert albumInstance.price == price
    	assert albumInstance.image == image
    	assert albumInstance.releaseDate == releaseDate
    	assert albumInstance.link == link

    }
	
	@Test
	void testGetNewAlbumReleasesWithCommand(){
		def command = new FeedsCommand()
		def expected = []
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.NEW_RELEASES
		}
		
		List releases = feedsService.getNewAlbumReleases(command)
		assert expected == releases
		
	}
	
	@Test
	void testGetNewAlbumReleasesWithoutCommand(){
		def expected = []
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.NEW_RELEASES
		}
		
		List releases = feedsService.getNewAlbumReleases()
		assert expected == releases
	}
	
	@Test
	void testGetJustAddedAlbumsWithCommand(){
		def expected = []
		def command = new FeedsCommand()
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.JUST_ADDED
		}
		
		List albums = feedsService.getJustAddedAlbums(command)
		assert expected == albums
	}

	@Test
	void testGetJustAddedAlbumsWithoutCommand(){
		def expected = []
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.JUST_ADDED
		}
		
		List albums = feedsService.getJustAddedAlbums()
		assert expected == albums
	}
	
	@Test
	void testGetFeaturedAlbumsWithCommand(){
		def expected = []
		def command = new FeedsCommand()
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.FEATURED_ALBUMS
		}
		
		List albums = feedsService.getFeaturedAlbums(command)
		assert expected == albums
	}
	
	@Test
	void testGetFeaturedAlbumsWithoutCommand(){
		def expected = []
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.FEATURED_ALBUMS
		}
		
		List albums = feedsService.getFeaturedAlbums()
		assert expected == albums
	}

	@Test
	void testGetTopAlbumsWithCommand(){
		def expected = []
		def command = new FeedsCommand()
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.TOP_ALBUMS
		}
		
		List albums = feedsService.getTopAlbums(command)
		assert expected == albums
	}
	
	@Test
	void testGetTopAlbumsWithoutCommand(){
		def expected = []
		feedsService.convertRssParams = { return expected }
		feedsService.fetch = { cmd ->
			assert cmd.feedType == FeedType.TOP_ALBUMS
		}
		
		List albums = feedsService.getTopAlbums()
		assert expected == albums
	}

	@Test(expected=UnsupportedOperationException)
    void testGetTopIMixesWithCommand(){
    	feedsService.getTopIMixes(new FeedsCommand())
    }
	
	@Test(expected=UnsupportedOperationException)
    void testGetTopIMixesWithoutCommand(){
    	feedsService.getTopIMixes()
    }
    
	@Test(expected=UnsupportedOperationException)
    void testGetTopSongsWithCommand(){
    	feedsService.getTopSongs(new FeedsCommand())
    }

	@Test(expected=UnsupportedOperationException)
    void testGetTopSongsWithoutCommand(){
    	feedsService.getTopSongs()
    }
}
