h1. iTunes Service Plugin


h3. Description

A plugin for the Grails Framework that facilitates the integration with iTunes Search Services and Feeds.
The plugin draws on the [Rome|https://rome.dev.java.net/] libraries to parse and handle feed data, and
then converts results into instances of an Album domain class. It also provides advanced Search API
functionality, marshalling these into various Domain Classes.

The net result: Wire a service into your controller, call a method, and receive back a list of Domain objects 
that may be persisted or rendered directly in your GSP.


h3. Feeds and Web Services

The initial release focused only on retrieving and processing RSS Feeds that iTunes exposes. Subsequent releases
include Artist and Album searches to mention a few. The plugin will also expand beyond the realm of Music, 
and will include Apps, Podcasts, TV Programs, Films and Music Videos.
Please let me know what else you would like from the plugin!


h3. Using the Feeds API
Add the following to your controller to use it:

{code}
class FeedsDemoController {

    def itunesFeedsService

    ...

    //inside the action closure
    def command = new FeedsCommand()
    List newReleases = itunesFeedsService.getNewAlbumReleases(command)
    List justAddedAlbums = itunesFeedsService.getJustAddedAlbums(command)
    List featuredAlbums = itunesFeedsService.getFeaturedAlbums(command)
    List topAlbums = itunesFeedsService.getTopAlbums(command)
    
    //from release 0.1.1 you can omit the command object on all service calls for default values
    List defaultNewReleases = itunesFeedsService.getNewAlbumReleases()
    ... 

}
{code}

Please refer to the source code for more details about structuring your searches, and the fields
available in the domain instances returned.

h3. Using the Search API
Add the following to your controller to use it:
{code}
class SearchDemoController {

    def itunesSearchService
    
    ...
    
    //in the action closure
    List artistByName = itunesSearchService.searchArtistsbyName('xxxx')
    
    List albumsByName = itunesSearchService.searchAlbumsByName('xxxx')
    List albumsByArtist = itunesSearchService.searchAlbumsByArtist('xxxx')
    
    List tracksByName = itunesSearchService.searchTracksByName('xxxx')
    List tracksByArtist = itunesSearchService.searchTracksByArtist('xxxx')
    ...    
        
}
{code}

Please refer to the source code for more details about structuring your searches, and the fields
available in the domain instances returned.

h3. SearchProfiles

The iTunes Search Service also provides a search() method that takes an ItunesSearchCommand object.
This ItunesSearchCommand is constructed with a search term and a SearchProfile enum instance.
This is a very powerful and convenient way of performing complex searches.

The SearchProfile enum:
{code}
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
{code}

This gives you many handy permutations of the Media, Entity and Attribute enums.
For even more granular searches, you could instantiate an ItunesSearchCommand using instances of these
low level enums.

Once again, please refer to the source code for more details. 

h3. Bugs and Enhancements

Find bugs or want enhancements?
Please come over to [http://hashcode.co.uk:8081/jira] and report any bugs or usability issues.
New feature requests are also always welcome!


h3. Source Code

Source code is available at:
[http://github.com/marcoVermeulen/grails-itunes-services-plugin]


h3. Demo

Fetch the plugin source from GitHub, step into the plugin directory and run the app:
{code}
git clone git@github.com:marcoVermeulen/grails-itunes-services-plugin.git
cd grails-itunes-services-plugin
grails run-app
{code}

Wait for the application to start up and point your browser at 
[http://localhost:8080/itunes-service/feedsDemo]
[http://localhost:8080/itunes-service/searchDemo]

Thanks and enjoy the plugin!
