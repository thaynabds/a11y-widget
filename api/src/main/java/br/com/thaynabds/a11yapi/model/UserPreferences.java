package br.com.thaynabds.a11yapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "preferences")
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    private boolean darkMode = false;
    private boolean highContrast = false;
    private int fontSize = 0;
    private boolean dyslexicFont = false;
    private String colorBlind = "none";
    private boolean pauseAnimations = false;
    private boolean textSpacing = false;
    private boolean bigCursor = false;

    // Constructors
    public UserPreferences() {}

    public UserPreferences(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public boolean isDarkMode() { return darkMode; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }
    public boolean isHighContrast() { return highContrast; }
    public void setHighContrast(boolean highContrast) { this.highContrast = highContrast; }
    public int getFontSize() { return fontSize; }
    public void setFontSize(int fontSize) { this.fontSize = fontSize; }
    public boolean isDyslexicFont() { return dyslexicFont; }
    public void setDyslexicFont(boolean dyslexicFont) { this.dyslexicFont = dyslexicFont; }
    public String getColorBlind() { return colorBlind; }
    public void setColorBlind(String colorBlind) { this.colorBlind = colorBlind; }
    public boolean isPauseAnimations() { return pauseAnimations; }
    public void setPauseAnimations(boolean pauseAnimations) { this.pauseAnimations = pauseAnimations; }
    public boolean isTextSpacing() { return textSpacing; }
    public void setTextSpacing(boolean textSpacing) { this.textSpacing = textSpacing; }
    public boolean isBigCursor() { return bigCursor; }
    public void setBigCursor(boolean bigCursor) { this.bigCursor = bigCursor; }
}
