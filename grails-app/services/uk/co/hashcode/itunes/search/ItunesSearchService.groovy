package uk.co.hashcode.itunes.search

import grails.converters.JSON
import uk.co.hashcode.itunes.Album
import uk.co.hashcode.itunes.Artist
import uk.co.hashcode.itunes.Track
import grails.plugin.springcache.annotations.Cacheable
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class ItunesSearchCommand {
    def term

    Media media = Media.MUSIC
    Entity entity = Entity.ALBUM
    Attribute attribute = Attribute.ARTIST_TERM

    Country country = Country.US
    Language language = Language.ENGLISH

    SearchProfile profile

    def limit = 20

	String execute(){
	    if(!term) throw new IllegalArgumentException('Search term not specified.')
        limit = (limit > 200) ? 200 : limit
        limit = (limit < 1) ? 1 : limit

        def mediaStr = "media=${profile?.media ?: media}"
        def entityStr = "entity=${profile?.entity ?: entity}"
        def attributeStr = "attribute=${profile?.attribute ?: attribute}"

        def countryStr = "country=$country"
        def languageStr = "language=$language"
        def limitStr = "limit=$limit"

        def termStr = "term=${URLEncoder.encode(term)}"

        def searchString = "$mediaStr&$entityStr&$attributeStr&$languageStr&$countryStr&$limitStr&$termStr"

		return searchString
	}

    def boolean equals(o){
        if(!(o && o instanceof ItunesSearchCommand)) return false

        return new EqualsBuilder()
            .append(this.media, o.media)
            .append(this.entity, o.entity)
            .append(this.attribute, o.attribute)
            .append(this.country, o.country)
            .append(this.language, o.language)
            .append(this.profile, o.profile)
            .append(this.limit, o.limit)
            .isEquals()
    }

    def int hashCode(){
        return new HashCodeBuilder(17,37)
            .append(media)
            .append(entity)
            .append(attribute)
            .append(country)
            .append(language)
            .append(profile)
            .append(limit)
            .hashCode()
    }
	
}

class ItunesSearchService {

    static transactional = false

    def unmarshallingService

    def domain

    def context


    /**
     * Search Albums by album name.
     * @param name The album name to search for
     * @return A list of Album objects.
     */
    @Cacheable('albumsByNameCache')
    List<Album> searchAlbumsByName(String name){
        def command = new ItunesSearchCommand(profile:SearchProfile.ALBUMS, term:name)
        def result = search(command)
        return unmarshallingService.unmarshallJsonAlbums(result)
    }

    /**
     * Search Albums by artist name.
     * @param artist The name of the Artist
     * @return A list of Album objects.
     */
    @Cacheable('albumsByArtistCache')
    List<Album> searchAlbumsByArtist(String artist){
        def command = new ItunesSearchCommand(profile:SearchProfile.ALBUMS_BY_ARTIST, term:artist)
        def result = search(command)
        return unmarshallingService.unmarshallJsonAlbums(result)
    }

    /**
     * Search Artists by artist name.
     * @param name The name of the artist.
     * @return A list of Artist objects.
     */
    @Cacheable('artistsByNameCache')
    List<Artist> searchArtistsByName(String name){
        def command = new ItunesSearchCommand(profile:SearchProfile.MUSIC_ARTISTS, term:name)
        def result = search(command)
        return unmarshallingService.unmarshallJsonArtists(result)
    }

    /**
     * Search Tracks by track name.
     * @param name The track name
     * @return A list of Track objects
     */
    @Cacheable('tracksByNameCache')
    List<Track> searchTracksByName(String name){
        def command = new ItunesSearchCommand(profile:SearchProfile.MUSIC_TRACKS, term:name)
        def result = search(command)
        return unmarshallingService.unmarshallJsonTracks(result)
    }

    /**
     * Search Tracks by artist name.
     * @param artist The artist name
     * @return A list of Track objects
     */
    @Cacheable('tracksByArtistCache')
    List<Track> searchTracksByArtist(String artist){
        def command = new ItunesSearchCommand(profile:SearchProfile.TRACKS_BY_ARTIST, term:artist)
        def result = search(command)
        return unmarshallingService.unmarshallJsonTracks(result)
    }

    /**
     * Search MusicVideos by artist name.
     * @param artist The artist name
     * @return A list of MusicVideo objects
     */
    List searchMusicVideosByArtist(String artist){
        throw new UnsupportedOperationException('Use SearchProfile.MUSIC_VIDEOS_BY_ARTIST for search.')
    }

    List searchMoviesByName(String name){
        throw new UnsupportedOperationException('Use SearchProfile.MOVIES for search.')
    }

    List searchMoviesByArtist(String artist){
        throw new UnsupportedOperationException('Use SearchProfile.MOVIES_BY_ARTIST for search.')
    }

    List searchMoviesByActor(String actor){
        throw new UnsupportedOperationException('Use SearchProfile.MOVIES_BY_ACTOR for search.')
    }

    List searchPodcastsByName(String podcast){
        throw new UnsupportedOperationException('Use SearchProfile.PODCASTS for search.')
    }

    List searchPodcastsByAuthor(String author){
        throw new UnsupportedOperationException('Use SearchProfile.PODCASTS_BY_AUTHOR for search.')
    }

    List searchAudiobooksByName(String name){
        throw new UnsupportedOperationException('Use SearchProfile.AUDIOBOOKS for search.')
    }

    List searchAudiobooksByAuthor(String author){
        throw new UnsupportedOperationException('Use SearchProfile.AUDIOBOOKS_BY_AUTHOR for search.')
    }

    List searchShortFilmsByName(String name){
        throw new UnsupportedOperationException('Use SearchProfile.SHORT_FILMS for search.')
    }

    List searchShortFilmsByArtist(String artist){
        throw new UnsupportedOperationException('Use SearchProfile.SHORT_FILMS_BY_ARTIST for search.')
    }

    List searchTvShowByEpisode(String episode){
        throw new UnsupportedOperationException('Use SearchProfile.TV_SHOWS_BY_EPISODE for search.')
    }

    List searchTvShowBySeason(String season){
        throw new UnsupportedOperationException('Use SearchProfile.TV_SHOWS_BY_SEASON for search.')
    }

    /**
     * General search method that takes the ItunesSearchCommand
     * as argument, and returns a GroovyObject. Use this in
     * conjunction with the getMetadata method.
     * @param command The iTunes search command
     * @return The resultant groovy object
     */
    def search(ItunesSearchCommand command) {
        def urlStr = buildUrl(command)
        def url = urlStr.toURL()
        def json = url.text
        def jsonObject = JSON.parse(json)
        return jsonObject
    }

    /**
     * Gets metadata that describes search results
     * that are retrieved in the search method.
     * @param command The command object
     * @return A key set of metadata
     */
    def getMetadata(ItunesSearchCommand command){
        def jsonObject = search(command)
        def results = jsonObject.results
        return results[0].keySet()
    }

    def buildUrl(ItunesSearchCommand command){
        if(!(domain && context)) throw new IllegalStateException('Domain or Context not set.')
        def urlString = command.execute()
        return "http://$domain/$context?$urlString"
    }

}
