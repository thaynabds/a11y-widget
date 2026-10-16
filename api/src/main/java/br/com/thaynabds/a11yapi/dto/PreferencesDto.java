package br.com.thaynabds.a11yapi.dto;

public record PreferencesDto(
    boolean darkMode,
    boolean highContrast,
    int fontSize,
    boolean dyslexicFont,
    String colorBlind,
    boolean pauseAnimations,
    boolean textSpacing,
    boolean bigCursor
) {}
