package uk.co.hashcode.itunes

import grails.test.*

class AlbumTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAlbumConstructorWithParameters() {
    	def rankParam = 1
    	def artistParam = 'Lee DeWyze'
    	def artistLinkParam = 'http://itunes.apple.com/artist/lee-dewyze/id356767680?uo=1&v0=9988'
    	def albumParam = 'Debut Album'
    	def albumLinkParam = 'http://itunes.apple.com/preorder/debut-album/id374940214?uo=1&v0=9988'
    	def albumPriceParam = '$11.99'
    	def coverArtParam = 'http://a1.phobos.apple.com/us/r30/Features/cf/67/68/dj.eqozraou.100x100-75.jpg'
    	def rightsParam = 'Not Available'
    	def releasedateParam = 'October 26, 2010'
    		
    	def params = [
    	    rank:rankParam, 
    	    artist:artistParam, 
    	    artistLink:artistLinkParam, 
    	    album:albumParam, 
    	    albumLink:albumLinkParam, 
    	    albumPrice:albumPriceParam, 
    	    coverArt:coverArtParam, 
    	    rights:rightsParam, 
    	    releasedate:releasedateParam
    	]
    	
    	Album album = new Album(params)
    	assert album.rank == 1
    	assert album.artist == artistParam
    	assert album.artistLink == artistLinkParam
    	assert album.album == albumParam
    	assert album.albumLink == albumLinkParam
    	assert album.albumPrice == albumPriceParam
    	assert album.coverArt == coverArtParam
    	assert album.rights == rightsParam
    	assert album.releasedate == releasedateParam
    	
    }
}
