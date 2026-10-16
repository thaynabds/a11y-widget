package br.com.thaynabds.a11yapi.service;

import br.com.thaynabds.a11yapi.dto.PreferencesDto;
import br.com.thaynabds.a11yapi.model.UserPreferences;
import br.com.thaynabds.a11yapi.repository.PreferencesRepository;
import org.springframework.stereotype.Service;

@Service
public class PreferencesService {

    private final PreferencesRepository preferencesRepository;

    public PreferencesService(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    public PreferencesDto getByEmail(String email) {
        return preferencesRepository.findByUserEmail(email)
            .map(this::toDto)
            .orElse(defaultPrefs());
    }

    public PreferencesDto save(String email, PreferencesDto dto) {
        var entity = preferencesRepository.findByUserEmail(email)
            .orElse(new UserPreferences(email));

        entity.setDarkMode(dto.darkMode());
        entity.setHighContrast(dto.highContrast());
        entity.setFontSize(dto.fontSize());
        entity.setDyslexicFont(dto.dyslexicFont());
        entity.setColorBlind(dto.colorBlind() != null ? dto.colorBlind() : "none");
        entity.setPauseAnimations(dto.pauseAnimations());
        entity.setTextSpacing(dto.textSpacing());
        entity.setBigCursor(dto.bigCursor());

        return toDto(preferencesRepository.save(entity));
    }

    private PreferencesDto toDto(UserPreferences e) {
        return new PreferencesDto(
            e.isDarkMode(), e.isHighContrast(), e.getFontSize(),
            e.isDyslexicFont(), e.getColorBlind(),
            e.isPauseAnimations(), e.isTextSpacing(), e.isBigCursor()
        );
    }

    private PreferencesDto defaultPrefs() {
        return new PreferencesDto(false, false, 0, false, "none", false, false, false);
    }
}
