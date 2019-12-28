package tutorial.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AdministrativeDivisionKey {
    @Column(name = "\"CodePublisher\"", length = 10)
    private String codePublisher;

    @Column(name = "\"CodeID\"", length = 10)
    private String codeID;

    @Column(name = "\"DivisionCode\"", length = 10)
    private String divisionCode;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeID == null) ? 0 : codeID.hashCode());
        result = prime * result + ((codePublisher == null) ? 0 : codePublisher.hashCode());
        result = prime * result + ((divisionCode == null) ? 0 : divisionCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdministrativeDivisionKey other = (AdministrativeDivisionKey) obj;
        if (codeID == null) {
            if (other.codeID != null)
                return false;
        } else if (!codeID.equals(other.codeID))
            return false;
        if (codePublisher == null) {
            if (other.codePublisher != null)
                return false;
        } else if (!codePublisher.equals(other.codePublisher))
            return false;
        if (divisionCode == null) {
            return other.divisionCode == null;
        } else return divisionCode.equals(other.divisionCode);
    }
}