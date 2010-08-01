package uk.co.hashcode.itunes.feeds

class DemoController {

    def itunesFeedsService

    def index = { 
        //new releases
    	def newReleaseCommand = new FeedsCommand(
        		genre:Genre.POP,
        		limit:10,
        		country:Country.UK
        )
        def newReleases = itunesFeedsService.getNewAlbumReleases(newReleaseCommand)
        
        //just added
        def justAddedCommand = new FeedsCommand(
        		genre:Genre.ROCK,
        		limit:5,
        		country:Country.JAPAN
        )
        def justAddedAlbums = itunesFeedsService.getJustAddedAlbums(justAddedCommand)
        
        //featured albums
        def featuedCmd = new FeedsCommand(
        		genre:Genre.ALL,
        		limit:4,
        		country:Country.GERMANY
        )
        def featuredAlbums = itunesFeedsService.getFeaturedAlbums(featuedCmd)
        
        //top albums
        def topCmd = new FeedsCommand() //a default command: Genre.ALL, limit=10, Country.USA
        def topAlbums = itunesFeedsService.getTopAlbums(topCmd)
        
        def results = [
		    newReleases:newReleases, 
		    justAddedAlbums:justAddedAlbums, 
		    featuredAlbums:featuredAlbums,
		    topAlbums:topAlbums
        ]
        
        render(view:'show', model:[results:results])
    }

}
