package uk.co.hashcode.itunes

class DemoController {

    def feedsService

    def index = { 
        def command = new FeedsCommand()
        def newReleases = feedsService.getNewAlbumReleases(command)
        render(view:'show', model:[newreleases:newReleases])
    }

}
