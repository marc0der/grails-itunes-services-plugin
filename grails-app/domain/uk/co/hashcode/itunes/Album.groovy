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
	def contentType
	def itemCount
	
    static constraints = {
    }

	String getAlbum(){
		return name
	}
	
	void setAlbum(String album){
		this.name = album
	}
	
	String getAlbumLink(){
		return link
	}
	
	void setAlbumLink(String albumLink){
		this.link = albumLink
	}
	
	String getAlbumPrice(){
		return price
	}
	
	void setAlbumPrice(String albumPrice){
		this.price = albumPrice
	}
	
	String getCoverArt(){
		return image
	}
	
	void setCoverArt(String coverArt){
		this.image = coverArt
	}
	
	String getReleasedate(){
		return releaseDate
	}
	
	void setReleasedate(String releasedate){
		this.releaseDate = releasedate
	}
	
	String toString(){
		"[Album: $id, rank:$rank, artist:$artist, artistLink:$artistLink, album:$name, albumLink:$link, albumPrice:$price, coverArt:$image, rights:$rights, releasedate:$releaseDate]"
	}
}
