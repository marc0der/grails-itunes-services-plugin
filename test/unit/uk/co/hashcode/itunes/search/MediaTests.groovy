package uk.co.hashcode.itunes.search

import grails.test.*

class MediaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testMediaName() {
		assert Media.ALL.name == 'all'
		assert Media.AUDIO_BOOK.name == 'audiobook'
		assert Media.MOVIE.name == 'movie'
		assert Media.MUSIC.name == 'music'
		assert Media.MUSIC_VIDEO.name == 'musicVideo'
		assert Media.PODCAST.name == 'podcast'
		assert Media.SHORT_FILM.name == 'shortFilm'
		assert Media.TV_SHOW.name == 'tvShow'
    }
}
