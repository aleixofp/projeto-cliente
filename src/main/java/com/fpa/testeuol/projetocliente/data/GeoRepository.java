package com.fpa.testeuol.projetocliente.data;

import com.fpa.testeuol.projetocliente.entity.geo.GeoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoRepository extends JpaRepository<GeoModel, Long> {

	@Override
	Optional<GeoModel> findById(Long aLong);
}
