package uk.co.hashcode.itunes

class Album {
	def rank
	def artist
	def artistLink
	def name
	def link
	def price
	def image
	def rights
	def releaseDate
	
    static constraints = {
    }
	
	String toString(){
		"[Album: $id, rank:$rank, artist:$artist, artistLink:$artistLink, album:$name, albumLink:$link, albumPrice:$price, coverArt:$image, rights:$rights, releasedate:$releaseDate]"
	}
}
