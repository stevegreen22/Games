package gametest.gcp_storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Tuple;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import gametest.fileManipulation.FileDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by SteveGreen on 14/04/2019.
 */
public class StorageUpload {

    public StorageUpload(){}

    // extracted from the GCP examples and reqorked to fit my own purposes.

    /**
     * This class demonstrates how to create a new Blob or to update its content.
     *
     * @see <a href="https://cloud.google.com/storage/docs/json_api/v1/objects/insert">Objects:
     *     insert</a>
     */


    /*todo:
        add logging
        remove cred location
        refactor for use with spring.
        add list items and get item
    */

    //private static String cred = "/Users/SteveGreen/Downloads/gameapp/Games/complete/src/googleKey.json";
    private static String cred = "C:\\Users\\Steve\\Downloads\\googleKey.json";

    private static String bucketName = "my-new-bucket-which-will-hopefully-fucking-work";


    public void doPostToStorage(String urlFileName, String saveFileName) {
        try {
            //create new url, use library to save to temp storage
            URL x = new URL(urlFileName);
            //todo: either use the id of the game, strip the spaces and tolower or something simialr.
            String result = FileDownloader.downloadFromUrl(x, saveFileName+".png");
            File file = new File(result);

            // create the GCP storage options, passing in the cred from the file. and setting proj id.
            StorageOptions options = null;
            options = StorageOptions.newBuilder()
                    .setProjectId("gameapp-237013")
                    .setCredentials(GoogleCredentials.fromStream(
                            new FileInputStream(cred))).build();


            // get the storage service from the options above (could likely do this in one.
            Storage storage = options.getService();

            // take raw values and check params / parse them
            String[] bleuh = {file.getPath(), bucketName};
            Tuple<Path, BlobInfo> parsedData = parse(bleuh);

            // d the task passing in tuple values and storage.
            run(storage, parsedData.x(), parsedData.y());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //file.delete();
        }
    }

    private Tuple<Path, BlobInfo> parse(String... args) throws IOException {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException();
        }
        Path path = Paths.get(args[0]);
        String contentType = Files.probeContentType(path);
        String blob = args.length < 3 ? path.getFileName().toString() : args[2];
        return Tuple.of(path, BlobInfo.newBuilder(args[1], blob).setContentType(contentType).build());
    }

    private void run(Storage storage, Path uploadFrom, BlobInfo blobInfo) throws IOException {
            if (Files.size(uploadFrom) > 1_000_000) {
                // When content is not available or large (1MB or more) it is recommended
                // to write it in chunks via the blob's channel writer.
                try (WriteChannel writer = storage.writer(blobInfo)) {
                    byte[] buffer = new byte[1024];
                    try (InputStream input = Files.newInputStream(uploadFrom)) {
                        int limit;
                        while ((limit = input.read(buffer)) >= 0) {
                            try {
                                writer.write(ByteBuffer.wrap(buffer, 0, limit));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                byte[] bytes = Files.readAllBytes(uploadFrom);
                // create the blob in one request.
                storage.create(blobInfo, bytes);
            }
            System.out.println("Blob was created");
        }


    }

