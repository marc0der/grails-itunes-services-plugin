package uk.co.hashcode.itunes

class Album {
	int rank
	String artist
	String name
	String link
	String price
	String image
	String rights
	String releaseDate
	String artistLink = ''
	
    static constraints = {
    }
	
	String toString(){
		"[Album: $id, rank:$rank, artist:$artist, artistLink:$artistLink, album:$name, albumLink:$link, albumPrice:$price, coverArt:$image, rights:$rights, releasedate:$releaseDate]"
	}
}
