package uk.co.hashcode.itunes

class DemoController {

    def itunesFeedsService

    def index = { 
        def command = new FeedsCommand()
        def newReleases = itunesFeedsService.getNewAlbumReleases(command)
        render(view:'show', model:[newreleases:newReleases])
    }

}
