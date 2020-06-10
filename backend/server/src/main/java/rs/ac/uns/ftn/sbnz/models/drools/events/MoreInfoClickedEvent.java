package rs.ac.uns.ftn.sbnz.models.drools.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;
import java.util.Objects;

@Role(Role.Type.EVENT)
@Timestamp("clickedAt")
@Expires("14d")
public class MoreInfoClickedEvent {

    private Date clickedAt;
    private Long propertyId;

    public MoreInfoClickedEvent(Date clickedAt, Long propertyId) {
        this.clickedAt = clickedAt;
        this.propertyId = propertyId;
    }

    public Date getClickedAt() {
        return clickedAt;
    }

    public void setClickedAt(Date clickedAt) {
        this.clickedAt = clickedAt;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoreInfoClickedEvent event = (MoreInfoClickedEvent) o;
        return clickedAt.equals(event.clickedAt) &&
                propertyId.equals(event.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clickedAt, propertyId);
    }
}
