package uk.co.hashcode.itunes.search;

import org.junit.*

class ItunesSearchCommandTests {
	ItunesSearchCommand command
	
	@Before
	void setUp() throws Exception {
		command = new ItunesSearchCommand(term:'search')
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
    void testExecuteReturnsNotNull(){
        assert command.execute() != null
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
	void testExecuteThrowsExceptionNoSearchTerm() {
	    command = new ItunesSearchCommand()
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

    @Test
    void testEqualsTrue(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 == command2

    }

    @Test
    void testEqualsFalseTypeDiffers(){
        def command1 = new ItunesSearchCommand()
        def object = new Object()

        assert command != object
        
    }

    @Test
    void testEqualsFalseAllPropertiesDiffer(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.AUDIOBOOK,
                entity:Entity.ALL_TRACK,
                attribute:Attribute.ALL_TRACK_TERM,
                country:Country.JP,
                language:Language.JAPANESE,
                profile:SearchProfile.TRACKS_BY_ARTIST,
                limit:11
        )

        assert command1 != command2
    }

    @Test
    void testEqualsFalseMediaDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.AUDIOBOOK,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 != command2
    }

    @Test
    void testEqualsFalseEntityDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_TRACK,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 != command2
    }

    @Test
    void testEqualsFalseAttributeDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_TRACK_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 != command2

    }

    @Test
    void testEqualsFalseCountryDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.US,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 != command2
    }

    @Test
    void testEqualsFalseLanguageDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.JAPANESE,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 != command2
    }

    @Test
    void testEqualsFalseSearchProfileDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1 != command2
    }

    @Test
    void testEqualsFalseLimitDiffers(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:11
        )

        assert command1 != command2
    }

    @Test
    void testHashCodeSameProperties(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        assert command1.hashCode() == command2.hashCode()
    }

    @Test
    void testHashCodeDifferentProperties(){
        def command1 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:10
        )

        def command2 = new ItunesSearchCommand(
                media:Media.ALL,
                entity:Entity.ALL_ARTIST,
                attribute:Attribute.ALL_ARTIST_TERM,
                country:Country.GB,
                language:Language.ENGLISH,
                profile:SearchProfile.ALBUMS_BY_ARTIST,
                limit:11
        )

        assert command1.hashCode() != command2.hashCode()
    }

}
