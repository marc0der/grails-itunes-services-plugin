package uk.co.hashcode.itunes.search

enum Media {
	ALL('all'),
	AUDIOBOOK('audiobook'), 
	MOVIE('movie'), 
	MUSIC('music'), 
	MUSIC_VIDEO('musicVideo'), 
	PODCAST('podcast'), 
	SHORT_FILM('shortFilm'), 
	TV_SHOW('tvShow') 
	
	String name
	
	public Media(String name){
		this.name = name
	}

    String toString(){
        name
    }
}
