package chatsolution.web.auth.repository;

import chatsolution.web.auth.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Admin, String> {
    Admin findByAdminId(String id);
}
