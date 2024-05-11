package com.uanfcnccdam.lab04.services;

import com.uanfcnccdam.lab04.model.Enseignant;

public interface EnseignantServices {
    void AddEnseignant(Enseignant enseignant);

    Enseignant getEnseignantById(long enseignantId);

    void updateEnseignant(Enseignant enseignant);

    void deleteEnseignant(long enseignantId);
}
