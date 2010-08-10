package uk.co.hashcode.itunes.search

import grails.test.*

class SearchProfileTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testSearchProfileAlbum() {
		assert SearchProfile.ALBUM.name == 'Album'
		assert SearchProfile.ALBUM.media == Media.MUSIC
		assert SearchProfile.ALBUM.entity == Entity.ALBUM
		assert SearchProfile.ALBUM.attribute == Attribute.ALBUM_TERM
	}
	
	void testSearchProfileMusicArtist() {
		assert SearchProfile.MUSIC_ARTIST.name == 'Artist'
		assert SearchProfile.MUSIC_ARTIST.media == Media.MUSIC
		assert SearchProfile.MUSIC_ARTIST.entity == Entity.MUSIC_ARTIST
		assert SearchProfile.MUSIC_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileMusicTrack() {
	    assert SearchProfile.MUSIC_TRACK.name == 'Track'
	    assert SearchProfile.MUSIC_TRACK.media == Media.MUSIC
	    assert SearchProfile.MUSIC_TRACK.entity == Entity.MUSIC_TRACK
	    assert SearchProfile.MUSIC_TRACK.attribute == Attribute.MUSIC_TRACK_TERM
	}
	
	void testSearchProfileAlbumsByArtist() {
	    assert SearchProfile.ALBUMS_BY_ARTIST.name == 'Albums by Artist'
	    assert SearchProfile.ALBUMS_BY_ARTIST.media == Media.MUSIC
	    assert SearchProfile.ALBUMS_BY_ARTIST.entity == Entity.ALBUM
	    assert SearchProfile.ALBUMS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileMusicVideosByArtist() {
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.name == 'Music Videos by Artist'
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.media == Media.MUSIC
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.entity == Entity.MUSIC_VIDEO
	    assert SearchProfile.MUSIC_VIDEOS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
	
	void testSearchProfileTracksByArtist() {
	    assert SearchProfile.TRACKS_BY_ARTIST.name == 'Tracks by Artist'
	    assert SearchProfile.TRACKS_BY_ARTIST.media == Media.MUSIC
	    assert SearchProfile.TRACKS_BY_ARTIST.entity == Entity.MUSIC_TRACK
	    assert SearchProfile.TRACKS_BY_ARTIST.attribute == Attribute.ARTIST_TERM
	}
}
