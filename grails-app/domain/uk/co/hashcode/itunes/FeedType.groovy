package uk.co.hashcode.itunes

enum FeedType {
	NEW_RELEASES('MZStore.woa', 'wpa', 'MRSS', 'newreleases'),
	JUST_ADDED('MZStore.woa', 'wpa', 'MRSS', 'justadded'),
	FEATURED_ALBUMS('MZStore.woa', 'wpa', 'MRSS', 'featuredalbums'),
	TOP_ALBUMS('MZStoreServices.woa', 'ws', 'RSS', 'topalbums'),
	TOP_IMIXES('MZStoreServices.woa', 'ws', 'RSS', 'topimixes'),
	TOP_SONGS('MZStoreServices.woa', 'ws', 'RSS', 'topsongs')
	
	String woa
	String context
	String subContext
	String service
	
	public FeedType(String woa, String context, String subContext, String service){
		this.woa = woa
		this.context = context
		this.subContext = subContext
		this.service = service
	}
	
}
