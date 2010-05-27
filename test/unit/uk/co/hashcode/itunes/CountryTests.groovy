package uk.co.hashcode.itunes

import grails.test.*

class CountryTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEnumIds() {
    	assert Country.AUSTRALIA.id == 143460
    	assert Country.AUSTRIA.id == 143445
    	assert Country.BELGIUM.id == 143446
    	assert Country.CANADA.id == 143455
    	assert Country.DENMARK.id == 143458
    	assert Country.FINLAND.id == 143447
    	assert Country.FRANCE.id == 143442
    	assert Country.GERMANY.id == 143443
    	assert Country.GREECE.id == 143448
    	assert Country.IRELAND.id == 143449
    	assert Country.ITALY.id == 143450
    	assert Country.JAPAN.id == 143462
    	assert Country.LUXEMBOURG.id == 143451
    	assert Country.MEXICO.id == 143468
    	assert Country.NETHERLANDS.id == 143452
    	assert Country.NEW_ZEALAND.id == 143461
    	assert Country.NORWAY.id == 143457
    	assert Country.PORTUGAL.id == 143453
    	assert Country.SPAIN.id == 143454
    	assert Country.SWEDEN.id == 143456
    	assert Country.SWITZERLAND.id == 143459
    	assert Country.UK.id == 143444
    	assert Country.USA.id == 143441
    }
    
}
