package com.example.videogamelibrary.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.example.videogamelibrary.entity.VideoGame;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class VideoGameSerializer extends JsonSerializer<VideoGame> {
    @Override
    public void serialize(VideoGame videoGame, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", videoGame.getId());
        gen.writeStringField("title", videoGame.getTitle());
        gen.writeStringField("genre", videoGame.getGenre());
        // Форматирование даты выпуска
        String formattedDate = videoGame.getReleaseDate() != null 
                               ? videoGame.getReleaseDate().format(DateTimeFormatter.ISO_DATE) 
                               : null;
        gen.writeStringField("releaseDate", formattedDate);
        gen.writeNumberField("rating", videoGame.getRating());
        gen.writeEndObject();
    }
    @SuppressWarnings("deprecation")
    @Override
    public void serializeWithType(VideoGame value, JsonGenerator gen, SerializerProvider serializers,
                                TypeSerializer typeSer) throws IOException {
        // Добавляем типовую обёртку
        typeSer.writeTypePrefixForObject(value, gen);
        serialize(value, gen, serializers);
        typeSer.writeTypeSuffixForObject(value, gen);
    }
}
