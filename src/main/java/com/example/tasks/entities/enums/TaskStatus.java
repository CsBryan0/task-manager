package com.example.tasks.entities.enums;

public enum TaskStatus {
    TODO("A Fazer"),
    IN_PROGRESS("Em Andamento"),
    COMPLETED("Concluída"),
    CANCELLED("Cancelada");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
