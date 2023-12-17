package activityScreen;

public class UserStory {
    private String name;
    private String creator;
    private String description;
    private String relevance;

    public UserStory(String name, String creator, String description, String relevance) {
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.relevance = relevance;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public String getRelevance() {
        return relevance;
    }
}
