package uk.co.hashcode.itunes.search

import grails.test.*

class EntityTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEntityName() {
		assert Entity.ALL_ARTIST.name == 'allArtist'
		assert Entity.ALL_TRACK.name == 'allTrack'
		assert Entity.ALBUM.name == 'album'
		assert Entity.AUDIOBOOK.name == 'audiobook'
		assert Entity.AUDIOBOOK_AUTHOR.name == 'audiobookAuthor'
		assert Entity.MIX.name == 'mix'
		assert Entity.MOVIE.name == 'movie'
		assert Entity.MOVIE_ARTIST.name == 'movieArtist'
		assert Entity.MUSIC_ARTIST.name == 'musicArtist'
		assert Entity.MUSIC_TRACK.name == 'musicTrack'
		assert Entity.MUSIC_VIDEO.name == 'musicVideo'
		assert Entity.PODCAST.name == 'podcast'
		assert Entity.PODCAST_AUTHOR.name == 'podcastAuthor'
		assert Entity.SHORT_FILM.name == 'shortFilm'
		assert Entity.SHORT_FILM_ARTIST.name == 'shortFilmArtist'
		assert Entity.TV_EPISODE.name == 'tvEpisode'
		assert Entity.TV_SEASON.name == 'tvSeason'
    }
}
