package uk.co.hashcode.itunes.search

enum Language {
	ENGLISH('en_us'),
	JAPANESE('ja_jp')
	
	String name
	
	public Language(String name){
		this.name = name
	}

    String toString(){
        name
    }
}
