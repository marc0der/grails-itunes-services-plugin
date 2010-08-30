package uk.co.hashcode.itunes.search

import grails.converters.JSON
import uk.co.hashcode.itunes.Album
import uk.co.hashcode.itunes.Artist

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
	
}

class ItunesSearchService {

    static transactional = false

    def domain

    def context


    /**
     * Search Albums by album name.
     * @param name The album name to search for
     * @return A list of Album objects.
     */
    List<Album> searchAlbumsByName(String name){
        def command = new ItunesSearchCommand(profile:SearchProfile.ALBUMS, term:name)
        def result = search(command)
        return unmarshallJsonAlbums(result)
    }

    /**
     * Search Albums by artist name.
     * @param artist The name of the Artist
     * @return A list of Album objects.
     */
    List<Album> searchAlbumsByArtist(String artist){
        def command = new ItunesSearchCommand(profile:SearchProfile.ALBUMS_BY_ARTIST, term:artist)
        def result = search(command)
        return unmarshallJsonAlbums(result)
    }


    /**
     * Search Artists by artist name.
     * @param name The name of the artist.
     * @return A list of Artist objects.
     */
    List<Artist> searchArtistsByName(String name){
        def command = new ItunesSearchCommand(profile:SearchProfile.MUSIC_ARTISTS, term:name)
        def result = search(command)
        return unmarshallJsonArtists(result)
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

    List<Album> unmarshallJsonAlbums(def jsonObject){
        def albums = []
        jsonObject.results.each { album ->
            albums << new Album(
                    albumId:album.collectionId,
                    name:album.collectionName,
                    link:album.collectionViewUrl,
                    artistId:album.artistId,
                    artist:album.artistName,
                    price:album.collectionPrice,
                    image:album.artworkUrl100,
                    rights:album.copyright,
                    releaseDate:album.releaseDate,
                    artistLink:album.artistViewUrl
            )
        }

        return albums
    }

    List<Artist> unmarshallJsonArtists(def jsonObject){
        def artists = []
        jsonObject.results.each { artist ->
            assert artist.wrapperType == 'artist'
            artists << new Artist(
                    artistId:artist.artistId,
                    amgArtistId:artist.amgArtistId,
                    amgVideoArtistId:artist.amgVideoArtistId,
                    name:artist.artistName,
                    link:artist.artistLinkUrl,
                    artistType:artist.artistType,
                    genreId:artist.primaryGenreId,
                    genreName:artist.primaryGenreName
            )
        }
        return artists
    }
}
