package uk.co.hashcode.itunes.search

/**
* Provides convenient combinations of Media, Entity and Attributes
* for use in your searches.
* @author marco
*
*/
enum SearchProfile {
    ALBUMS('Albums', Media.MUSIC, Entity.ALBUM, Attribute.ALBUM_TERM),
    MUSIC_ARTISTS('Artists', Media.MUSIC, Entity.MUSIC_ARTIST, Attribute.ARTIST_TERM),
    MUSIC_TRACKS('Tracks', Media.MUSIC, Entity.MUSIC_TRACK, Attribute.MUSIC_TRACK_TERM),
    ALBUMS_BY_ARTIST('Albums by Artist', Media.MUSIC, Entity.ALBUM, Attribute.ARTIST_TERM),
    MUSIC_VIDEOS_BY_ARTIST('Music Videos by Artist', Media.MUSIC, Entity.MUSIC_VIDEO, Attribute.ARTIST_TERM),
    TRACKS_BY_ARTIST('Tracks by Artist', Media.MUSIC, Entity.MUSIC_TRACK, Attribute.ARTIST_TERM),
    MOVIES('Movies', Media.MOVIE, Entity.MOVIE, Attribute.MOVIE_TERM),
    MOVIES_BY_ACTOR('Movies by Actor', Media.MOVIE, Entity.MOVIE, Attribute.ACTOR_TERM),
    MOVIES_BY_ARTIST('Movies by Artist', Media.MOVIE, Entity.MOVIE, Attribute.MOVIE_ARTIST_TERM),
    PODCASTS('Podcasts', Media.PODCAST, Entity.PODCAST, Attribute.TITLE_TERM),
    PODCASTS_BY_AUTHOR('Podcasts by Author', Media.PODCAST, Entity.PODCAST, Attribute.AUTHOR_TERM),
    AUDIOBOOKS('Audiobooks', Media.AUDIOBOOK, Entity.AUDIOBOOK, Attribute.TITLE_TERM),
    AUDIOBOOKS_BY_AUTHOR('Audiobooks by Author', Media.AUDIOBOOK, Entity.AUDIOBOOK, Attribute.AUTHOR_TERM),
    SHORT_FILMS('Short Films', Media.SHORT_FILM, Entity.SHORT_FILM, Attribute.SHORT_FILM_TERM),
    SHORT_FILMS_BY_ARTIST('Short Films by Artist', Media.SHORT_FILM, Entity.SHORT_FILM, Attribute.ARTIST_TERM),
    TV_SHOWS_BY_EPISODE('TV Shows by Episode', Media.TV_SHOW, Entity.TV_EPISODE, Attribute.TV_EPISODE_TERM),
    TV_SHOWS_BY_SEASON('TV Shows by Season', Media.TV_SHOW, Entity.TV_SEASON, Attribute.TV_SEASON_TERM)
    
    String name
    Media media
    Entity entity
    Attribute attribute
    
    public SearchProfile(String name, Media media, Entity entity, Attribute attribute){
        this.name = name
        this.media = media
        this.entity = entity
        this.attribute = attribute
    }
    
}
