package ay.kafka.producer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"text",
"id",
"retweeted_status"
})
public class Entities {

@JsonProperty("text")
private String text;
@JsonProperty("id")
private Long id;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/*private Date created_at;
public Date getCreated_at() {
	return created_at;
}

public void setCreated_at(Date created_at) {
	this.created_at = created_at;
}
*/

@JsonProperty("id")
public Long getId() {
	return id;
}

@JsonProperty("id")
public void setId(Long id) {
	this.id = id;
}

@JsonProperty("text")
public String getText() {
	return text;
}

@JsonProperty("text")
public void setText(String text) {
	this.text = text;
}


@Override
public String toString() {
	return "Entities [text=" + text  + ", id=" + id + "]";
} 




}