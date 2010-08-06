package uk.co.hashcode.itunes.search

import grails.test.*

class CountryTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCountryName() {
		assert Country.GB.name == 'GB'
		assert Country.JP.name == 'JP'
		assert Country.US.name == 'US'
    }
}
