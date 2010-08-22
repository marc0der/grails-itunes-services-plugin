package uk.co.hashcode.itunes.search

enum Attribute {
    ACTOR_TERM('actorTerm'), 
    ALBUM_TERM('albumTerm'), 
    ALL_ARTIST_TERM('allArtistTerm'), 
    ALL_TRACK_TERM('allTrackTerm'), 
    ARTIST_TERM('artistTerm'), 
    AUTHOR_TERM('authorTerm'), 
    COMPOSER_TERM('composerTerm'), 
    DESCRIPTION_TERM('descriptionTerm'), 
    DIRECTOR_TERM('directorTerm'), 
    FEATURE_FILM_TERM('featureFilmTerm'), 
    GENRE_INDEX('genreIndex'), 
    KEYWORDS_TERM('keywordsTerm'), 
    LANGUAGE_TERM('languageTerm'), 
    MIX_TERM('mixTerm'), 
    MOVIE_ARTIST_TERM('movieArtistTerm'), 
    MOVIE_TERM('movieTerm'), 
    MUSIC_TRACK_TERM('musicTrackTerm'), 
    PRODUCER_TERM('producerTerm'), 
    RATING_INDEX('ratingIndex'), 
    RATING_TERM('ratingTerm'), 
    RELEASE_YEAR_TERM('releaseYearTerm'), 
    SHORT_FILM_TERM('shortFilmTerm'), 
    SHOW_TERM('showTerm'), 
    SONG_TERM('songTerm'), 
    TITLE_TERM('titleTerm'), 
    TV_EPISODE_TERM('tvEpisodeTerm'), 
    TV_SEASON_TERM('tvSeasonTerm')
    
    String name
    
    public Attribute(String name){
        this.name =name
    }

    String toString(){
        name
    }
}
