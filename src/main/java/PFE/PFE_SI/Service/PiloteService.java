package PFE.PFE_SI.Service;

import PFE.PFE_SI.Model.Pilote;
import PFE.PFE_SI.Repository.PiloteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiloteService {

    @Autowired
    private PiloteRepository piloteRepository;

    public List<Pilote> getAllPilotes() {
        return piloteRepository.findAll();
    }

    public Pilote getPiloteById(String id) {
        return piloteRepository.findById(id).orElse(null);
    }
    public List<Pilote> getPiloteByNom(String nom) {
        return piloteRepository.findByNom(nom);
    }

    public Pilote createPilote(Pilote pilote) {
        return piloteRepository.save(pilote);
    }

    public void deletePilote(String id) {
        piloteRepository.deleteById(id);
    }

    public Pilote updatePilote(String id, Pilote pilote) {
        pilote.setId(id);
        return piloteRepository.save(pilote);
    }
}
