package PFE.PFE_SI.Model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
@Document(collection = "formulaires")
@Data
public class Formulaire {

    @Id
    private String numero;

    private String titre;

    private String causesProbables;

    private String constat;

    private String processusConcerné;

    private String source;

    private String action;

    private LocalDate deadline;

    private String statut;


    private LocalDate dateEvaluation;

    private String evaluation;

    private String type;

    private String utilisateurId;


}
