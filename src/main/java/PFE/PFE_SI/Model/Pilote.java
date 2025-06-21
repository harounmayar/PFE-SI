package PFE.PFE_SI.Model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "pilotes")
@Data
public class Pilote {
    @Id
    private String id;

    private String nom;

    private String prenom;

    private String département;

    private String utilisateurId;


}
