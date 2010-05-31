beans = {
    feedsCommand(uk.co.hashcode.itunes.FeedsCommand){ b ->
    	b.scope = 'prototype'
        urlBase = 'http://ax.itunes.apple.com'
    }
}
