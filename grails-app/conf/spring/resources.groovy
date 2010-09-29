import org.springframework.cache.ehcache.EhCacheFactoryBean

beans = {

    println 'In the beans block...'

    //Feed Service related caches

    newAlbumReleasesCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    justAddedAlbumsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    featuredAlbumsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    topAlbumsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    topMixesCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    topSongsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }


    //Search Service related caches

    albumsByNameCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    albumsByArtistCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    artistsByNameCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    tracksByNameCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    tracksByArtistCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 3600
    }

    
}
