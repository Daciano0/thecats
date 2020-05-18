package com.api.thecat.DTO;

import com.api.thecat.enums.EventsEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

@Data
@AllArgsConstructor
public class EventLogDTO {

    public EventsEnum event;
    public String Information = "TheCatAPI";
    public Object body;

    public EventLogDTO(EventsEnum event, Object body) {
        this.event = event;
        this.body = body;
    }

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}