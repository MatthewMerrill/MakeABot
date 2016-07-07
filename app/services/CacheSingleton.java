package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.cache.CacheApi;
import play.cache.NamedCache;

@Singleton
public class CacheSingleton {

    private final CacheApi cache;

    @Inject
    public CacheSingleton(@NamedCache("session-cache") CacheApi cache) {
        this.cache = cache;
    }

    public CacheApi getCache() {
        return cache;
    }

}
