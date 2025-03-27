package com.ramonkaizer.skinstore.repository;

import com.ramonkaizer.skinstore.domain.Skin;
import com.ramonkaizer.skinstore.enums.StatusSkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Long> {

    List<Skin> findByStatus(StatusSkin status);
}
