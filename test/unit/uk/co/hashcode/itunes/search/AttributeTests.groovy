package uk.co.hashcode.itunes.search

import grails.test.*

class AttributeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAttributenName() {
        assert Attribute.ACTOR_TERM.name == 'actorTerm' 
        assert Attribute.ALBUM_TERM.name == 'albumTerm' 
        assert Attribute.ALL_ARTIST_TERM.name == 'allArtistTerm' 
        assert Attribute.ALL_TRACK_TERM.name == 'allTrackTerm' 
        assert Attribute.ARTIST_TERM.name == 'artistTerm' 
        assert Attribute.AUTHOR_TERM.name == 'authorTerm' 
        assert Attribute.COMPOSER_TERM.name == 'composerTerm' 
        assert Attribute.DESCRIPTION_TERM.name == 'descriptionTerm' 
        assert Attribute.DIRECTOR_TERM.name == 'directorTerm' 
        assert Attribute.FEATURE_FILM_TERM.name == 'featureFilmTerm' 
        assert Attribute.GENRE_INDEX.name == 'genreIndex' 
        assert Attribute.KEYWORDS_TERM.name == 'keywordsTerm' 
        assert Attribute.LANGUAGE_TERM.name == 'languageTerm' 
        assert Attribute.MIX_TERM.name == 'mixTerm' 
        assert Attribute.MOVIE_ARTIST_TERM.name == 'movieArtistTerm' 
        assert Attribute.MOVIE_TERM.name == 'movieTerm' 
        assert Attribute.MUSIC_TRACK_TERM.name == 'musicTrackTerm' 
        assert Attribute.PRODUCER_TERM.name == 'producerTerm' 
        assert Attribute.RATING_INDEX.name == 'ratingIndex' 
        assert Attribute.RATING_TERM.name == 'ratingTerm' 
        assert Attribute.RELEASE_YEAR_TERM.name == 'releaseYearTerm' 
        assert Attribute.SHORT_FILM_TERM.name == 'shortFilmTerm' 
        assert Attribute.SHOW_TERM.name == 'showTerm' 
        assert Attribute.SONG_TERM.name == 'songTerm' 
        assert Attribute.TITLE_TERM.name == 'titleTerm' 
        assert Attribute.TV_EPISODE_TERM.name == 'tvEpisodeTerm' 
        assert Attribute.TV_SEASON_TERM.name == 'tvSeasonTerm'

    }
}
