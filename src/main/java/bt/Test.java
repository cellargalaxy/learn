package bt;

import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;
import com.google.inject.Module;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by cellargalaxy on 18-5-5.
 */
public class Test {
    public static void main(String[] args) {
        // enable multithreaded verification of torrent data
        Config config = new Config() {
            @Override
            public int getNumOfHashingThreads() {
                return Runtime.getRuntime().availableProcessors() * 2;
            }
        };

        // enable bootstrapping from public routers
        Module dhtModule = new DHTModule(new DHTConfig() {
            @Override
            public boolean shouldUseRouterBootstrap() {
                return true;
            }
        });

        // get download directory
        Path targetDirectory = new File("/home/cellargalaxy").toPath();

        // create file system based backend for torrent data
        Storage storage = new FileSystemStorage(targetDirectory);

        // create client with a private runtime
        BtClient client = Bt.client()
                .config(config)
                .storage(storage)
                .magnet("magnet:?xt=urn:btih:644242f666ea85d8c84ae4edc0bda5de73d2fdf7")
                .autoLoadModules()
                .module(dhtModule)
                .stopWhenDownloaded()
                .build();

        // launch
        client.startAsync().join();
    }
}
