package br.com.cartpurchase.repository;

import br.com.cartpurchase.model.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartPurchaseRepository extends JpaRepository<ItemDTO, Integer> {
}
