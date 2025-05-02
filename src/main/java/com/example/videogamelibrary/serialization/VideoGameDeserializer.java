package com.example.videogamelibrary.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.example.videogamelibrary.entity.VideoGame;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VideoGameDeserializer extends JsonDeserializer<VideoGame> {
    @Override
    public VideoGame deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        VideoGame videoGame = new VideoGame();
        if (node.has("id")) {
            videoGame.setId(node.get("id").asLong());
        }
        videoGame.setTitle(node.get("title").asText());
        videoGame.setGenre(node.get("genre").asText());
        if (node.has("releaseDate") && !node.get("releaseDate").isNull()) {
            String dateStr = node.get("releaseDate").asText();
            videoGame.setReleaseDate(LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE));
        }
        if (node.has("rating")) {
            videoGame.setRating(node.get("rating").asDouble());
        }
        return videoGame;
    }
}
