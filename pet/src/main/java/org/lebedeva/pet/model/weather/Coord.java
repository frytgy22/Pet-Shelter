
package org.lebedeva.pet.model.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lon",
        "lat"
})

@Data
public class Coord {

    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("lat")
    private Double lat;
}
