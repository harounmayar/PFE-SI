package PFE.PFE_SI.Controller;

import PFE.PFE_SI.Model.Pilote;
import PFE.PFE_SI.Service.PiloteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilotes")
public class PiloteController {

    @Autowired
    private PiloteService piloteService;

    @GetMapping
    public List<Pilote> getAll() {
        return piloteService.getAllPilotes();
    }

    @GetMapping("/{id}")
    public Pilote getById(@PathVariable String id) {
        return piloteService.getPiloteById(id);
    }
    @GetMapping("/nom/{nom}")
    public List<Pilote> getByNom(@PathVariable String nom) {
        return piloteService.getPiloteByNom(nom);
    }


    @PostMapping
    public Pilote create(@RequestBody Pilote pilote) {
        return piloteService.createPilote(pilote);
    }

    @PutMapping("/{id}")
    public Pilote update(@PathVariable String id, @RequestBody Pilote pilote) {
        return piloteService.updatePilote(id, pilote);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        piloteService.deletePilote(id);
    }
}
