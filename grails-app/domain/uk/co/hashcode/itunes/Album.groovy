package uk.co.hashcode.itunes

class Album {
	def rank
	def artist
	def artistLink
	def album
	def albumLink
	def albumPrice
	def coverArt
	def rights
	def releasedate
	
    static constraints = {
    }
	
	String toString(){
		"[Album: $id, rank:$rank, artist:$artist, artistLink:$artistLink, album:$album, albumLink:$albumLink, albumPrice:$albumPrice, coverArt:$coverArt, rights:$rights, releasedate:$releasedate]"
	}
}
