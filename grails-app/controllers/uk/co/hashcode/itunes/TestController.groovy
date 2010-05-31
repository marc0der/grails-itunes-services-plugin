package uk.co.hashcode.itunes

class TestController {

    def feedsService

    def index = { 
        def command = new FeedsCommand()
        def newReleases = feedsService.getNewAlbumReleases(command)
        render(view:'releases', model:[newreleases:newReleases])
    }

}
