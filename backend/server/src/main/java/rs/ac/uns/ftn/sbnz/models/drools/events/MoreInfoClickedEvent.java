package rs.ac.uns.ftn.sbnz.models.drools.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

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
}
