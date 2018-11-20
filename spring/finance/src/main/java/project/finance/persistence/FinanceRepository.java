package project.finance.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import project.finance.entities.Invoice;

/* $regex
 * $lt or $lte
 * $gt or $gte
 * $ne
 * $eq or $in
 */
public interface FinanceRepository extends MongoRepository<Invoice, String> {

	// Queries
	
	@Query("{ 'items.name' : ?0 }")
	List<Invoice> findByName(String name);
	
	@Query("{ 'items.cost' : {$gt : ?0}}")
	List<Invoice> findByCostGreaterThan(double cost);
	
	@Query("{ 'status' : {$ne : 'Paid'}}")
	List<Invoice> findByStatusNotPaid();
	
	@Query("{ 'patientId' : ?0, 'status' : {$ne : 'PAID'}}")
	List<Invoice> findByPatientIdAndStatusNotPaid(String patientId);
}
