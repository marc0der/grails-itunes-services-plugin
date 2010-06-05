package uk.co.hashcode.itunes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

class FeedsCommandTests {
	def feedsCommand
	
	def feedType = FeedType.NEW_RELEASES
	def country = Country.USA
	def genre = Genre.POP
	def limit = 10
	
	@Before
	void setUp() throws Exception {
		feedsCommand = new FeedsCommand(
			feedType:feedType,
			country:country,
			genre:genre,
			limit:limit
		)
	}

	@Test
	void testExecuteReturnsNotNull(){
		assert feedsCommand.execute() != null
	}
	
	@Test
	void testExecuteFeedTypeNewReleasesSuccess() {
		feedsCommand.feedType = FeedType.NEW_RELEASES
		def result = feedsCommand.execute()
		assert result != null
		assert result.contains(FeedType.NEW_RELEASES.woa)
		assert result.contains(FeedType.NEW_RELEASES.context)
		assert result.contains(FeedType.NEW_RELEASES.subContext)
		assert result.contains(FeedType.NEW_RELEASES.service)
        assert result.contains(FeedType.NEW_RELEASES.suffix)
	}
	
	@Test
	void testExecuteFeedTypeJustAddedSuccess() {
		feedsCommand.feedType = FeedType.JUST_ADDED
		def result = feedsCommand.execute()
		assert result != null
		assert result.contains(FeedType.JUST_ADDED.woa)
		assert result.contains(FeedType.JUST_ADDED.context)
		assert result.contains(FeedType.JUST_ADDED.subContext)
		assert result.contains(FeedType.JUST_ADDED.service)
		assert result.contains(FeedType.JUST_ADDED.suffix)
	}
	
	@Test
	void testExecuteFeedTypeFeaturedAlbumsSuccess() {
		feedsCommand.feedType = FeedType.FEATURED_ALBUMS
		def result = feedsCommand.execute()
		assert result != null
		assert result.contains(FeedType.FEATURED_ALBUMS.woa)
		assert result.contains(FeedType.FEATURED_ALBUMS.context)
		assert result.contains(FeedType.FEATURED_ALBUMS.subContext)
		assert result.contains(FeedType.FEATURED_ALBUMS.service)
		assert result.contains(FeedType.FEATURED_ALBUMS.suffix)
	}
	
	@Test
	void testExecuteFeedTypeTopAlbumsSuccess() {
		feedsCommand.feedType = FeedType.TOP_ALBUMS
		def result = feedsCommand.execute()
		assert result != null
		assert result.contains(FeedType.TOP_ALBUMS.woa)
		assert result.contains(FeedType.TOP_ALBUMS.context)
		assert result.contains(FeedType.TOP_ALBUMS.subContext)
		assert result.contains(FeedType.TOP_ALBUMS.service)
		assert result.contains(FeedType.TOP_ALBUMS.suffix)
	}
	
	@Test
	void testExecuteFeedTypeTopIMixesNotSupported() {
		feedsCommand.feedType = FeedType.TOP_IMIXES
		try {
			feedsCommand.execute()
			fail()
		} catch (UnsupportedOperationException uoe){
			assert uoe.message == "The FeedType ${FeedType.TOP_IMIXES.service} is not supported."
		}
	}
	
	@Test
	void testExecuteFeedTypeTopSongsNotSupported() {
		feedsCommand.feedType = FeedType.TOP_SONGS
		try {
			feedsCommand.execute()
			fail()
		} catch (UnsupportedOperationException uoe){
			assert uoe.message == "The FeedType ${FeedType.TOP_SONGS.service} is not supported."
		}
	}
	
	@Test
	void testExecuteFeedTypeNotSetDefaultsToNewReleases() {
		feedsCommand.feedType = null
		def result = feedsCommand.execute()
		assert result != null
		assert result.contains(FeedType.NEW_RELEASES.service)
		assert result.contains(FeedType.NEW_RELEASES.woa)
		assert result.contains(FeedType.NEW_RELEASES.context)
		assert result.contains(FeedType.NEW_RELEASES.subContext)
		assert result.contains(FeedType.NEW_RELEASES.suffix)
	}
	
	@Test
	void testExecuteLimitNegativeFloorTo1() {
		feedsCommand.limit = -1
		def result = feedsCommand.execute()
		assert result.contains('limit=1')
	}
	
	@Test
	void testExecuteLimit26Ceiling25() {
		feedsCommand.limit = 26
		def result = feedsCommand.execute()
		assert result.contains("limit=25")
	}
	
	@Test
	void testExecuteLimit1Success() {
		feedsCommand.limit = 1
		def result = feedsCommand.execute()
		assert result.contains("limit=${feedsCommand.limit}")
	}
	
	@Test
	void testExecuteLimit25Success() {
		feedsCommand.limit = 25
		def result = feedsCommand.execute()
		assert result.contains("limit=${feedsCommand.limit}")
	}
	
	@Test
	void testExecuteLimit10Success() {
		feedsCommand.limit = 10
		def result = feedsCommand.execute()
		assert result.contains("limit=${feedsCommand.limit}")
	}
	
	@Test
	void testExecuteNoLimitDefaultsTo10Success() {
		feedsCommand.limit = 0
		def result = feedsCommand.execute()
		assert result.contains("limit=10")
	}
	
	@Test
	void testExecuteCountrySetSuccess() {
		feedsCommand.country = Country.UK
		def result = feedsCommand.execute()
		assert result.contains("sf=${feedsCommand.country.id}")
	}
	
	@Test
	void testExecuteCountryNullDefaultsToUSA() {
		feedsCommand.country = null
		def result = feedsCommand.execute()
		assert result.contains("sf=${Country.USA.id}")
	}
	
	@Test
	void testExecuteGenreSetSuccess() {
		feedsCommand.genre = Genre.ROCK
		def result = feedsCommand.execute()
		assert result.contains("genre=${Genre.ROCK.id}")
	}
	
	@Test
	void testExecuteGenreAll() {
		feedsCommand.genre = Genre.ALL
		def result = feedsCommand.execute()
		assert !result.contains('genre=')
	}
	
	@Test
	void testExecuteGenreNullDefaultsToAll() {
		feedsCommand.genre = null
		def result = feedsCommand.execute()
		assert !result.contains('genre=')
	}
	
	@Test
	void testExecuteGeneratesWellFormedURL() {
		def result = feedsCommand.execute()
		def expected = "/WebObjects/${feedType.woa}/${feedType.context}/${feedType.subContext}/${feedType.service}/sf=${country.id}/limit=${limit}/genre=${genre.id}/${feedType.suffix}"
		assert result == expected
	}

}
