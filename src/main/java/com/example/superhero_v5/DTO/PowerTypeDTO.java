package com.example.superhero_v5.DTO;

import java.util.List;

public class PowerTypeDTO {

    private String realName;
    private String heroName;
    private List<String> powerType;

    public PowerTypeDTO(String realName, String heroName, List <String> powerType) {
        this.realName = realName;
        this.heroName = heroName;
        this.powerType = powerType;
    }

       public PowerTypeDTO(String real_name, String powertype) {
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public List<String> getPowerType() {
        return powerType;
    }

    public void setPowerType(List<String> powerType) {
        this.powerType = powerType;
    }

    @Override
    public String toString() {
        return "{" + this.heroName + "-" + this.realName + "-" + this.powerType + "}";

    }
}
