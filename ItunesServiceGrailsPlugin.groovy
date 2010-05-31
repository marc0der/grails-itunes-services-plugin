class ItunesServiceGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/",
            "grails-app/controllers/"
    ]

    def author = "Marco Vermeulen"
    def authorEmail = "marco_vermeulen@yahoo.co.uk"
    def title = "iTunes Service Plugin"
    def description = '''
This plugin allows Grails applications to integrate with the iTunes Web Services and RSS Feeds.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/itunes-service"

    def doWithWebDescriptor = { xml ->

    }

    def doWithSpring = {
	    feedInfoCache(com.sun.syndication.fetcher.impl.HashMapFeedInfoCache){ bean ->
		    bean.factoryMethod = 'getInstance'
	    }
	    println 'FeedInfoCache configured...'
	    feedFetcher(com.sun.syndication.fetcher.impl.HttpURLFeedFetcher){
		    feedInfoCache = ref('feedInfoCache')
	    }
	    println 'FeedFetcher configured...'
        feedsService(uk.co.hashcode.itunes.FeedsService){
	    	domain(String, 'http://ax.itunes.apple.com')
            feedFetcher = ref('feedFetcher')
        }
	    println 'FeedsService configured...'
    }

    def doWithDynamicMethods = { ctx ->

    }

    def doWithApplicationContext = { applicationContext ->

    }

    def onChange = { event ->

    }

    def onConfigChange = { event ->

    }
}
