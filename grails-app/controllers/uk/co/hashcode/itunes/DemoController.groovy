package uk.co.hashcode.itunes

class DemoController {

    def itunesFeedsService

    def index = { 
        def command = new FeedsCommand()
        def newReleases = itunesFeedsService.getNewAlbumReleases(command)
        def justAddedAlbums = itunesFeedsService.getJustAddedAlbums(command)
        def featuredAlbums = itunesFeedsService.getFeaturedAlbums(command)
        def topAlbums = itunesFeedsService.getTopAlbums(command)
        
        def results = [
		    newReleases:newReleases, 
		    justAddedAlbums:justAddedAlbums, 
		    featuredAlbums:featuredAlbums,
		    topAlbums:topAlbums
        ]
        render(view:'show', model:[results:results])
    }

}
