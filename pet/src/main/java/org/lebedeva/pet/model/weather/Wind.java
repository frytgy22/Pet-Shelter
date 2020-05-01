
package org.lebedeva.pet.model.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "speed",
        "deg"
})

@Data
public class Wind {

    @JsonProperty("speed")
    private Integer speed;
    @JsonProperty("deg")
    private Integer deg;
}
