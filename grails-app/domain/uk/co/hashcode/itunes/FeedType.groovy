package uk.co.hashcode.itunes

enum FeedType {
	NEW_RELEASES('MZStore.woa', 'newreleases'),
	JUST_ADDED('MZStore.woa', 'justadded'),
	FEATURED_ALBUMS('MZStore.woa', 'featuredalbums'),
	TOP_ALBUMS('MZStoreServices.woa', 'topalbums'),
	TOP_IMIXES('MZStoreServices.woa', 'topimixes'),
	TOP_SONGS('MZStoreServices.woa', 'topsongs')
	
	String woa
	String service
	
	public FeedType(String woa, String service){
		this.woa = woa
		this.service = service
	}
	
}
