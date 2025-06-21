package PFE.PFE_SI.Repository;
import PFE.PFE_SI.Model.Pilote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PiloteRepository extends MongoRepository<Pilote, String> {
    List<Pilote> findByNom(String nom);
}

