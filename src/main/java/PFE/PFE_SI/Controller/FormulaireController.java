package PFE.PFE_SI.Controller;

import PFE.PFE_SI.Model.Formulaire;
import PFE.PFE_SI.Service.FormulaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formulaires")
public class FormulaireController {

    @Autowired
    private FormulaireService formulaireService;

    @GetMapping
    public List<Formulaire> getAll() {
        return formulaireService.getAllFormulaires();
    }

    @GetMapping("/{id}")
    public Formulaire getById(@PathVariable String id) {
        return formulaireService.getFormulaireById(id);
    }

    @PostMapping
    public Formulaire create(@RequestBody Formulaire formulaire) {
        return formulaireService.createFormulaire(formulaire);
    }

    @PutMapping("/{id}")
    public Formulaire update(@PathVariable String id, @RequestBody Formulaire formulaire) {
        return formulaireService.updateFormulaire(id, formulaire);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        formulaireService.deleteFormulaire(id);
    }
}
