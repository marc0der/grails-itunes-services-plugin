package uk.co.hashcode.itunes.search

/**
* Provides convenient combinations of Media, Entity and Attributes
* for use in your searches.
* @author marco
*
*/
enum SearchProfile {
    ALBUM('Album', Media.MUSIC, Entity.ALBUM, Attribute.ALBUM_TERM),
    MUSIC_ARTIST('Artist', Media.MUSIC, Entity.MUSIC_ARTIST, Attribute.ARTIST_TERM),
    MUSIC_TRACK('Track', Media.MUSIC, Entity.MUSIC_TRACK, Attribute.MUSIC_TRACK_TERM),
    ALBUMS_BY_ARTIST('Albums by Artist', Media.MUSIC, Entity.ALBUM, Attribute.ARTIST_TERM),
    MUSIC_VIDEOS_BY_ARTIST('Music Videos by Artist', Media.MUSIC, Entity.MUSIC_VIDEO, Attribute.ARTIST_TERM),
    TRACKS_BY_ARTIST('Tracks by Artist', Media.MUSIC, Entity.MUSIC_TRACK, Attribute.ARTIST_TERM)
//	MOVIE,
//	MOVIE_BY_ARTIST,
//	PODCAST,
//	PODCAST_BY_AUTHOR,
//	AUDIOBOOK,
//	AUDIOBOOK_BY_AUTHOR,
//	SHORT_FILM,
//	SHORT_FILM_BY_ARTIST,
//	TV_SHOW_BY_EPISODE,
//	TV_SHOW_BY_SEASON
    
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
