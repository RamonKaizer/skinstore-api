package com.ramonkaizer.skinstore.repository;

import com.ramonkaizer.skinstore.domain.entity.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Long>, JpaSpecificationExecutor<Skin> {

    @Query("SELECT s FROM Skin s WHERE s.ofertaSemana = true AND s.status = 'DISPONIVEL' ")
    List<Skin> ofertaSemana();

}
