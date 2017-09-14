package com.cryprocodes.mediator.Utils;

public class PatternPair<L,R> {

    private final L key;
    private final R pattern;

    public PatternPair(L key, R pattern) {
        this.key = key;
        this.pattern = pattern;
    }

    public L getKey() { return key; }
    public R getPattern() { return pattern; }

    @Override
    public int hashCode() { return key.hashCode() ^ pattern.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PatternPair)) return false;
        PatternPair pairo = (PatternPair) o;
        return this.key.equals(pairo.getKey()) &&
                this.pattern.equals(pairo.getPattern());
    }
}
