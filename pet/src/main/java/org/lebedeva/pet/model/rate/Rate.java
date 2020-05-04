
package org.lebedeva.pet.model.rate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ccy",
        "base_ccy",
        "buy",
        "sale"
})

@Data
public class Rate {

    @JsonProperty("ccy")
    private String ccy;
    @JsonProperty("base_ccy")
    private String baseCcy;
    @JsonProperty("buy")
    private String buy;
    @JsonProperty("sale")
    private String sale;
}
