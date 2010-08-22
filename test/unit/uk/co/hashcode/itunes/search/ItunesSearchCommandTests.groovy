package uk.co.hashcode.itunes.search;

import org.junit.*

class ItunesSearchCommandTests {
	ItunesSearchCommand command
	
	@Before
	void setUp() throws Exception {
		command = new ItunesSearchCommand('search')
	}

    @Test
    void testSensibleDefaults(){
        assert command.media == Media.MUSIC
        assert command.entity == Entity.ALBUM
        assert command.attribute == Attribute.ARTIST_TERM
        assert command.language == Language.ENGLISH
        assert command.country == Country.US
        assert command.limit == 20
        
        assert command.profile == null
    }

    @Test
    void testExecuteWithSensibleDefaults(){
        def searchString = command.execute()

        assert searchString.contains('media=music')
        assert searchString.contains('entity=album')
        assert searchString.contains('attribute=artistTerm')
    }

    @Test
    void testExecuteProfileOverridesSensibleDefaults(){
        command.profile = SearchProfile.MOVIES_BY_ACTOR
        def searchString = command.execute()

        assert !searchString.contains('media=music')
        assert !searchString.contains('entity=album')
        assert !searchString.contains('attribute=artistTerm')

        assert searchString.contains('media=movie')
        assert searchString.contains('entity=movie')
        assert searchString.contains('attribute=actorTerm')
    }

    @Test
    void testExecuteSensibleDefaultsForSundryValuesPresent(){
        def searchString = command.execute()

        assert searchString.contains('language=en_us')
        assert searchString.contains('country=US')
        assert searchString.contains('limit=20')
    }

    @Test
    void testExecuteExactMatchSensibleDefaults(){
        def searchString = command.execute()
        assert searchString == 'media=music&entity=album&attribute=artistTerm&language=en_us&country=US&limit=20&term=search'
    }

    @Test
    void testExecuteExactMatchProfileOverride(){
        command.profile = SearchProfile.MOVIES_BY_ACTOR
        def searchString = command.execute()
        assert searchString == 'media=movie&entity=movie&attribute=actorTerm&language=en_us&country=US&limit=20&term=search'
    }

	@Test
	void testExecuteReturnsNotNull(){
		assert command.execute() != null
	}

    @Test
	void testExecuteThrowsExceptionNoSearchTerm() {
	    command = new ItunesSearchCommand(null)
	    try {
	        command.execute()
	        fail()
	    } catch (Exception e){
	        assert e instanceof IllegalArgumentException
	    }
	}

    @Test
    void testExecuteSearchTermFound() {
        def searchStr = command.execute()
        assert searchStr.contains('term=search')
    }

    @Test
    void testExecuteLimitDefault20(){
        def searchStr = command.execute()
        assert searchStr.contains('limit=20')
    }

    @Test
    void testExecuteLimitFloorLessThan1(){
        command.limit = 0
        def searchStr = command.execute()
        assert searchStr.contains('limit=1')
    }

    @Test
    void testExecuteLimitCeiling200(){
        command.limit = 200
        def searchStr = command.execute()
        assert searchStr.contains('limit=200')
    }

    @Test
    void testExecuteLimitCeiling200Exceeded(){
        command.limit = 201
        def searchStr = command.execute()
        assert searchStr.contains('limit=200')
    }

    @Test
    void testExecuteSearchTermUrlEncoding(){
        command.term = 'Led Zeppelin'
        def searchStr = command.execute()
        assert searchStr.contains('term=Led+Zeppelin')
    }
}
