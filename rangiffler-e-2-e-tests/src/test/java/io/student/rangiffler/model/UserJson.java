package io.student.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UserJson(
    @JsonProperty("id")
    UUID id,
    @JsonProperty("username")
    String username,
    @JsonProperty("password")
    String password

    // TODO Раскомментировать, когда понадобятся все поля из wiremock/rest/mappings/query_user.json
    //  @JsonProperty("firstname")
    //  String firstName,
    //  @JsonProperty("surname")
    //  String surname,
    //  @JsonProperty("avatar")
    //  String avatar,
    //  @JsonProperty("location")
    //  Location location
) {
  public record Location(
      @JsonProperty("code")
      String code,
      @JsonProperty("name")
      String name
  ) {
  }
}
