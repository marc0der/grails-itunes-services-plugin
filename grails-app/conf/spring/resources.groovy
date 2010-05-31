beans = {
	feedInfoCache(com.sun.syndication.fetcher.impl.HashMapFeedInfoCache){ bean ->
		bean.factoryMethod = 'getInstance'
	}
	feedFetcher(com.sun.syndication.fetcher.impl.HttpURLFeedFetcher){
		feedInfoCache = ref('feedInfoCache')
	}
    feedsService(uk.co.hashcode.itunes.FeedsService){
        domain = 'http://ax.itunes.apple.com'
        feedFetcher = ref('feedFetcher')
    }
}
