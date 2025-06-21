package PFE.PFE_SI.Service;

import PFE.PFE_SI.Model.Formulaire;
import PFE.PFE_SI.Repository.FormulaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaireService {

    @Autowired
    private FormulaireRepository formulaireRepository;

    public List<Formulaire> getAllFormulaires() {
        return formulaireRepository.findAll();
    }

    public Formulaire getFormulaireById(String id) {
        return formulaireRepository.findById(id).orElse(null);
    }

    public Formulaire createFormulaire(Formulaire formulaire) {
        return formulaireRepository.save(formulaire);
    }

    public void deleteFormulaire(String id) {
        formulaireRepository.deleteById(id);
    }

    public Formulaire updateFormulaire(String id, Formulaire formulaire) {
        formulaire.setNumero(id);
        return formulaireRepository.save(formulaire);
    }
}
