package com.example.Recysell.donacion.repo;

import com.example.Recysell.donacion.model.Donacion;
import com.example.Recysell.donacion.model.DonacionPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository<Donacion, DonacionPK> {
}
