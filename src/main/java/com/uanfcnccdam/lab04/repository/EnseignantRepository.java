package com.uanfcnccdam.lab04.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uanfcnccdam.lab04.model.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long>{


	Enseignant findByEnseignantId(long enseignantId);

}
