package uk.co.hashcode.itunes

/**
 * Represents an album on the iTunes Store.
 */
class Album {
	int rank
    long albumId
    String name
    String link
    String artist
    long artistId
    String price
	String image
	String rights
	String releaseDate
	String artistLink
	
    static constraints = {
    }
    
    static mapping = {
        table 'itunes_album'
    }
	
	String toString(){
		"[Album: $id, rank:$rank, artist:$artist, artistLink:$artistLink, album:$name, albumLink:$link, albumPrice:$price, coverArt:$image, rights:$rights, releasedate:$releaseDate]"
	}
}
