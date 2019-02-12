package project.ward.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import project.ward.entities.Ward;

public interface WardRepository extends CrudRepository<Ward, Long>{

	/* Queries */
	int countByIdAndBedsPatientIDIsNull(Long id); 
	
	@Query("SELECT w FROM Ward w WHERE w.id = ?1")
	Ward findWardById(Long id);
	
	@Query("SELECT w FROM Ward w")
	List<Ward> findAllWards(); 
}
