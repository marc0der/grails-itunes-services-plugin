package uk.co.hashcode.itunes.search

class SearchDemoController {

    def itunesSearchService

    def index = {
        redirect(action:search)
    }

    def search = {
        def searchTerm = params.search ?: 'Led Zeppelin'
        def searchResults = itunesSearchService.searchAlbumsByArtist(searchTerm)
        render(view:'search', model:[searchTerm:searchTerm, searchResults:searchResults])
    }
}
