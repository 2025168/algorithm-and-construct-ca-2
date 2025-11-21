package ca_2;

public enum ManagerType {
    INTERN("Entry-level learning position", 1),
    JUNIOR("Early career role with supervision", 2),
    MIDDLE("Mid-level position with independent responsibilities", 3),
    SENIOR("Experienced role with leadership duties", 4),
    CONTRACT("Temporary or project-based employment", 0);

    private final String description;
    private final int hierarchyLevel;

    ManagerType(String description, int hierarchyLevel) {
        this.description = description;
        this.hierarchyLevel = hierarchyLevel;
    }

    public String getDescription() {
        return description;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }
}
