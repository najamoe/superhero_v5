package com.example.superhero_v5.Model;

public class Superhero {

        private int heroID;
        private String heroName;
        private String realName;
        private int creationYear;
        private String superpower;

        public Superhero(int heroID, String heroName, String realName, int creationYear, String superpower) {
                this.heroID = heroID;
                this.heroName = heroName;
                this.realName = realName;
                this.creationYear = creationYear;
                this.superpower = superpower;
        }

        public int getHeroID() {
                return heroID;
        }

        public void setHeroID(int heroID) {
                this.heroID = heroID;
        }

        public String getHeroName() {
                return heroName;
        }

        public void setHeroName(String heroName) {
                this.heroName = heroName;
        }

        public String getRealName() {
                return realName;
        }

        public void setRealName(String realName) {
                this.realName = realName;
        }

        public int getCreationYear() {
                return creationYear;
        }

        public void setCreationYear(int creationYear) {
                this.creationYear = creationYear;
        }

        public String getSuperpower() {
                return superpower;
        }

        public void setSuperpower(String superpower) {
                this.superpower = superpower;
        }
}
