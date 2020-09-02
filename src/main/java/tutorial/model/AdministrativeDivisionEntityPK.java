package tutorial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Objects;

public class AdministrativeDivisionEntityPK {
    public AdministrativeDivisionEntityPK() {
        // Needed to be used as IdClass for java.lang.reflect.Constructor.newInstance
    }

    public AdministrativeDivisionEntityPK(String codePublisher, String codeId, String divisionCode) {
        this.codePublisher = codePublisher;
        this.codeId = codeId;
        this.divisionCode = divisionCode;
    }

    @Column(name = "\"CodePublisher\"", length = 10)
    @Id
    private String codePublisher;

    public String getCodePublisher() { return codePublisher; }

    public void setCodePublisher(String codePublisher) { this.codePublisher = codePublisher; }

    @Column(name = "\"CodeID\"", length = 10)
    @Id
    private String codeId;

    public String getCodeId() { return codeId; }

    public void setCodeId(String codeId) { this.codeId = codeId; }

    @Column(name = "\"DivisionCode\"", length = 10)
    @Id
    private String divisionCode;

    public String getDivisionCode() { return divisionCode; }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministrativeDivisionEntityPK that = (AdministrativeDivisionEntityPK) o;

        if (!Objects.equals(codePublisher, that.codePublisher)) return false;
        if (!Objects.equals(codeId, that.codeId)) return false;
        return Objects.equals(divisionCode, that.divisionCode);
    }

    @Override
    public int hashCode() {
        int result = codePublisher != null ? codePublisher.hashCode() : 0;
        result = 31 * result + (codeId != null ? codeId.hashCode() : 0);
        result = 31 * result + (divisionCode != null ? divisionCode.hashCode() : 0);
        return result;
    }

}