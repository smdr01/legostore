package rc.legostore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
@Document(collection ="legosets") // Defines this entire class as a document
public class LegoSet {
    @Id
    private String id;
    private String name;
    private LegoSetDifficulty difficulty;
    @Indexed(direction = IndexDirection.ASCENDING) // This is put onto a field which may be used on if this field is used alot
    private String theme;
    @JsonIgnoreProperties
    private Collection<ProductReview> reviews = new ArrayList<>();
    @Field("delivery") // Lets spring know that this will be a field with this name
    private DeliveryInfo deliveryInfo;

     // This tells spring and mongo that this contructor is going to be used if there are many constructors.
    public LegoSet(String name, LegoSetDifficulty difficulty, String theme, Collection<ProductReview> reviews, DeliveryInfo deliveryInfo) {
        this.name = name;
        this.difficulty = difficulty;
        this.theme = theme;
        this.deliveryInfo = deliveryInfo;
        if(reviews!=null)
        {
            this.reviews=reviews;
        }
    }
    @Transient //Tells spring we are not interested in this field
    private int nbParts;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LegoSetDifficulty getDifficulty() {
        return difficulty;
    }

    public String getTheme() {
        return theme;
    }

    public Collection<ProductReview> getReviews() {
        return Collections.unmodifiableCollection(this.reviews);    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }
}
