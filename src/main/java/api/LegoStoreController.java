package api;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import persistance.LegoSetRepository;
import rc.legostore.model.LegoSet;

import java.util.Collection;

@RestController
@RequestMapping("/legostore/api")
public class LegoStoreController {
    private MongoTemplate mongoTemplate;
    private LegoSetRepository legoSetRepository;

    public LegoStoreController(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet)
    {
        this.mongoTemplate.insert(legoSet);

    }
    @GetMapping("/all")
    public Collection<LegoSet> all()
    {
        Collection<LegoSet> legoset=this.legoSetRepository.findAll();

        return legoset;

    }
    @PutMapping
    public void update(@RequestBody LegoSet legoSet)
    {
        this.legoSetRepository.save(legoSet);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id)
    {
        this.legoSetRepository.deleteById(id);
    }
}
