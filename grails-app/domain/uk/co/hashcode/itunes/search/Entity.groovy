package uk.co.hashcode.itunes.search

enum Entity {
	ALL_ARTIST('allArtist'),
	ALL_TRACK('allTrack'),
	ALBUM('album'),
	AUDIOBOOK('audiobook'),
	AUDIOBOOK_AUTHOR('audiobookAuthor'),
	MIX('mix'),
	MOVIE('movie'),
	MOVIE_ARTIST('movieArtist'),
	MUSIC_ARTIST('musicArtist'),
	MUSIC_TRACK('musicTrack'),
	MUSIC_VIDEO('musicVideo'),
	PODCAST('podcast'),
	PODCAST_AUTHOR('podcastAuthor'),
	SHORT_FILM('shortFilm'),
	SHORT_FILM_ARTIST('shortFilmArtist'),
	TV_EPISODE('tvEpisode'),
	TV_SEASON('tvSeason');
	
	String name
	
	public Entity(String name){
	    this.name = name
	}
}
