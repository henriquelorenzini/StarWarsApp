package com.example.starwarsapp;

class VeiculosClass {
    private String name;
    private String model;
    private String cost_in_credits;
    private String lenght;
    private String crew;
    private String passangers;
    private String cargo_capacity;
    private String consumables;
    private String vehicle_class;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCost_in_credits() {
        return cost_in_credits;
    }

    public void setCost_in_credits(String cost_in_credits) {
        this.cost_in_credits = cost_in_credits;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassangers() {
        return passangers;
    }

    public void setPassangers(String passangers) {
        this.passangers = passangers;
    }

    public String getCargo_capacity() {
        return cargo_capacity;
    }

    public void setCargo_capacity(String cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public String getVehicle_class() {
        return vehicle_class;
    }

    public void setVehicle_class(String vehicle_class) {
        this.vehicle_class = vehicle_class;
    }

    @Override
    public String toString() {
        return "Nome: " + getName()
                + "\nModelo: " + getModel()
                + "\nCusto: " + getCost_in_credits()
                + "\nTamanho: " + getLenght()
                + "\nNº Tripulantes:" + getCrew()
                + "\nNº Passageiros: " + getPassangers()
                + "\nQtd Cargas: " + getCargo_capacity()
                + "\nConsumo: " + getConsumables()
                + "\nClasse do veículo: " + getVehicle_class();
    }
}
