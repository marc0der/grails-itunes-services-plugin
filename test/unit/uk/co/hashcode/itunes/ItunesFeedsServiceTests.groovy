package uk.co.hashcode.itunes

import grails.test.*
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sun.util.calendar.LocalGregorianCalendar.Date;

class ItunesFeedsServiceTests extends GrailsUnitTestCase {
	ItunesFeedsService itunesFeedsService
	
    protected void setUp() {
        super.setUp()
        itunesFeedsService = new ItunesFeedsService()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConvertRssParams() {
    	def formatter = new SimpleDateFormat('MMM dd, yyyy')
    	
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
    	
    	List albums  = itunesFeedsService.convertRssParams(params)
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
    	assert albumInstance.releaseDate == formatter.parse(releasedate)
    	
    }
    
    void testConvertXmlParams(){
    	def formatter = new SimpleDateFormat('yyyy-MM-dd')
    	
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

    	
    	List albums = itunesFeedsService.convertXmlParams(params)
    	assert albums.size() == 1
    	
    	Album albumInstance = albums[0]
    	assert albumInstance.rank == rank
    	assert albumInstance.name == name
    	assert albumInstance.artist == artist
    	assert albumInstance.price == price
    	assert albumInstance.image == image
    	assert albumInstance.releaseDate == formatter.parse(releaseDate)
    	assert albumInstance.link == link

    }
    
    void testDateConversionForRssFeeds(){
    	def date = 'October 26, 2010'
    	def params = [[releasedate:date]] //subtly different with no upper case D
    	List albums = itunesFeedsService.convertRssParams(params)
    	
    	assert albums != null
    	assert albums.size() == 1
    	
    	Album album = albums[0]
    	def releaseDate = album.releaseDate
    	assert releaseDate != null
    	
    	def cal = new GregorianCalendar()
    	cal.set 2010, Calendar.OCTOBER, 26
    	assert cal.time.compareTo(releaseDate)

    }
    
    void testDateConversionForXmlFeeds(){
    	def date = '2010-06-01T00:00:00-07:00'
    	def params = [[releaseDate:date]]
    	List albums = itunesFeedsService.convertXmlParams(params)
    	
    	assert albums != null
    	assert albums.size() == 1
    	
    	Album album = albums[0]
    	def releaseDate = album.releaseDate
    	assert releaseDate != null
    	
    	def cal = new GregorianCalendar()
    	cal.set 2010, Calendar.JUNE, 1
    	assert cal.time.compareTo(releaseDate)
    			
    }
}
