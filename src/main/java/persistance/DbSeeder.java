package persistance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import rc.legostore.model.DeliveryInfo;
import rc.legostore.model.LegoSet;
import rc.legostore.model.LegoSetDifficulty;
import rc.legostore.model.ProductReview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
@Service
public class DbSeeder implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    public DbSeeder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void run(String... args){
        /*
        Lego Sets
         */
        this.mongoTemplate.dropCollection(LegoSet.class);

        LegoSet milleniumFalcon = new LegoSet("MilleniumFalcon", LegoSetDifficulty.HARD,"star wars",
                Arrays.asList(new ProductReview("Johan",7),
                        new ProductReview("hans",2)
                )
                ,new DeliveryInfo(LocalDate.now().plusDays(1),30,true));
        LegoSet legoman = new LegoSet("legoSet", LegoSetDifficulty.EASY,"Original",
                Arrays.asList(new ProductReview("Otto",2),
                        new ProductReview("Tenma",5)
                )
                ,new DeliveryInfo(LocalDate.now().plusDays(4),2,false));
        LegoSet GTO = new LegoSet("gto", LegoSetDifficulty.HARD,"Cartoon",
                Arrays.asList(new ProductReview("Yunkers",7),
                        new ProductReview("ROb",2)
                )
                ,new DeliveryInfo(LocalDate.now().plusDays(4),2,true));

        Collection<LegoSet> InitialProducts = Arrays.asList(milleniumFalcon,legoman,GTO);
        this.mongoTemplate.insertAll(InitialProducts);
    }
}
