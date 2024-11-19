package org.jnosql.demoee.heroes;

public record HeroesQuery(String name, String secretIdentity, String impactPhrase) {

    static QueryBuilder queryBuilder() {
        return new QueryBuilder();
    }

    public static class QueryBuilder {

        private String name;
        private String secretIdentity;
        private String impactPhrase;

        public QueryBuilder byName(String name) {
            this.name = name;
            return this;
        }

        public QueryBuilder bySecretIdentity(String secretIdentity) {
            this.secretIdentity = secretIdentity;
            return this;
        }

        public QueryBuilder byImpactPhrase(String impactPhrase) {
            this.impactPhrase = impactPhrase;
            return this;
        }

        public HeroesQuery build() {
            return new HeroesQuery(name, secretIdentity, impactPhrase);
        }

    }
}