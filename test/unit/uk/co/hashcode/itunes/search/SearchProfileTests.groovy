package uk.co.hashcode.itunes.search

import grails.test.*

class SearchProfileTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testSearchProfileAlbums() {
		assert SearchProfile.ALBUMS.name == 'Albums'
		assert SearchProfile.ALBUMS.media == Media.MUSIC
		assert SearchProfile.ALBUMS.entity == Entity.ALBUM
		assert SearchProfile.ALBUMS.attribute == Attribute.ALBUM_TERM
	}
	
	void testSearchProfileMusicArtists() {
		assert SearchProfile.MUSIC_ARTISTS.name == 'Artists'
		assert SearchProfile.MUSIC_ARTISTS.media == Media.MUSIC
		assert SearchProfile.MUSIC_ARTISTS.entity == Entity.MUSIC_ARTIST
		assert SearchProfile.MUSIC_ARTISTS.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileMusicTracks() {
	    assert SearchProfile.MUSIC_TRACKS.name == 'Tracks'
	    assert SearchProfile.MUSIC_TRACKS.media == Media.MUSIC
	    assert SearchProfile.MUSIC_TRACKS.entity == Entity.MUSIC_TRACK
	    assert SearchProfile.MUSIC_TRACKS.attribute == Attribute.MUSIC_TRACK_TERM
	}
	
	void testSearchProfileAlbumsByArtist() {
	    assert SearchProfile.ALBUMS_BY_ARTIST.name == 'Albums by Artist'
	    assert SearchProfile.ALBUMS_BY_ARTIST.media == Media.MUSIC
	    assert SearchProfile.ALBUMS_BY_ARTIST.entity == Entity.ALBUM
	    assert SearchProfile.ALBUMS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileMusicVideosByArtist() {
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.name == 'Music Videos by Artist'
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.media == Media.MUSIC
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.entity == Entity.MUSIC_VIDEO
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileTracksByArtist() {
	    assert SearchProfile.TRACKS_BY_ARTIST.name == 'Tracks by Artist'
	    assert SearchProfile.TRACKS_BY_ARTIST.media == Media.MUSIC
	    assert SearchProfile.TRACKS_BY_ARTIST.entity == Entity.MUSIC_TRACK
	    assert SearchProfile.TRACKS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileMovies() {
	    assert SearchProfile.MOVIES.name == 'Movies'
	    assert SearchProfile.MOVIES.media == Media.MOVIE
	    assert SearchProfile.MOVIES.entity == Entity.MOVIE
	    assert SearchProfile.MOVIES.attribute == Attribute.MOVIE_TERM
	}
	
	void testSearchProfileMoviesByActor() {
	    assert SearchProfile.MOVIES_BY_ACTOR.name == 'Movies by Actor'
	    assert SearchProfile.MOVIES_BY_ACTOR.media == Media.MOVIE
	    assert SearchProfile.MOVIES_BY_ACTOR.entity == Entity.MOVIE
	    assert SearchProfile.MOVIES_BY_ACTOR.attribute == Attribute.ACTOR_TERM
	}

	void testSearchProfileMoviesByArtist() {
	    assert SearchProfile.MOVIES_BY_ARTIST.name == 'Movies by Artist'
	    assert SearchProfile.MOVIES_BY_ARTIST.media == Media.MOVIE
	    assert SearchProfile.MOVIES_BY_ARTIST.entity == Entity.MOVIE
	    assert SearchProfile.MOVIES_BY_ARTIST.attribute == Attribute.MOVIE_ARTIST_TERM
	}
	
	void testSearchProfilePodcasts() {
	    assert SearchProfile.PODCASTS.name == 'Podcasts'
	    assert SearchProfile.PODCASTS.media == Media.PODCAST
	    assert SearchProfile.PODCASTS.entity == Entity.PODCAST
	    assert SearchProfile.PODCASTS.attribute == Attribute.TITLE_TERM
	}
	
	void testSearchProfilePodcastsByAuthor() {
	    assert SearchProfile.PODCASTS_BY_AUTHOR.name == 'Podcasts by Author'
	    assert SearchProfile.PODCASTS_BY_AUTHOR.media == Media.PODCAST
	    assert SearchProfile.PODCASTS_BY_AUTHOR.entity == Entity.PODCAST
	    assert SearchProfile.PODCASTS_BY_AUTHOR.attribute == Attribute.AUTHOR_TERM
	}

	void testSearchProfileAudioBooks() {
	    assert SearchProfile.AUDIOBOOKS.name == 'Audiobooks'
	    assert SearchProfile.AUDIOBOOKS.media == Media.AUDIOBOOK
	    assert SearchProfile.AUDIOBOOKS.entity == Entity.AUDIOBOOK
	    assert SearchProfile.AUDIOBOOKS.attribute == Attribute.TITLE_TERM
	}

	void testSearchProfileAudioBooksByAuthor() {
	    assert SearchProfile.AUDIOBOOKS_BY_AUTHOR.name == 'Audiobooks by Author'
	    assert SearchProfile.AUDIOBOOKS_BY_AUTHOR.media == Media.AUDIOBOOK
	    assert SearchProfile.AUDIOBOOKS_BY_AUTHOR.entity == Entity.AUDIOBOOK
	    assert SearchProfile.AUDIOBOOKS_BY_AUTHOR.attribute == Attribute.AUTHOR_TERM
	}
	
	void testSearchProfileShortFilms() {
	    assert SearchProfile.SHORT_FILMS.name == 'Short Films'
	    assert SearchProfile.SHORT_FILMS.media == Media.SHORT_FILM
	    assert SearchProfile.SHORT_FILMS.entity == Entity.SHORT_FILM
	    assert SearchProfile.SHORT_FILMS.attribute == Attribute.SHORT_FILM_TERM
	}
	
	void testSearchProfileShortFilmsByShortFilmArtist() {
	    assert SearchProfile.SHORT_FILMS_BY_ARTIST.name == 'Short Films by Artist'
	    assert SearchProfile.SHORT_FILMS_BY_ARTIST.media == Media.SHORT_FILM
	    assert SearchProfile.SHORT_FILMS_BY_ARTIST.entity == Entity.SHORT_FILM
	    assert SearchProfile.SHORT_FILMS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileTvShowsByEpisode() {
	    assert SearchProfile.TV_SHOWS_BY_EPISODE.name == 'TV Shows by Episode'
	    assert SearchProfile.TV_SHOWS_BY_EPISODE.media == Media.TV_SHOW
	    assert SearchProfile.TV_SHOWS_BY_EPISODE.entity == Entity.TV_EPISODE
	    assert SearchProfile.TV_SHOWS_BY_EPISODE.attribute == Attribute.TV_EPISODE_TERM
	}
	
	void testSearchProfileTvShowsBySeries() {
	    assert SearchProfile.TV_SHOWS_BY_SEASON.name == 'TV Shows by Season'
	    assert SearchProfile.TV_SHOWS_BY_SEASON.media == Media.TV_SHOW
	    assert SearchProfile.TV_SHOWS_BY_SEASON.entity == Entity.TV_SEASON
	    assert SearchProfile.TV_SHOWS_BY_SEASON.attribute == Attribute.TV_SEASON_TERM
	}
	
}
