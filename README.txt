h1. iTunes Service Plugin


h3. Description

A plugin for the Grails Framework that facilitates the integration with iTunes Web Services and Feeds.
The plugin draws on the [Rome|https://rome.dev.java.net/] libraries to parse and handle feed data, and
then converts results into instances of an Album domain class. 

The net result: Wire a service into your controller, call a method, and receive back a list of Domain objects 
that may be persisted or rendered directly in your GSP.


h3. Feeds and Web Services

The initial release focuses only on retrieving and processing RSS Feeds that iTunes exposes. Subsequent releases
will include Artist and Album searches. The plugin will also expand beyond the realm of Music, and will include Apps, 
Podcasts, TV Programs, Films and Music Videos. Please let me know what else you would like from the plugin!


h3. Using It
Add the following to your controller to use it:

{code}
class DemoController {

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


Each of these service methods return a List of @uk.co.hashcode.itunes.Album@ instances.
The Album instance has the following fields:

{code}
int rank
String artist
String artistLink
String name
String link
String price
String image
String rights
Date releaseDate
{code}


The results may be refined by constructing the @uk.co.hashcode.itunes.FeedsCommand@ as follows:

{code}
def command = new FeedsCommand(
    limit=10,               //amount of results, min 1, max 25
    genre=Genre.POP,        //see below for all possible genres
    country=Country.UK      //see below for all possible countries
)
{code}


The @uk.co.hashcode.itunes.Genre@ enum has the following values:
{code}
ALL
ALTERNATIVE
BLUES
CHILDREN
CLASSICAL
COMEDY
COUNTRY
DANCE
ELECTRONIC
FITNESS_WORKOUT
HIPHOP_RAP
JAZZ
LATINO
POP
RB_SOUL
REGGAE
RELIGEOUS
ROCK
SOUNDTRACK
SPOKEN_WORD
WORLD
{code}


The @uk.co.hashcode.itunes.Country@ enum has the following values:
{code}
AUSTRALIA
AUSTRIA
BELGIUM
CANADA
DENMARK
FINLAND
FRANCE
GERMANY
GREECE
IRELAND
ITALY
JAPAN
LUXEMBOURG
MEXICO
NETHERLANDS
NEW_ZEALAND
NORWAY
PORTUGAL
SPAIN
SWEDEN
SWITZERLAND
UK
USA
{code}


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

Wait for the application to start up and point your browser at [http://localhost:8080/itunes-service/demo]

Thanks and enjoy the plugin!
