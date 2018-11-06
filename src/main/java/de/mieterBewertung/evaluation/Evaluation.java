package de.mieterBewertung.evaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evaluation {

    private int id;
    private String firstName;
    private String lastName;
    private String city;
    private Date birthDay;
    private List<EvaluationPoint> evaluationPointList;

    public Evaluation(String firstName, String lastName, String city, Date birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.birthDay = birthDay;
        this.evaluationPointList = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean addEvaluationPoint(EvaluationPoint evaluationPoint) {
        return this.evaluationPointList.add(evaluationPoint);
    }

    public boolean removeEvaluationPoint(EvaluationPoint evaluationPoint) {
        return this.evaluationPointList.remove(evaluationPoint);
    }

    public int countOfEvaluationPoints() {
        return this.evaluationPointList.size();
    }

    public List<EvaluationPoint> getEvaluationPointList() {
        return this.evaluationPointList;
    }
}
