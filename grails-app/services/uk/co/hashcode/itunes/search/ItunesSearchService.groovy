package uk.co.hashcode.itunes.search

import grails.converters.JSON
import uk.co.hashcode.itunes.Album

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
     * General search method that takes the ItunesSearchCommand
     * as argument, and returns a GroovyObject.
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

    def buildUrl(ItunesSearchCommand command){
        if(!(domain && context)) throw new IllegalStateException('Domain or Context not set.')
        def urlString = command.execute()
        return "http://$domain/$context?$urlString"
    }

    List<Album> unmarshallJsonAlbums(def jsonObject){
        def albums = []
        jsonObject.results.each { album ->
            albums << new Album(
                    artist:album.artistName,
                    name:album.collectionName,
                    link:album.collectionViewUrl,
                    price:album.collectionPrice,
                    image:album.artworkUrl100,
                    rights:album.copyright,
                    releaseDate:album.releaseDate,
                    artistLink:album.artistViewUrl
            )
        }

        return albums
    }
}
