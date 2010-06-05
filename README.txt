=============================
Grails iTunes Services Plugin
=============================

A plugin for the Grails Framework that facilitates the integration with iTunes Web Services and Feeds.
The plugin draws on the Rome libraries (https://rome.dev.java.net/) to parse and handle feed data, and
then converts results into instances of an Album domain class. 

The net result: Wire a service into your controller, call a method, and receive back a list of Domain objects 
that can may be persisted or rendered directly in your GSP.

Install plugin:
In the root of your grails project, use the command line to install the plugin with:
$ grails install-plugin itunes-services

Use it:
Add the following to your controller to use it:

class DemoController {

    def itunesFeedsService

    ...

    //inside the action closure
    def command = new FeedsCommand()
    List newReleases = itunesFeedsService.getNewAlbumReleases(command)
    List justAddedAlbums = itunesFeedsService.getJustAddedAlbums(command)
    List featuredAlbums = itunesFeedsService.getFeaturedAlbums(command)
    List topAlbums = itunesFeedsService.getTopAlbums(command)
       
    ... 

}

Each of these service methods return a List of uk.co.hashcode.itunes.Album instances.
The Album instance has the following fields:

    int rank
    String artist
    String artistLink
    String name
    String link
    String price
    String image
    String rights
    Date releaseDate


Find bugs or want enhancements?
Please come over to http://hashcode.co.uk:8081 and report any bugs or usability issues.
New feature requests are also always welcome!

Thanks and enjoy the plugin!
