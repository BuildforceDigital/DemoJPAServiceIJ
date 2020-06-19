package tutorial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AdministrativeDivisionDescriptionEntityPK {

    @Column(name = "\"CodePublisher\"", nullable = false, length = 10)
    private String codePublisher;

    public String getCodePublisher() { return codePublisher; }

    public void setCodePublisher(String codePublisher) { this.codePublisher = codePublisher; }

    @Column(name = "\"CodeID\"", nullable = false, length = 10)
    private String codeId;

    public String getCodeId() { return codeId; }

    public void setCodeId(String codeId) { this.codeId = codeId; }

    @Column(name = "\"DivisionCode\"", nullable = false, length = 10)
    private String divisionCode;

    public String getDivisionCode() { return divisionCode; }

    public void setDivisionCode(String divisionCode) { this.divisionCode = divisionCode; }

    @Column(name = "\"LanguageISO\"", nullable = false, length = 4)
    private String languageIso;

    public String getLanguageIso() { return languageIso; }

    public void setLanguageIso(String languageIso) { this.languageIso = languageIso; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministrativeDivisionDescriptionEntityPK that = (AdministrativeDivisionDescriptionEntityPK) o;

        if (!Objects.equals(codePublisher, that.codePublisher)) return false;
        if (!Objects.equals(codeId, that.codeId)) return false;
        if (!Objects.equals(divisionCode, that.divisionCode)) return false;
        return Objects.equals(languageIso, that.languageIso);
    }

    @Override
    public int hashCode() {
        int result = codePublisher != null ? codePublisher.hashCode() : 0;
        result = 31 * result + (codeId != null ? codeId.hashCode() : 0);
        result = 31 * result + (divisionCode != null ? divisionCode.hashCode() : 0);
        result = 31 * result + (languageIso != null ? languageIso.hashCode() : 0);
        return result;
    }

}