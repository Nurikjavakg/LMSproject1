package peaksoft.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {
    private String groupName;
    private String imageLink;
    private String description;
}
