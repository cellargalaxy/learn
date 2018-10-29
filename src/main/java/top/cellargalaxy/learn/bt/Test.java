package top.cellargalaxy.learn.bt;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;
import bt.tracker.http.HttpTrackerModule;
import com.google.inject.Module;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cellargalaxy on 18-5-5.
 */
public class Test {
	public static void main(String[] args) throws MalformedURLException {
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
		Path targetDirectory = new File("/home/cellargalaxy/download").toPath();

		// create file system based backend for torrent data
		Storage storage = new FileSystemStorage(targetDirectory);

		HttpTrackerModule httpTrackerModule = new HttpTrackerModule();

		// create client with a private runtime
		BtClient client = Bt.client()
				.config(config)
				.storage(storage)
				.magnet("magnet:?xt=urn:btih:148af29f55b8de640994e19bb6f4ad338b5dbf2d&tr=http%3A%2F%2F104.238.198.186%3A8000%2Fannounce&tr=udp%3A%2F%2F104.238.198.186%3A8000%2Fannounce&tr=http%3A%2F%2Ftracker.openbittorrent.com%3A80%2Fannounce&tr=udp%3A%2F%2Ftracker3.itzmx.com%3A6961%2Fannounce&tr=http%3A%2F%2Ftracker4.itzmx.com%3A2710%2Fannounce&tr=http%3A%2F%2Ftracker.publicbt.com%3A80%2Fannounce&tr=http%3A%2F%2Ftracker.prq.to%2Fannounce&tr=http%3A%2F%2Fopen.acgtracker.com%3A1096%2Fannounce&tr=https%3A%2F%2Ft-115.rhcloud.com%2Fonly_for_ylbud&tr=http%3A%2F%2Fbtfile.sdo.com%3A6961%2Fannounce&tr=http%3A%2F%2Fexodus.desync.com%3A6969%2Fannounce&tr=http%3A%2F%2Ftr.bangumi.moe%3A6969%2Fannounce&tr=http%3A%2F%2Ft.nyaatracker.com%2Fannounce&tr=http%3A%2F%2Fopen.nyaatorrents.info%3A6544%2Fannounce&tr=http%3A%2F%2Ft2.popgo.org%3A7456%2Fannonce&tr=http%3A%2F%2Fshare.camoe.cn%3A8080%2Fannounce&tr=http%3A%2F%2Fopentracker.acgnx.se%2Fannounce")
				.autoLoadModules()
//				.torrent(Paths.get("/home", "cellargalaxy", "download", "gesha.torrent").toFile().toURI().toURL())
				.module(dhtModule)
				.module(httpTrackerModule)
				.afterTorrentFetched(torrent -> System.err.println("magnet:?xt=urn:btih:" + torrent.getTorrentId()))
				.build();

		client.startAsync(torrentSessionState -> {
			System.out.println();
			System.out.println("getUploaded: " + torrentSessionState.getUploaded());
			System.out.println("getDownloaded: " + torrentSessionState.getDownloaded());
			System.out.println("getPiecesTotal: " + torrentSessionState.getPiecesTotal());
			System.out.println("getPiecesSkipped: " + torrentSessionState.getPiecesSkipped());
			System.out.println("getConnectedPeers: " + torrentSessionState.getConnectedPeers());
			System.out.println("getPiecesComplete: " + torrentSessionState.getPiecesComplete());
			System.out.println("getPiecesRemaining: " + torrentSessionState.getPiecesRemaining());
			System.out.println("getPiecesIncomplete: " + torrentSessionState.getPiecesIncomplete());
			System.out.println("getPiecesNotSkipped: " + torrentSessionState.getPiecesNotSkipped());
			System.out.println();
		}, 1000 * 5);


		// launch
		client.startAsync().join();
	}
}
