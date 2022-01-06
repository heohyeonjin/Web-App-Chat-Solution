package chatsolution.web.client.repository;

import chatsolution.web.client.model.Client;
import chatsolution.web.corporation.model.Corporation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientEmail(String email);
    Client findByClientNo(Long clientNo);
    @NotNull
    Page<Client> findAll(Pageable pageable);
    Page<Client> findAllByClientNameContaining(String clientName, Pageable pageable);
    List<Client> findByClientNameContaining(String clientName);
}
