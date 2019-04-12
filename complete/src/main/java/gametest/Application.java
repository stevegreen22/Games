package gametest;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.tools.cloudstorage.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;
@SpringBootApplication
@EnableCaching
//@ComponentScan({"gametest.repo","gametest.repo.TestRepo"})
public class Application {
//
//    public static void saveToStorage() throws IOException {
//        GcsService gcsService =
//                GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
//
//        GcsFileOptions instance = GcsFileOptions.getDefaultInstance();
//        GcsFilename fileName = "";//getFileName(req);
//        GcsOutputChannel outputChannel;
//        outputChannel = gcsService.createOrReplace(fileName, instance);
//        copy(req.getInputStream(), Channels.newOutputStream(outputChannel));
//    }
//
//    public static void getImageFromStorage(){
//        GcsFilename gcsFilename = new GcsFilename("bucketName", "objectName");
//        ImagesService is = ImagesServiceFactory.getImagesService();
//        String filename = String.format("/gs/%s/%s", gcsFilename.getBucketName(), gcsFilename.getObjectName());
//        String servingUrl = is.getServingUrl(ServingUrlOptions.Builder.withGoogleStorageFileName(filename));
//    }

    public static void test() throws IOException {
        GcsService gcsService = GcsServiceFactory.createGcsService();
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        GcsFileOptions options = new GcsFileOptions.Builder()
                .mimeType(".jpg")
                .acl("project-private")
                .build();

        String bucketName = "fcg_game_images";
        String secureFilename = "https://helpx.adobe.com/uk/stock/how-to/visual-reverse-image-search/_jcr_content/main-pars/image.img.jpg/visual-reverse-image-search-v2_1000x560.jpg";

        GcsFilename p_filename = new GcsFilename(bucketName, secureFilename);
        GcsOutputChannel writeChannel = gcsService.createOrReplace(p_filename, options); //exception here
        //writeChannel.write(ByteBuffer.wrap(image.getImageData()));
        writeChannel.close();

        BlobKey blobKey = blobstoreService.createGsBlobKey("/gs/" + p_filename.getBucketName() + "/" + p_filename.getObjectName());

        ImagesService imageService = ImagesServiceFactory.getImagesService();
        ServingUrlOptions servoptions = ServingUrlOptions.Builder.withBlobKey(blobKey).secureUrl(true);
        String url = imageService.getServingUrl(servoptions);
    }

    public static void main(String[] args) {

        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainy(String[] args) {
        SpringApplication.run(Application.class, args);
    }







    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx ) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


        };
    }


}
