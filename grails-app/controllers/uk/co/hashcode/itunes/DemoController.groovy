package uk.co.hashcode.itunes

class DemoController {

    def itunesFeedsService

    def index = { 
        def command = new FeedsCommand()
        def newReleases = itunesFeedsService.getNewAlbumReleases(command)
        def justAddedAlbums = itunesFeedsService.getJustAddedAlbums(command)
        
        def results = [newReleases:newReleases, justAddedAlbums:justAddedAlbums]
        render(view:'show', model:[results:results])
    }

}
