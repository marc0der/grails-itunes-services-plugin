package uk.co.hashcode.itunes

enum FeedType {
	NEW_RELEASES('MZStore.woa', 'wpa', 'MRSS', 'newreleases', 'rss.xml'),
	JUST_ADDED('MZStore.woa', 'wpa', 'MRSS', 'justadded', 'rss.xml'),
	FEATURED_ALBUMS('MZStore.woa', 'wpa', 'MRSS', 'featuredalbums', 'rss.xml'),
	TOP_ALBUMS('MZStoreServices.woa', 'ws', 'RSS', 'topalbums', 'xml'),
	TOP_IMIXES('MZStoreServices.woa', 'ws', 'RSS', 'topimixes', 'xml'),
	TOP_SONGS('MZStoreServices.woa', 'ws', 'RSS', 'topsongs', 'xml')
	
	String woa
	String context
	String subContext
	String service
    String suffix
	
	public FeedType(String woa, String context, String subContext, String service, String suffix){
		this.woa = woa
		this.context = context
		this.subContext = subContext
		this.service = service
        this.suffix = suffix
	}
	
}
