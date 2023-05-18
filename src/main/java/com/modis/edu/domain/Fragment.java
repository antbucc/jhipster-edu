package com.modis.edu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Fragment.
 */
@Document(collection = "fragment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fragment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    @Field("condition")
    private Fragment condition;

    @DBRef
    @Field("parent")
    @JsonIgnoreProperties(
        value = { "condition", "parents", "preconditions", "effects", "goals", "activities", "children", "module", "modules" },
        allowSetters = true
    )
    private Set<Fragment> parents = new HashSet<>();

    @DBRef
    @Field("precondition")
    @JsonIgnoreProperties(value = { "fragment" }, allowSetters = true)
    private Set<Precondition> preconditions = new HashSet<>();

    @DBRef
    @Field("effect")
    @JsonIgnoreProperties(value = { "fragment" }, allowSetters = true)
    private Set<Effect> effects = new HashSet<>();

    @DBRef
    @Field("goal")
    @JsonIgnoreProperties(value = { "fragment" }, allowSetters = true)
    private Set<Goal> goals = new HashSet<>();

    @DBRef
    @Field("activities")
    @JsonIgnoreProperties(value = { "concepts", "fragments" }, allowSetters = true)
    private Set<Activity> activities = new HashSet<>();

    @DBRef
    @Field("children")
    @JsonIgnoreProperties(
        value = { "condition", "parents", "preconditions", "effects", "goals", "activities", "children", "module", "modules" },
        allowSetters = true
    )
    private Fragment children;

    @DBRef
    @Field("module")
    @JsonIgnoreProperties(value = { "scenario", "fragments", "fragments" }, allowSetters = true)
    private Module module;

    @DBRef
    @Field("modules")
    @JsonIgnoreProperties(value = { "scenario", "fragments", "fragments" }, allowSetters = true)
    private Set<Module> modules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Fragment id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Fragment title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getCondition() {
        return this.condition;
    }

    public void setCondition(Fragment fragment) {
        this.condition = fragment;
    }

    public Fragment condition(Fragment fragment) {
        this.setCondition(fragment);
        return this;
    }

    public Set<Fragment> getParents() {
        return this.parents;
    }

    public void setParents(Set<Fragment> fragments) {
        if (this.parents != null) {
            this.parents.forEach(i -> i.setChildren(null));
        }
        if (fragments != null) {
            fragments.forEach(i -> i.setChildren(this));
        }
        this.parents = fragments;
    }

    public Fragment parents(Set<Fragment> fragments) {
        this.setParents(fragments);
        return this;
    }

    public Fragment addParent(Fragment fragment) {
        this.parents.add(fragment);
        fragment.setChildren(this);
        return this;
    }

    public Fragment removeParent(Fragment fragment) {
        this.parents.remove(fragment);
        fragment.setChildren(null);
        return this;
    }

    public Set<Precondition> getPreconditions() {
        return this.preconditions;
    }

    public void setPreconditions(Set<Precondition> preconditions) {
        if (this.preconditions != null) {
            this.preconditions.forEach(i -> i.setFragment(null));
        }
        if (preconditions != null) {
            preconditions.forEach(i -> i.setFragment(this));
        }
        this.preconditions = preconditions;
    }

    public Fragment preconditions(Set<Precondition> preconditions) {
        this.setPreconditions(preconditions);
        return this;
    }

    public Fragment addPrecondition(Precondition precondition) {
        this.preconditions.add(precondition);
        precondition.setFragment(this);
        return this;
    }

    public Fragment removePrecondition(Precondition precondition) {
        this.preconditions.remove(precondition);
        precondition.setFragment(null);
        return this;
    }

    public Set<Effect> getEffects() {
        return this.effects;
    }

    public void setEffects(Set<Effect> effects) {
        if (this.effects != null) {
            this.effects.forEach(i -> i.setFragment(null));
        }
        if (effects != null) {
            effects.forEach(i -> i.setFragment(this));
        }
        this.effects = effects;
    }

    public Fragment effects(Set<Effect> effects) {
        this.setEffects(effects);
        return this;
    }

    public Fragment addEffect(Effect effect) {
        this.effects.add(effect);
        effect.setFragment(this);
        return this;
    }

    public Fragment removeEffect(Effect effect) {
        this.effects.remove(effect);
        effect.setFragment(null);
        return this;
    }

    public Set<Goal> getGoals() {
        return this.goals;
    }

    public void setGoals(Set<Goal> goals) {
        if (this.goals != null) {
            this.goals.forEach(i -> i.setFragment(null));
        }
        if (goals != null) {
            goals.forEach(i -> i.setFragment(this));
        }
        this.goals = goals;
    }

    public Fragment goals(Set<Goal> goals) {
        this.setGoals(goals);
        return this;
    }

    public Fragment addGoal(Goal goal) {
        this.goals.add(goal);
        goal.setFragment(this);
        return this;
    }

    public Fragment removeGoal(Goal goal) {
        this.goals.remove(goal);
        goal.setFragment(null);
        return this;
    }

    public Set<Activity> getActivities() {
        return this.activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Fragment activities(Set<Activity> activities) {
        this.setActivities(activities);
        return this;
    }

    public Fragment addActivity(Activity activity) {
        this.activities.add(activity);
        activity.getFragments().add(this);
        return this;
    }

    public Fragment removeActivity(Activity activity) {
        this.activities.remove(activity);
        activity.getFragments().remove(this);
        return this;
    }

    public Fragment getChildren() {
        return this.children;
    }

    public void setChildren(Fragment fragment) {
        this.children = fragment;
    }

    public Fragment children(Fragment fragment) {
        this.setChildren(fragment);
        return this;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Fragment module(Module module) {
        this.setModule(module);
        return this;
    }

    public Set<Module> getModules() {
        return this.modules;
    }

    public void setModules(Set<Module> modules) {
        if (this.modules != null) {
            this.modules.forEach(i -> i.removeFragments(this));
        }
        if (modules != null) {
            modules.forEach(i -> i.addFragments(this));
        }
        this.modules = modules;
    }

    public Fragment modules(Set<Module> modules) {
        this.setModules(modules);
        return this;
    }

    public Fragment addModules(Module module) {
        this.modules.add(module);
        module.getFragments().add(this);
        return this;
    }

    public Fragment removeModules(Module module) {
        this.modules.remove(module);
        module.getFragments().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fragment)) {
            return false;
        }
        return id != null && id.equals(((Fragment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fragment{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
