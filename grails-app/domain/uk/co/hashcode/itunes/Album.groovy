package uk.co.hashcode.itunes

class Album {
	int rank
	String artist
	String artistLink
	String name
	String link
	String price
	String image
	String rights
	Date releaseDate
	
    static constraints = {
    }
	
	String toString(){
		"[Album: $id, rank:$rank, artist:$artist, artistLink:$artistLink, album:$name, albumLink:$link, albumPrice:$price, coverArt:$image, rights:$rights, releasedate:$releaseDate]"
	}
}
