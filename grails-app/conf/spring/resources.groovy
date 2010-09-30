import org.springframework.cache.ehcache.EhCacheFactoryBean

beans = {


    //Feed Service related caches

    newAlbumReleasesCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    justAddedAlbumsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    featuredAlbumsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    topAlbumsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    topMixesCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    topSongsCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }


    //Search Service related caches

    albumsByNameCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    albumsByArtistCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    artistsByNameCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    tracksByNameCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    tracksByArtistCache(EhCacheFactoryBean) { bean ->
        cacheName = 'newAlbumReleasesCache'
        maxElementsInMemory = 2000
        eternal = false
        diskPersistent = false
        memoryStoreEvictionPolicy = 'LRU'
        timeToIdle = 3600
        timeToLive = 86400
    }

    
}
