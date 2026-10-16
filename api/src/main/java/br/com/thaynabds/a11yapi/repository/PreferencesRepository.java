package br.com.thaynabds.a11yapi.repository;

import br.com.thaynabds.a11yapi.model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PreferencesRepository extends JpaRepository<UserPreferences, Long> {
    Optional<UserPreferences> findByUserEmail(String userEmail);
}
