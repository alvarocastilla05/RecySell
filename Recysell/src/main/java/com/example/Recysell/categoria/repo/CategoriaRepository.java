package com.example.Recysell.categoria.repo;

import com.example.Recysell.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
