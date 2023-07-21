package com.example.demo.demo.Quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.demo.Quiz.model.Inscription;
import com.example.demo.demo.Quiz.repository.InsRepository;

@Service
public class InsServiceImpl implements InscriptionService {

    private final InsRepository insRepository;

    @Autowired
    public InsServiceImpl(InsRepository insRepository) {
        this.insRepository = insRepository;
    }

    public Inscription creer(Inscription inscription) {
        return insRepository.save(inscription);
    }

    public Inscription modifier(Integer id, Inscription inscription) {
        return insRepository.findById(id).map(existingInscription -> {
            existingInscription.setNom(inscription.getNom());
            existingInscription.setPrenom(inscription.getPrenom());
            existingInscription.setPassword(inscription.getPassword());
            return insRepository.save(existingInscription);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public String supprimer(Integer id) {
        insRepository.deleteById(id);
        return "Utilisateur supprimé avec succès";
    }
}
